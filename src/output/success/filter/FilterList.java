package output.success.filter;

import database.Database;
import input.action.filters.Filter;
import input.action.filters.Sort;
import input.movie.Movie;
import output.success.Strategy;

import java.util.ArrayList;
import java.util.Comparator;

import static database.Constants.INCREASING;

public final class FilterList implements Strategy {
  private final Filter filters;

  public FilterList(final Filter filters) {
    this.filters = filters;
  }
  @Override
  public ArrayList<Movie> findMovieList(final ArrayList<Movie> movies) {
    ArrayList<Movie> moviesList = containsFilter(movies);

    moviesList = sortMovies(moviesList, filters.getSort());

    Database.getInstance().setCurrentMovieList(moviesList);
    return moviesList;
  }

  private ArrayList<Movie> containsFilter(final ArrayList<Movie> movies) {
    ArrayList<Criteria> criteriaList = new ArrayList<>();
    criteriaList.add(new CriteriaIsNotBanned());
    if (filters.getContains() != null) {
      criteriaList.add(new CriteriaActors(filters.getContains().getActors()));
      criteriaList.add(new CriteriaGenre(filters.getContains().getGenre()));
    }

    Criteria criteria = new AllCriteria(criteriaList);

    return criteria.meetCriteria(movies);
  }
  private ArrayList<Movie> sortMovies(final ArrayList<Movie> moviesList, final Sort sort) {
    if (sort == null) {
      return moviesList;
    }

    if (sort.getRating() != null) {
      if (sort.getRating().equalsIgnoreCase(INCREASING)) {
        moviesList.sort(Comparator.comparing(Movie::getRating));
      } else {
        moviesList.sort(Comparator.comparing(Movie::getRating).reversed());
      }
    }

    if (sort.getDuration() != null) {
      if (sort.getDuration().equalsIgnoreCase(INCREASING)) {
        moviesList.sort(Comparator.comparingInt(Movie::getDuration));
      } else {
        moviesList.sort(Comparator.comparingInt(Movie::getDuration).reversed());
      }
    }

    return moviesList;
  }
}
