package output.success;

import input.movie.Movie;

import java.util.ArrayList;

public interface Strategy {
  /**
   * Returns the movies list after applying the strategy.
   * @param movies the initial movies list
   * @return the movies list after applying the strategy
   */
  ArrayList<Movie> findMovieList(ArrayList<Movie> movies);
}
