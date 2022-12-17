package command.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import output.OutputFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ChangePage extends OutputFactory implements Command {
  private final Action action;

  public ChangePage(final Action action) {
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

  @Override
  public void executeSuccess(final ObjectMapper mapper,
                             final ArrayNode arrayNode, final File output) throws IOException {
    switch (action.getPage().toUpperCase()) {
      case "LOGOUT":
        Database.getInstance().setCurrentPage("HOMEPAGENEAUTENTIFICAT");
        break;
      case "MOVIES":
        Objects.requireNonNull(getOutput("SUCCESS", action)).write(mapper, arrayNode, output);
        Database.getInstance().setCurrentPage(action.getPage());
        break;
      case "SEE DETAILS":
        if (Database.getInstance().getMoviesTitles().contains(action.getMovie())) {
          Database.getInstance().setCurrentPage(action.getPage());
          Database.getInstance().setCurrentMovie(action.getMovie());
          Objects.requireNonNull(getOutput("SUCCESS", action)).write(mapper, arrayNode, output);
        } else {
          Objects.requireNonNull(getOutput("ERROR", action)).write(mapper, arrayNode, output);
        }
        break;
      default:
        Database.getInstance().setCurrentPage(action.getPage());
        break;
    }
  }

  @Override
  public void executeError(final ObjectMapper mapper,
                           final ArrayNode arrayNode, final File output) throws IOException {
    Objects.requireNonNull(getOutput("ERROR", action)).write(mapper, arrayNode, output);
  }
}
