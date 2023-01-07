package command.commands.database.notify;

import input.movie.Movie;
import input.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public final class ObserverNotification implements Subject {
  private final HashMap<String, ArrayList<User>> observers = new HashMap<>();

  @Override
  public void attach(final String genre, final User user) {
    if (!observers.containsKey(genre)) {
      observers.put(genre, new ArrayList<>());
    }
    observers.get(genre).add(user);
  }

  @Override
  public void notifyAllObservers(final Movie movie, final String type) {
    ArrayList<String> movieGenres = movie.getGenres();
    HashSet<User> users = new HashSet<>();

    movieGenres.forEach(genre -> {
      if (observers.containsKey(genre)) {
        users.addAll(observers.get(genre));
      }
    });

    users.forEach(user -> user.addNotification(movie.getName(), type));
  }
}
