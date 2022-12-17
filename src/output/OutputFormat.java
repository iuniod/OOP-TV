package output;

import input.movie.Movie;
import input.user.User;

import java.util.ArrayList;

public final class OutputFormat {
  private final String error;
  private final ArrayList<Movie> currentMoviesList;
  private final User currentUser;

  public OutputFormat(final String error, final ArrayList<Movie> currentMoviesList,
                      final User currentUser) {
    this.error = error;
    this.currentMoviesList = currentMoviesList;
    this.currentUser = currentUser;
  }

  protected String getError() {
    return error;
  }

  protected ArrayList<Movie> getCurrentMoviesList() {
    return currentMoviesList;
  }

  protected User getCurrentUser() {
    return currentUser;
  }
}
