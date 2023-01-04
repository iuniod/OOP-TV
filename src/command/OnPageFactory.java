package command;

import command.commands.*;
import input.action.Action;

import static database.Constants.*;

public final class OnPageFactory extends AbstractFactory {

  @Override
  public Command getAction(final Action action) {
    switch (action.getFeature().toUpperCase()) {
      case LOGIN:
        return new Login(action);
      case REGISTER:
        return new Register(action);
      case SEARCH:
        return new Search(action);
      case FILTER:
        return new Filter(action);
      case BUYTOKENS:
        return new BuyTokens(action);
      case BUYPREMIUMACCOUNT:
        return new BuyPremiumAccount(action);
      case PURCHASE:
        return new Purchase(action);
      case WATCH:
        return new Watch(action);
      case LIKE:
        return new Like(action);
      case RATE:
        return new Rate(action);
      default:
        return null;
    }
  }
}
