package output.success;

import input.movie.Movie;

import java.util.ArrayList;

public final class Context {
  private final Strategy strategy;

  public Context(final Strategy strategy) {
    this.strategy = strategy;
  }

  /**
   * Executes the strategy.

   * @param movies the initial movies list.
   * @return the movies list after applying the strategy.
   */
  public ArrayList<Movie> executeStrategy(final ArrayList<Movie> movies) {
    return strategy.findMovieList(movies);
  }
}
