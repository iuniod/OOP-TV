package command.commands.features;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import output.OutputFactory;

import java.io.IOException;
import java.util.Objects;

import static database.Constants.*;

public final class Filter extends OutputFactory implements Command {
  private final Action action;

  public Filter(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    if (!database.getCurrentPage().equalsIgnoreCase(MOVIES)) {
      return false;
    }

    return database.getFeatureWorkFlow().get(MOVIES).contains(action.getFeature().toUpperCase());
  }

  @Override
  public void executeError(final ObjectMapper mapper,
                           final ArrayNode arrayNode) throws IOException {
    Objects.requireNonNull(getOutput(ERROR, action)).write(mapper, arrayNode);
  }

  @Override
  public void executeSuccess(final ObjectMapper mapper,
                             final ArrayNode arrayNode) throws IOException {
    Objects.requireNonNull(getOutput(SUCCESS, action)).write(mapper, arrayNode);
  }

}
