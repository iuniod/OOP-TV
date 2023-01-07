package command.commands.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import input.movie.Movie;
import output.OutputFactory;

import java.io.IOException;
import java.util.Objects;

import static database.Constants.ADD;
import static database.Constants.ERROR;

public final class Add extends OutputFactory implements Command {
  private final Action action;

  public Add(final Action action) {
    this.action = action;
  }
  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    return !Movie.isInList(database.getMovies(), action.getAddedMovie().getName());
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
    database.getMovies().add(action.getAddedMovie());

    Database.getInstance().getNotifications().notifyAllObservers(action.getAddedMovie(), ADD);
  }

}
