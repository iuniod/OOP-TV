package command.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import input.user.Credential;
import output.OutputFactory;

import java.io.File;
import java.io.IOException;

public class Login extends OutputFactory implements Command {
  private final Action action;
  private String error;

  public Login(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    if (!database.getCurrentPage().equals("Login")) {
      return false;
    }

    Credential credentials = action.getCredentials();
    if (credentials == null || !database.containsUser(credentials)) {
      return false;
    }

    return database.verifyPassword(credentials);
  }

  @Override
  public void executeSuccess(ObjectMapper mapper, ArrayNode arrayNode, File output) throws IOException {
    Database database = Database.getInstance();
    database.setCurrentUser(action.getCredentials());
    getOutput("success").write(mapper, null, arrayNode, output);
    Database.getInstance().setCurrentPage("HomepageAutentificat");
  }

  @Override
  public void executeError(ObjectMapper mapper, ArrayNode arrayNode, File output) throws IOException {
    getOutput("error").write(mapper, "error", arrayNode, output);
    Database.getInstance().setCurrentPage("HomepageNeautentificat");
  }
}
