package output.success;

import database.Database;
import input.movie.Movie;
import input.user.Credential;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class SearchList implements Strategy {
  private final String startTitle;
  SearchList(final String startTitle) {
    this.startTitle = startTitle;
  }
  @Override
  public ArrayList<Movie> findMovieList(final ArrayList<Movie> movies) {
    Database database = Database.getInstance();
    Credential credentials = database.getCurrentUser().getCredentials();
    String country = credentials.getCountry();

    return (ArrayList<Movie>)
               database.getMovies().stream().filter(movie ->
               (movie.getCountriesBanned().isEmpty()
                || !movie.getCountriesBanned().contains(country))
                   && movie.getName().startsWith(startTitle))
                .collect(Collectors.toList());
  }
}
