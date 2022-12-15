package output;

import input.movie.Movie;
import input.user.User;

import java.util.ArrayList;

public class Error implements Output {
  private final String page;

  public Error(final String page) {
    this.page = page;
  }

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
