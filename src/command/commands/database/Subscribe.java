package command.commands.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.Command;
import database.Database;
import input.action.Action;
import input.movie.Movie;
import input.user.User;
import output.OutputFactory;

import java.io.IOException;
import java.util.Objects;

import static database.Constants.ERROR;
import static database.Constants.SEE_DETAILS;

public final class Subscribe extends OutputFactory implements Command {
  private final Action action;

  public Subscribe(final Action action) {
    this.action = action;
  }

  @Override
  public boolean isExecutable() {
    if (!Database.getInstance().getCurrentPage().equalsIgnoreCase(SEE_DETAILS)) {
      return false;
    }

    User currentUser = Database.getInstance().getCurrentUser();

    if (currentUser.getSubscribedGenres().contains(action.getSubscribedGenre())) {
      return false;
    }

    Movie currentMovie = Database.getInstance().getCurrentMovie();

    return currentMovie.getGenres().contains(action.getSubscribedGenre());
  }

  @Override
  public void executeError(final ObjectMapper mapper,
                           final ArrayNode arrayNode) throws IOException {
    Objects.requireNonNull(getOutput(ERROR, action)).write(mapper, arrayNode);
  }

  @Override
  public void executeSuccess(final ObjectMapper mapper,
                             final ArrayNode arrayNode) throws IOException {
    String genre = action.getSubscribedGenre();
    User currentUser = Database.getInstance().getCurrentUser();

    Database.getInstance().getCurrentUser().addSubscribedGenre(genre);
    Database.getInstance().getNotifications().attach(genre, currentUser);
}
}
