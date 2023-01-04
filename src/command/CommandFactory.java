package command;

import static database.Constants.*;

public final class CommandFactory {
  private CommandFactory() {
  }
  /** Factory pattern. Returns the correct factory according to the action type. */
  public static AbstractFactory getAction(final String actionType) {
    switch (actionType.toLowerCase()) {
      case ON_PAGE:
        return new OnPageFactory();
      case CHANGE_PAGE:
        return new ChangePageFactory();
      default:
        return null;
    }
  }
}
