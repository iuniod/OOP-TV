package output;

import input.movie.Movie;
import input.user.User;

import java.util.ArrayList;

class OutputFormat {
  private String error;
  private ArrayList<Movie> currentMoviesList;
  private User currentUser;

  public OutputFormat(final String error, final ArrayList<Movie> currentMoviesList,
                      final User currentUser) {
    this.error = error;
    this.currentMoviesList = currentMoviesList;
    this.currentUser = currentUser;
  }

  public OutputFormat() {
  }

  protected void setError(final String error) {
    this.error = error;
  }

  protected void setCurrentMoviesList(final ArrayList<Movie> currentMoviesList) {
    this.currentMoviesList = currentMoviesList;
  }

  protected void setCurrentUser(final User currentUser) {
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
