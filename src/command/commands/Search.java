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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Search implements Command, Output {
  private final Action action;
  private final ArrayList<Movie> movies = new ArrayList<>();
  private String error = null;

  public Search(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    if (!database.getCurrentPage().equalsIgnoreCase("MOVIES")) {
      error = "Error";
      return false;
    }

    if (!database.getFeatureWorkFlow().get("MOVIES").contains(action.getFeature().toUpperCase())) {
      error = "Error";
      return false;
    }

    Credential credentials = Database.getInstance().getCurrentUser().getCredentials();
    for (Movie movie : database.getMovies())
      if (movie.getCountriesBanned().isEmpty()) {
        movies.add(movie);
      } else if (!movie.getCountriesBanned().contains(credentials.getCountry()) &&
                     movie.getName().startsWith(action.getStartsWith())) {
        movies.add(movie);
      }

    return true;
  }

  @Override
  public void executeError(ObjectMapper mapper, ArrayNode arrayNode, File output) throws IOException {
    Output.super.write(mapper, arrayNode, output);
  }

  @Override
  public void executeSuccess(ObjectMapper mapper, ArrayNode arrayNode, File output) throws IOException {
    Output.super.write(mapper, arrayNode, output);
  }

  @Override
  public String error() {
    if (isExecutable())
      return null;

    return error;
  }

  @Override
  public User currentUser() {
    if (error != null)
      return null;

    return Database.getInstance().getCurrentUser();
  }

  @Override
  public ArrayList<Movie> currentMoviesList() {
    if (isExecutable())
      return movies;
    return null;
  }
}
