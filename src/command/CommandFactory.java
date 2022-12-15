package command;

public class CommandFactory {
  public static AbstractFactory getAction(final String actionType) {
    switch (actionType) {
      case "on page":{
        return new OnPageFactory();
      }
      case "change page": {
        return new ChangePageFactory();
      }
      default: {
        return null;
      }
    }
  }
}