package command.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import input.user.User;
import output.OutputFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class BuyTokens extends OutputFactory implements Command {
  private final Action action;

  public BuyTokens(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    if (!database.getCurrentPage().equalsIgnoreCase("UPGRADES")) {
      return false;
    }

    String feature = action.getFeature().toUpperCase();
    if (!database.getFeatureWorkFlow().get("UPGRADES").contains(feature)) {
      return false;
    }

    User user = database.getCurrentUser();
    return user.getCredentials().findBalanceCount() >= action.getCount();
  }

  @Override
  public void executeError(final ObjectMapper mapper,
                           final ArrayNode arrayNode, final File output) throws IOException {
    Objects.requireNonNull(getOutput("ERROR", action)).write(mapper, arrayNode, output);
  }

  @Override
  public void executeSuccess(final ObjectMapper mapper,
                             final ArrayNode arrayNode, final File output) throws IOException {
    Database database = Database.getInstance();
    User user = database.getCurrentUser();
    user.setTokensCount(user.getTokensCount() + action.getCount());
    user.getCredentials().setBalance(user.getCredentials().findBalanceCount() - action.getCount());
  }
}
