package command.commands.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import input.movie.Movie;
import output.OutputFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static database.Constants.*;

public final class Delete extends OutputFactory implements Command {
  private final Action action;

  public Delete(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    return Movie.isInList(database.getMovies(), action.getDeletedMovie());
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

    ArrayList<Movie> erasedMovies = new ArrayList<>();
    for (Movie movie : database.getMovies()) {
      if (movie.getName().equals(action.getDeletedMovie())) {
        database.getNotifications().notifyAllObservers(movie, DELETE);
        erasedMovies.add(movie);
      }
    }

    database.getMovies().removeAll(erasedMovies);
  }
}
