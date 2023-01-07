package output.success;

import input.movie.Movie;

import java.util.ArrayList;

public final class NullList implements Strategy {

  @Override
  public ArrayList<Movie> findMovieList(final ArrayList<Movie> movies) {
    return new ArrayList<Movie>();
  }
}
