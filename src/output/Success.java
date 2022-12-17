package output;

import database.Database;
import input.action.Action;
import input.movie.Movie;
import input.user.Credential;
import input.user.User;

import java.util.ArrayList;

public final class Success implements Output {
  private final Action action;
  public Success(final Action action) {
    this.action = action;
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
    ArrayList<Movie> moviesList = new ArrayList<>();
    ArrayList<Movie> movies = Database.getInstance().getMovies();
    Credential credentials = Database.getInstance().getCurrentUser().getCredentials();
    String key = action.getPage();

    if (key == null) {
      key = action.getFeature();
    }

    if (key.equalsIgnoreCase("MOVIES")) {
      if (!movies.isEmpty()) {
        for (Movie movie : movies) {
          if (!movie.getCountriesBanned().contains(credentials.getCountry())) {
            moviesList.add(movie);
          }
        }
      }
    } else if (key.equalsIgnoreCase("SEE DETAILS")) {
      for (Movie movie : movies) {
        if (movie.getName().equalsIgnoreCase(action.getMovie())) {
          moviesList.add(movie);
          break;
        }
      }
    }

    return moviesList;
  }
}
