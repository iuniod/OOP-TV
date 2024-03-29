package command.commands.features;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import input.movie.Movie;
import output.OutputFactory;

import java.io.IOException;
import java.util.Objects;

import static database.Constants.*;

public final class Rate extends OutputFactory implements Command {
  private final Action action;

  public Rate(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    Database database = Database.getInstance();
    if (!database.getCurrentPage().equalsIgnoreCase(SEE_DETAILS)) {
      return false;
    }

    String feature = action.getFeature().toUpperCase();
    if (!database.getFeatureWorkFlow().get(SEE_DETAILS).contains(feature)) {
      return false;
    }
    if (action.getMovie() != null
            && !database.getCurrentMovie().getName().equalsIgnoreCase(action.getMovie())) {
      return false;
    }

    if (action.getRate() > MAX_RATE || action.getRate() <= 0) {
      return false;
    }

    return database.getCurrentUser().getWatchedMovies().contains(database.getCurrentMovie());
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
    if (!Movie.isInList(database.getCurrentUser().getRatedMovies(),
        database.getCurrentMovie().getName())) {
      database.getCurrentMovie().addRating(action.getRate());
    } else {
      database.getCurrentMovie().updateRating(action.getRate());
    }
    database.getCurrentUser().addRatedMovie(Database.getInstance().getCurrentMovie());
    Objects.requireNonNull(getOutput(SUCCESS, action)).write(mapper, arrayNode);
  }
}
