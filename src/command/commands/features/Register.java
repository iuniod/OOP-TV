package command.commands.features;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import input.user.Credential;
import input.user.User;
import output.OutputFactory;

import java.io.IOException;
import java.util.Objects;

import static database.Constants.*;

public final class Register extends OutputFactory implements Command {
  private final Action action;

  public Register(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    if (!database.getCurrentPage().equalsIgnoreCase(REGISTER)) {
      return false;
    }

    if (!database.getFeatureWorkFlow().get(REGISTER)
             .contains(action.getFeature().toUpperCase())) {
      return false;
    }

    Credential credentials = action.getCredentials();

    return credentials != null && !database.containsUser(credentials);
  }

  @Override
  public void executeSuccess(final ObjectMapper mapper,
                             final ArrayNode arrayNode) throws IOException {
    Database database = Database.getInstance();
    Credential credentials = action.getCredentials();
    database.addUser(new User(credentials));
    database.setCurrentUser(credentials);
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
//  if there was an error in register, go back to HOMEPAGENEAUTENTIFICAT
    if (Database.getInstance().getCurrentPage().equalsIgnoreCase(REGISTER)) {
      Database.getInstance().setCurrentPage(HOMEPAGENEAUTENTIFICAT);
    }
  }
}
