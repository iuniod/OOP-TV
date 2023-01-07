package command.commands.navigate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.ChangePageFactory;
import command.Command;
import database.Database;
import input.action.Action;
import output.OutputFactory;

import java.io.IOException;
import java.util.Objects;

import static database.Constants.*;

public final class BackPage extends OutputFactory implements Command {
  private final Action action;

  public BackPage(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    if (!Database.getInstance().hasStackPreviousPage()) {
      return false;
    }

    String currentPage = Database.getInstance().getCurrentPage().toUpperCase();
    return Database.getInstance().getPageWorkFlow().get(BACK.toUpperCase()).contains(currentPage);
  }

  @Override
  public void executeError(final ObjectMapper mapper,
                           final ArrayNode arrayNode) throws IOException {
    Objects.requireNonNull(getOutput(ERROR, action)).write(mapper, arrayNode);
  }

  @Override
  public void executeSuccess(final ObjectMapper mapper,
                             final ArrayNode arrayNode) throws IOException {
    Database.getInstance().setCurrentPage(BACK);

    Database.getInstance().popPageFromStack();
    Action previousAction = Database.getInstance().peekPageFromStack();

    if (previousAction.getPage().equalsIgnoreCase(HOMEPAGEAUTENTIFICAT)) {
      Database.getInstance().setCurrentPage(HOMEPAGEAUTENTIFICAT);
      return;
    }

    Command command = new ChangePageFactory().getAction(previousAction);
    if (command.isExecutable()) {
      command.executeSuccess(mapper, arrayNode);
    } else {
      command.executeError(mapper, arrayNode);
    }

  }
}
