package command.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import input.user.Credential;
import input.user.User;
import output.OutputFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class Register extends OutputFactory implements Command {
  private final Action action;

  public Register(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    if (!database.getCurrentPage().equalsIgnoreCase("REGISTER")) {
      return false;
    }

    if (!database.getFeatureWorkFlow().get("REGISTER")
             .contains(action.getFeature().toUpperCase())) {
      return false;
    }

    Credential credentials = action.getCredentials();

    return credentials != null && !database.containsUser(credentials);
  }

  @Override
  public void executeSuccess(final ObjectMapper mapper,
                             final ArrayNode arrayNode, final File output) throws IOException {
    Database database = Database.getInstance();
    Credential credentials = action.getCredentials();
    database.addUser(new User(credentials));
    database.setCurrentUser(credentials);
    Objects.requireNonNull(getOutput("SUCCESS", action)).write(mapper, arrayNode, output);
    Database.getInstance().setCurrentPage("HOMEPAGEAUTENTIFICAT");
  }

  @Override
  public void executeError(final ObjectMapper mapper,
                           final ArrayNode arrayNode, final File output) throws IOException {
    Objects.requireNonNull(getOutput("ERROR", action)).write(mapper, arrayNode, output);
//  if there was an error in register, we go back to HOMEPAGENEAUTENTIFICAT only if we were in reg
    if (Database.getInstance().getCurrentPage().toUpperCase().equals("REGISTER")) {
      Database.getInstance().setCurrentPage("HOMEPAGENEAUTENTIFICAT");
    }
  }
}
