package command.commands.features;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import input.user.Credential;
import output.OutputFactory;

import java.io.IOException;
import java.util.Objects;

import static database.Constants.*;

public final class Login extends OutputFactory implements Command {
  private final Action action;

  public Login(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    if (!database.getCurrentPage().equalsIgnoreCase(LOGIN)) {
      return false;
    }

    if (!database.getFeatureWorkFlow().get(LOGIN).contains(action.getFeature().toUpperCase())) {
      return false;
    }

    Credential credentials = action.getCredentials();
    if (credentials == null || !database.containsUser(credentials)) {
      return false;
    }

    return database.verifyPassword(credentials);
  }

  @Override
  public void executeSuccess(final ObjectMapper mapper,
                             final ArrayNode arrayNode) throws IOException {
    Database database = Database.getInstance();
    database.setCurrentUser(action.getCredentials());
    Objects.requireNonNull(getOutput(SUCCESS, action)).write(mapper, arrayNode);
    Database.getInstance().setCurrentPage(HOMEPAGEAUTENTIFICAT);
    Action pushAction = new Action();
    pushAction.setPage(HOMEPAGEAUTENTIFICAT);
    Database.getInstance().pushPageToStack(pushAction);
  }

  @Override
  public void executeError(final ObjectMapper mapper,
                           final ArrayNode arrayNode) throws IOException {
    Objects.requireNonNull(getOutput(ERROR, action)).write(mapper, arrayNode);
//  if there was an error in login, go back to HOMEPAGENEAUTENTIFICAT
    if (Database.getInstance().getCurrentPage().equalsIgnoreCase(LOGIN)) {
      Database.getInstance().setCurrentPage(HOMEPAGENEAUTENTIFICAT);
    }
  }
}
