package command.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import input.user.User;
import output.OutputFactory;

import java.io.IOException;
import java.util.Objects;

import static database.Constants.*;

public final class BuyPremiumAccount extends OutputFactory implements Command {
  private final Action action;

  public BuyPremiumAccount(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    if (!database.getCurrentPage().equalsIgnoreCase(UPGRADES)) {
      return false;
    }

    String feature = action.getFeature().toUpperCase();
    if (!database.getFeatureWorkFlow().get(UPGRADES).contains(feature)) {
      return false;
    }

    User user = database.getCurrentUser();
    return user.getTokensCount() >= action.getCount();
  }

  @Override
  public void executeError(final ObjectMapper mapper,
                           final ArrayNode arrayNode) throws IOException {
    Objects.requireNonNull(getOutput(ERROR, action)).write(mapper, arrayNode);
  }

  @Override
  public void executeSuccess(final ObjectMapper mapper,
                             final ArrayNode arrayNode) throws IOException {
    Database database = Database.getInstance();
    User user = database.getCurrentUser();
    user.setTokensCount(user.getTokensCount() - PRICE_PREMIUM);
    user.getCredentials().setAccountType(PREMIUM);
  }
}
