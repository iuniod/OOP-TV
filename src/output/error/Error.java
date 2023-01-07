package output.error;

import input.movie.Movie;
import input.user.User;
import output.Output;

import java.util.ArrayList;

import static database.Constants.OUTPUT_ERROR;

public final class Error implements Output {
  @Override
  public String error() {
    return OUTPUT_ERROR;
  }

  @Override
  public User currentUser() {
    return null;
  }

  @Override
  public ArrayList<Movie> currentMoviesList() {
    return new ArrayList<Movie>();
  }
}
