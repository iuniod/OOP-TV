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

public final class Purchase extends OutputFactory implements Command {
  private final Action action;

  public Purchase(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    if (!database.getCurrentPage().equalsIgnoreCase("SEE DETAILS")) {
      return false;
    }

    String feature = action.getFeature().toUpperCase();
    if (!database.getFeatureWorkFlow().get("SEE DETAILS").contains(feature)) {
      return false;
    }

    if (action.getMovie() != null
            && !database.getCurrentMovie().getName().equalsIgnoreCase(action.getMovie())) {
      return false;
    }

    User user = database.getCurrentUser();
    switch (user.getCredentials().getAccountType()) {
      case "standard":
        return user.getTokensCount() >= 2;
      case "premium":
        return user.getNumFreePremiumMovies() != 0 || user.getTokensCount() >= 2;
      default:
        return false;
    }
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
    switch (user.getCredentials().getAccountType()) {
      case "standard":
        user.setTokensCount(user.getTokensCount() - 2);
        break;
      case "premium":
        if (user.getNumFreePremiumMovies() != 0) {
          user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
        } else {
          user.setTokensCount(user.getTokensCount() - 2);
        }
        break;
      default:
        break;
    }

    user.addPurchasedMovie(database.getCurrentMovie());
    Objects.requireNonNull(getOutput("SUCCESS", action)).write(mapper, arrayNode, output);
  }
}
