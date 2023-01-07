package output.success;

import database.Database;
import input.action.Action;
import input.movie.Movie;
import input.user.User;
import output.Output;
import output.success.filter.FilterList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static database.Constants.*;

public final class Success implements Output {
  private static final List<String> DETAILS = Arrays.asList(SEE_DETAILS, PURCHASE,
      WATCH, LIKE, RATE);
  private final Action action;

  public Success(final Action action) {
    this.action = action;
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
    ArrayList<Movie> movies = Database.getInstance().getMovies();
    String key = action.getFeature();

    if (key == null) {
      key = action.getPage();
    }

    Context context = new Context(new NullList());

    if (key.equalsIgnoreCase(MOVIES)) {
      context = new Context(new MovieList());
    } else if (key.equalsIgnoreCase((SEARCH))) {
      context = new Context(new SearchList(action.getStartsWith()));
    } else if (DETAILS.contains(key.toUpperCase())) {
      context = new Context(new DetailsList());
    } else if (key.equalsIgnoreCase((FILTER))) {
      context = new Context(new FilterList(action.getFilters()));
    }

    return context.executeStrategy(movies);
  }
}
