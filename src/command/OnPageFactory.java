package command;

import command.commands.Login;
import command.commands.Register;
import input.action.Action;

public class OnPageFactory extends AbstractFactory {

  @Override
  public Command getAction(final Action action) {
    switch (action.getFeature()) {
      case "login" -> {return  new Login(action);}
      case "register" -> {return new Register(action);}
      default -> {return null;}
    }
  }
}
