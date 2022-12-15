package output;

import database.Database;
import input.movie.Movie;
import input.user.User;

import java.util.ArrayList;

public class Success implements Output {
  private String page;
  public Success(final String page) {
    this.page = page;
  }

  @Override
  public String error() {
    return null;
  }

  @Override
  public User currentUser() {
    return Database.getInstance().getCurrentUser();
  }

  @Override
  public ArrayList<Movie> currentMoviesList() {
    return new ArrayList<Movie>();
  }
}
