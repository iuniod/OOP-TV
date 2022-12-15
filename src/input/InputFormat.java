package input;

import input.action.Action;
import input.movie.Movie;
import input.user.User;

import java.util.ArrayList;

public class InputFormat {
  private ArrayList<User> users = new ArrayList<>();
  private ArrayList<Movie> movies = new ArrayList<>();
  private ArrayList<Action> actions = new ArrayList<>();

  public InputFormat() {
  }

  public void setUsers(final ArrayList<User> users) {
    this.users = users;
  }

  public void setMovies(final ArrayList<Movie> movies) {
    this.movies = movies;
  }

  public void setActions(final ArrayList<Action> actions) {
    this.actions = actions;
  }

  public ArrayList<User> getUsers() {
    return users;
  }

  public ArrayList<Movie> getMovies() {
    return movies;
  }

  public ArrayList<Action> getActions() {
    return actions;
  }

}
