package output.success.filter;

import input.movie.Movie;

import java.util.ArrayList;

public interface Criteria {
  /**
   * Filter movies according to the criteria
   *
   * @param movies the list of movies
   * @return the list of movies filtered
   */
  ArrayList<Movie> meetCriteria(ArrayList<Movie> movies);
}
