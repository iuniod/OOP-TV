package output;

import database.Database;
import input.movie.Movie;
import input.user.Credential;
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
    ArrayList<Movie> moviesList = new ArrayList<>();
    if (page.equalsIgnoreCase("MOVIES")) {
      Credential credentials = Database.getInstance().getCurrentUser().getCredentials();
      ArrayList<Movie> movies = Database.getInstance().getMovies();
      if (!movies.isEmpty()) {
        for (Movie movie : movies) {
          if (!movie.getCountriesBanned().contains(credentials.getCountry())) {
            moviesList.add(movie);
          }
        }
      }
    }

    return moviesList;
  }
}
