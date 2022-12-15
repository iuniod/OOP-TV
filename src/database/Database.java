package database;

import input.InputFormat;
import input.movie.Movie;
import input.user.Credential;
import input.user.User;

import java.util.ArrayList;
import java.util.Map;

public class Database {
  private ArrayList<User> users;
  private ArrayList<Movie> movies;
  private String currentPage;
  private User currentUser;
  private final PageWorkFlow pageWorkFlow;

  private Database() {
    users = new ArrayList<>();
    movies = new ArrayList<>();
    currentPage = "HOMEPAGENEAUTENTIFICAT";
    currentUser = new User();
    pageWorkFlow = PageWorkFlow.getInstance();
  }

  private static final Database instance = new Database();

  public static Database getInstance() {
    return instance;
  }

  public void setDatabase(final InputFormat inputFormat) {
    users = inputFormat.getUsers();
    movies = inputFormat.getMovies();
  }

  public void setCurrentPage(final String currentPage) {
    this.currentPage = currentPage;
  }

  public void setCurrentUser(final Credential credentials) {
    for (User user : users) {
      if (user.getCredentials().equals(credentials)) {
        currentUser = user;
        return;
      }
    }

    currentUser = null;
  }

  public void setMovies(final ArrayList<Movie> movies) {
    this.movies = movies;
  }

  public ArrayList<User> getUsers() {
    return users;
  }

  public ArrayList<Movie> getMovies() {
    return movies;
  }

  public String getCurrentPage() {
    return currentPage;
  }

  public User getCurrentUser() {
    return currentUser;
  }

  public Map<String, ArrayList<String>> getPageWorkFlow() {
    return pageWorkFlow.getPageWorkFlow();
  }

  public boolean containsUser(final Credential credentials) {
    for (User u : users) {
      if (u.getCredentials().getName().equals(credentials.getName()))
        return true;
    }
    return false;
  }

  public boolean verifyPassword(final Credential credentials) {
    for (User u : users) {
      if (u.getCredentials().equals(credentials))
        return true;
    }
    return false;
  }

  public void addUser(User user) {
    users.add(user);
  }
}
