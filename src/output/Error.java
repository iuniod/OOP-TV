package output;

import input.movie.Movie;
import input.user.User;

import java.util.ArrayList;

public final class Error implements Output {
  @Override
  public String error() {
    return "Error";
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
