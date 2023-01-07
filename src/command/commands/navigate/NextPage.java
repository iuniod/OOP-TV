package command.commands.navigate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import output.OutputFactory;

import java.io.IOException;
import java.util.Objects;

import static database.Constants.*;

public final class NextPage extends OutputFactory implements Command {
  private final Action action;

  public NextPage(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    String currentPage = Database.getInstance().getCurrentPage();
    String nextPage = action.getPage();
    if (currentPage == null || nextPage == null) {
      return false;
    }

    currentPage = currentPage.toUpperCase();
    nextPage = nextPage.toUpperCase();

    return Database.getInstance().getPageWorkFlow().get(currentPage).contains(nextPage);
  }

  private void executeSuccessMovie(final ObjectMapper mapper,
                                   final ArrayNode arrayNode) throws IOException {
    Objects.requireNonNull(getOutput(SUCCESS, action)).write(mapper, arrayNode);
    Database database = Database.getInstance();
    database.setCurrentPage(action.getPage());
    database.setCurrentMovieList(database.getMovies().stream().filter(
        movie -> !movie.getCountriesBanned().contains(database.getCurrentUser().getCredentials()
                 .getCountry())).collect(java.util.stream
                 .Collectors.toCollection(java.util.ArrayList::new)));
    Database.getInstance().pushPageToStack(action);
  }

  private void executeSuccessSeeDetails(final ObjectMapper mapper,
                                        final ArrayNode arrayNode) throws IOException {
    if (Database.getInstance().getMoviesTitles().contains(action.getMovie())) {
      Database.getInstance().setCurrentPage(action.getPage());
      Database.getInstance().setCurrentMovie(action.getMovie());
      Objects.requireNonNull(getOutput(SUCCESS, action)).write(mapper, arrayNode);
      Database.getInstance().pushPageToStack(action);
    } else {
      Objects.requireNonNull(getOutput(ERROR, action)).write(mapper, arrayNode);
    }
  }

  private void executeSuccessLogout(final ObjectMapper mapper,
                                     final ArrayNode arrayNode) throws IOException {
    Database.getInstance().setCurrentPage(HOMEPAGENEAUTENTIFICAT);
    Database.getInstance().setCurrentUser(null);
    Database.getInstance().clearStack();
  }

  @Override
  public void executeSuccess(final ObjectMapper mapper,
                             final ArrayNode arrayNode) throws IOException {
    switch (action.getPage().toUpperCase()) {
      case LOGOUT:
        executeSuccessLogout(mapper, arrayNode);
        break;
      case MOVIES:
        executeSuccessMovie(mapper, arrayNode);
        break;
      case SEE_DETAILS:
        executeSuccessSeeDetails(mapper, arrayNode);
        break;
      default:
        Database.getInstance().setCurrentPage(action.getPage());
        if (action.getPage().equalsIgnoreCase(UPGRADES)) {
          Database.getInstance().pushPageToStack(action);
        }
        break;
    }
  }

  @Override
  public void executeError(final ObjectMapper mapper,
                           final ArrayNode arrayNode) throws IOException {
    Objects.requireNonNull(getOutput(ERROR, action)).write(mapper, arrayNode);
  }
}
