package database;

import input.InputFormat;
import input.movie.Movie;
import input.user.Credential;
import input.user.User;

import java.util.ArrayList;
import java.util.Map;

public final class Database {
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

  private static final Database INSTANCE = new Database();

  public static Database getInstance() {
    return INSTANCE;
  }

  /** Initialize the database with the input data  for each test */
  public void setDatabase(final InputFormat inputFormat) {
    users = inputFormat.getUsers();
    movies = inputFormat.getMovies();
    setCurrentPage("HOMEPAGENEAUTENTIFICAT");
    setCurrentUser(null);
  }

  public void setCurrentPage(final String currentPage) {
    this.currentPage = currentPage;
  }

  /** Set the current user if the user is registered, otherwise set the current user to null */
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

  /** Return a list of movies names for the current user that are not banned in his country */
  public ArrayList<String> getMoviesTitles() {
    ArrayList<String> moviesTitles = new ArrayList<>();

    for (Movie movie : movies) {
      if (!(movie.getCountriesBanned().contains(currentUser.getCredentials().getCountry()))) {
        moviesTitles.add(movie.getName());
      }
    }

    return moviesTitles;
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

  public Map<String, ArrayList<String>> getFeatureWorkFlow() {
    return pageWorkFlow.getFeatureWorkFlow();
  }

  /** Return true it the user is registered with the specified credentials */
  public boolean containsUser(final Credential credentials) {
    for (User user : users) {
      if (user.getCredentials().getName().equals(credentials.getName())) {
        return true;
      }
    }

    return false;
  }

  /** Verify if the user is registered and if the password is correct */
  public boolean verifyPassword(final Credential credentials) {
    for (User user : users) {
      if (user.getCredentials().equals(credentials)) {
        return true;
      }
    }

    return false;
  }

  /** Add a new user to the database */
  public void addUser(final User user) {
    users.add(user);
  }
}
