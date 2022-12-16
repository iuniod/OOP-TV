package command.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import output.OutputFactory;

import java.io.File;
import java.io.IOException;

public class ChangePage extends OutputFactory implements Command {
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
  public void executeSuccess(ObjectMapper mapper, ArrayNode arrayNode, File myOutput) throws IOException {
    Database.getInstance().setCurrentPage(action.getPage());
//  change page logout moves us to HOMEPAGENEAUTENTIFICAT
    if (Database.getInstance().getCurrentPage().equalsIgnoreCase("LOGOUT")) {
      Database.getInstance().setCurrentPage("HOMEPAGENEAUTENTIFICAT");
    } else if (Database.getInstance().getCurrentPage().equalsIgnoreCase("MOVIES")) {
      getOutput("SUCCESS", action.getPage()).write(mapper, arrayNode, myOutput);
    }
  }

  @Override
  public void executeError(ObjectMapper mapper, ArrayNode arrayNode, File output) throws IOException {
    getOutput("ERROR", action.getFeature()).write(mapper, arrayNode, output);
  }
}
