package output.success.filter;

import input.movie.Movie;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class CriteriaGenre implements Criteria {
  private final ArrayList<String> genres;

  public CriteriaGenre(final ArrayList<String> genres) {
    this.genres = genres;
  }

  @Override
  public ArrayList<Movie> meetCriteria(final ArrayList<Movie> movies) {
    return (ArrayList<Movie>) movies.stream().filter(this::containsActors)
                   .collect(Collectors.toList());
  }

  private boolean containsActors(final Movie movie) {
    return movie.getGenres().containsAll(genres);
  }
}
