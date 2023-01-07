package command;

import command.commands.database.Add;
import command.commands.database.Delete;
import input.action.Action;

import static database.Constants.ADD;
import static database.Constants.DELETE;

public final class DatabaseFactory extends AbstractFactory {
  @Override
  public Command getAction(final Action action) {
    switch (action.getFeature().toUpperCase()) {
      case ADD:
        return new Add(action);
      case DELETE:
        return new Delete(action);
      default:
        return null;
    }
  }
}
