package command;

public class CommandFactory {
  public static AbstractFactory getAction(final String actionType) {
    return switch (actionType) {
      case "on page" -> new OnPageFactory();
      case "change page" -> new ChangePageFactory();
      default -> null;
    };
  }
}