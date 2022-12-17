package command;

import command.commands.*;
import input.action.Action;

public final class OnPageFactory extends AbstractFactory {

  @Override
  public Command getAction(final Action action) {
    switch (action.getFeature()) {
      case "login":
        return new Login(action);
      case "register":
        return new Register(action);
      case "search":
        return new Search(action);
      case "filter":
        return new Filter(action);
      case "buy tokens":
        return new BuyTokens(action);
      case "buy premium account":
        return new BuyPremiumAccount(action);
      case "purchase":
        return new Purchase(action);
      case "watch":
        return new Watch(action);
      case "like":
        return new Like(action);
      case "rate":
        return new Rate(action);
      default:
        return null;
    }
  }
}
