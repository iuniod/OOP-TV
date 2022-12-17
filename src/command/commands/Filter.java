package command.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import input.action.filters.Contain;
import input.action.filters.Sort;
import input.movie.Movie;
import input.user.Credential;
import input.user.User;
import output.Output;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class Filter implements Command, Output {
  private final Action action;
  private ArrayList<Movie> movies = new ArrayList<>();

  public Filter(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    if (!database.getCurrentPage().equalsIgnoreCase("MOVIES")) {
      return false;
    }

    return database.getFeatureWorkFlow().get("MOVIES").contains(action.getFeature().toUpperCase());
  }

  @Override
  public void executeError(final ObjectMapper mapper,
                           final ArrayNode arrayNode, final File output) throws IOException {
    Output.super.write(mapper, arrayNode, output);
  }

  @Override
  public void executeSuccess(final ObjectMapper mapper,
                             final ArrayNode arrayNode, final File output) throws IOException {
    Output.super.write(mapper, arrayNode, output);
  }

  @Override
  public String error() {
    if (isExecutable()) {
      return null;
    }
    return "Error";
  }

  @Override
  public User currentUser() {
    if (isExecutable()) {
      return Database.getInstance().getCurrentUser();
    }
    return null;
  }

  @Override
  public ArrayList<Movie> currentMoviesList() {
    ArrayList<Movie> moviesList = new ArrayList<>();
    if (!isExecutable()) {
      return moviesList;
    }


    Database database = Database.getInstance();

    moviesList =
        (ArrayList<Movie>) database.getMovies().stream().filter(movie -> isNotBanned(movie)
                                                                             && contains(movie, action.getFilters().getContains())).collect(Collectors.toList());


    moviesList = sortMovies(moviesList, action.getFilters().getSort());

    return moviesList;
  }

  private boolean isNotBanned(final Movie movie) {
    Database database = Database.getInstance();
    Credential credentials = database.getCurrentUser().getCredentials();
    return movie.getCountriesBanned().isEmpty()
               || !movie.getCountriesBanned().contains(credentials.getCountry());
  }

  private boolean contains(final Movie movie, final Contain filter) {
    if (filter == null) {
      return true;
    }

    if (!filter.getGenre().isEmpty()) {
      for (String genre : filter.getGenre()) {
        boolean found = false;
        for (String movieGenre : movie.getGenres()) {
          if (movieGenre.equalsIgnoreCase(genre)) {
            found = true;
            break;
          }
        }
        if (!found) {
          return false;
        }
      }
    }

    if (!filter.getActors().isEmpty()) {
      for (String actor : filter.getActors()) {
        boolean found = false;
        for (String movieActor : movie.getActors()) {
          if (movieActor.equalsIgnoreCase(actor)) {
            found = true;
            break;
          }
        }
        if (!found) {
          return false;
        }
      }
    }

    return true;
  }

  private ArrayList<Movie> sortMovies(final ArrayList<Movie> moviesList, final Sort sort) {
    if (sort == null) {
      return moviesList;
    }

    if (sort.getRating() != null) {
      if (sort.getRating().equalsIgnoreCase("increasing")) {
        moviesList.sort(Comparator.comparing(Movie::getRating));
      } else {
        moviesList.sort(Comparator.comparing(Movie::getRating).reversed());
      }
    }

    if (sort.getDuration() != null) {
      if (sort.getDuration().equalsIgnoreCase("increasing")) {
        moviesList.sort(Comparator.comparingInt(Movie::getDuration));
      } else {
        moviesList.sort(Comparator.comparingInt(Movie::getDuration).reversed());
      }
    }

    return moviesList;
  }
}
