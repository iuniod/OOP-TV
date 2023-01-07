package output.success.filter;

import database.Database;
import input.movie.Movie;
import input.user.Credential;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class CriteriaIsNotBanned implements Criteria {

  @Override
  public ArrayList<Movie> meetCriteria(final ArrayList<Movie> movies) {
    return (ArrayList<Movie>) movies.stream().filter(this::isNotBanned)
               .collect(Collectors.toList());
  }

  private boolean isNotBanned(final Movie movie) {
    Database database = Database.getInstance();
    Credential credentials = database.getCurrentUser().getCredentials();
    return movie.getCountriesBanned().isEmpty()
               || !movie.getCountriesBanned().contains(credentials.getCountry());
  }
}
