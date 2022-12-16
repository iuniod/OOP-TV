package command;

import command.commands.Filter;
import command.commands.Login;
import command.commands.Register;
import command.commands.Search;
import input.action.Action;

public class OnPageFactory extends AbstractFactory {

  @Override
  public Command getAction(final Action action) {
    switch (action.getFeature()) {
      case "login":{
        return  new Login(action);
      }
      case "register": {
        return new Register(action);
      }
      case "search": {
        return new Search(action);
      }
      case "filter": {
        return new Filter(action);
      }
      default: {
        return null;
      }
    }
  }
}
