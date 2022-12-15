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

public class Register extends OutputFactory implements Command {
  private Action action;

  public Register(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    if (!database.getCurrentPage().equals("Register")) {
      return false;
    }

    Credential credentials = action.getCredentials();
    return credentials != null && !database.containsUser(credentials);
  }

  @Override
  public void executeSuccess(ObjectMapper mapper, ArrayNode arrayNode, File output) {
    Database database = Database.getInstance();
    User user = new User(action.getCredentials());
    database.addUser(user);
    database.setCurrentUser(action.getCredentials());
    Database.getInstance().setCurrentPage("HomepageAutentificat");
  }

  @Override
  public void executeError(ObjectMapper mapper, ArrayNode arrayNode, File output) throws IOException {
    getOutput("error").write(mapper, "error", arrayNode, output);
    Database.getInstance().setCurrentPage("HomepageNeautentificat");
  }
}
