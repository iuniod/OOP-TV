package command.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import output.Output;

import java.io.File;
import java.io.IOException;

import static output.OutputFactory.getOutput;

public class ChangePage implements Command, Output {
  private final Action action;
  private String error;

  public ChangePage(final Action action) {
    this.action = action;
  }
  @Override
  public boolean isExecutable() {
    String currentPage = Database.getInstance().getCurrentPage();
    return currentPage.equals(action.getPage());
  }

  @Override
  public void executeSuccess(ObjectMapper mapper, ArrayNode arrayNode, File myOutput) {
    Database.getInstance().setCurrentPage(action.getPage());
    if (action.getPage().equals("HomepageNeautentificat")) {
      Database.getInstance().setCurrentUser(null);
    }
  }

  @Override
  public void executeError(ObjectMapper mapper, ArrayNode arrayNode, File output) throws IOException {
    getOutput("error").write(mapper, "error", arrayNode, output);
  }
}
