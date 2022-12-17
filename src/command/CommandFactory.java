package command;

public final class CommandFactory {
  /** Factory pattern. Returns the correct factory according to the action type. */
  public static AbstractFactory getAction(final String actionType) {
    switch (actionType) {
      case "on page":
        return new OnPageFactory();
      case "change page":
        return new ChangePageFactory();
      default:
        return null;
    }
  }
}
