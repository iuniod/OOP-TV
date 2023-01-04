package command.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import input.movie.Movie;
import input.user.Credential;
import input.user.User;
import output.Output;

import java.io.IOException;
import java.util.ArrayList;

import static database.Constants.*;

public final class Search implements Command, Output {
  private final Action action;
  private final ArrayList<Movie> movies = new ArrayList<>();
  private String error = null;

  public Search(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    if (!database.getCurrentPage().equalsIgnoreCase(MOVIES)) {
      error = OUTPUTERROR;
      return false;
    }

    if (!database.getFeatureWorkFlow().get(MOVIES).contains(action.getFeature().toUpperCase())) {
      error = OUTPUTERROR;
      return false;
    }

    Credential credentials = Database.getInstance().getCurrentUser().getCredentials();
    movies.clear();
    for (Movie movie : database.getMovies()) {
      if ((movie.getCountriesBanned().isEmpty()
               || !movie.getCountriesBanned().contains(credentials.getCountry()))
                     && movie.getName().startsWith(action.getStartsWith())) {
        movies.add(movie);
      }
    }

    return true;
  }

  @Override
  public void executeError(final ObjectMapper mapper,
                           final ArrayNode arrayNode) throws IOException {
    Output.super.write(mapper, arrayNode);
  }

  @Override
  public void executeSuccess(final ObjectMapper mapper,
                             final ArrayNode arrayNode) throws IOException {
    Output.super.write(mapper, arrayNode);
  }

  @Override
  public String error() {
    return isExecutable() ? null : error;
  }

  @Override
  public User currentUser() {
    return error != null ? null : Database.getInstance().getCurrentUser();
  }

  @Override
  public ArrayList<Movie> currentMoviesList() {
    return isExecutable() ? movies : null;
  }
}
