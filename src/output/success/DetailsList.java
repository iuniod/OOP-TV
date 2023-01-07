package output.success;

import database.Database;
import input.movie.Movie;

import java.util.ArrayList;

public final class DetailsList implements Strategy {

  @Override
  public ArrayList<Movie> findMovieList(final ArrayList<Movie> movies) {
    ArrayList<Movie> moviesList = new ArrayList<>();
    moviesList.add(Database.getInstance().getCurrentMovie());
    return moviesList;
  }
}
