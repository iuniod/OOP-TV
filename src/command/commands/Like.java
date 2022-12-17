package command.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import output.OutputFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class Like extends OutputFactory implements Command {
  private final Action action;

  public Like(final Action action) {
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
    if (action.getMovie() != null &&
            !database.getCurrentMovie().getName().equalsIgnoreCase(action.getMovie())) {
      return false;
    }

    return database.getCurrentUser().getWatchedMovies().contains(database.getCurrentMovie());
  }

  @Override
  public void executeError(final ObjectMapper mapper,
                           final ArrayNode arrayNode, final File output) throws IOException {
    Objects.requireNonNull(getOutput("ERROR", action)).write(mapper, arrayNode, output);
  }

  @Override
  public void executeSuccess(final ObjectMapper mapper,
                             final ArrayNode arrayNode, final File output) throws IOException {
    Database.getInstance().getCurrentUser().addLikedMovie(Database.getInstance().getCurrentMovie());
    Database.getInstance().getCurrentMovie().setNumLikes(Database.getInstance().getCurrentMovie().getNumLikes() + 1);
    Objects.requireNonNull(getOutput("SUCCESS", action)).write(mapper, arrayNode, output);
  }
}
