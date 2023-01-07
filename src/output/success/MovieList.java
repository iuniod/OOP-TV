package output.success;

import database.Database;
import input.movie.Movie;
import input.user.Credential;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class MovieList implements Strategy {
  MovieList() {
  }

  @Override
  public ArrayList<Movie> findMovieList(final ArrayList<Movie> initialMovies) {
    ArrayList<Movie> movies = new ArrayList<>();

    Credential credentials = Database.getInstance().getCurrentUser().getCredentials();

    return initialMovies.isEmpty() ? movies
               : (ArrayList<Movie>) initialMovies.stream().filter(movie ->
               !movie.getCountriesBanned().contains(credentials.getCountry()))
                                      .collect(Collectors.toList());
  }
}
