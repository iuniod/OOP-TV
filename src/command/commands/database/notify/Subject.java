package command.commands.database.notify;

import input.movie.Movie;
import input.user.User;

public interface Subject {
  /**
   * Register an observer to the subject.
   *
   * @param genre the genre of the movie.
   * @param user the user to be registered to the genre.
   */
  void attach(String genre, User user);

  /**
   * Notify all the observers of the subject.
   *
   * @param movie the movie to be notified.
   * @param type the type of the notification.
   */
  void notifyAllObservers(Movie movie, String type);
}
