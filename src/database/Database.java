package database;

import input.InputFormat;
import input.action.Action;
import input.movie.Movie;
import input.user.Credential;
import command.commands.database.notify.ObserverNotification;
import input.user.User;

import java.util.ArrayList;
import java.util.Map;
import static database.Constants.HOMEPAGENEAUTENTIFICAT;

public final class Database {
  private ArrayList<User> users;
  private ArrayList<Movie> movies;
  private String currentPage;
  private User currentUser;
  private Movie currentMovie;
  private ArrayList<Movie> currentMovieList;
  private final PageWorkFlow pageWorkFlow;
  private ArrayList<Action> stackOfPages;

  private ObserverNotification notifications;

  private Database() {
    users = new ArrayList<>();
    movies = new ArrayList<>();
    currentMovieList = new ArrayList<>();
    currentPage = HOMEPAGENEAUTENTIFICAT;
    currentUser = new User();
    pageWorkFlow = PageWorkFlow.getInstance();
    stackOfPages = new ArrayList<>();
    notifications = new ObserverNotification();
  }

  private static final Database INSTANCE = new Database();

  public static Database getInstance() {
    return INSTANCE;
  }

  /**
   * Initialize the database with the input data  for each test
   */
  public void setDatabase(final InputFormat inputFormat) {
    users = inputFormat.getUsers();
    movies = inputFormat.getMovies();
    setCurrentPage("HOMEPAGENEAUTENTIFICAT");
    setCurrentUser(null);
    stackOfPages.clear();
  }

  public void setCurrentPage(final String currentPage) {
    this.currentPage = currentPage;
  }

  /**
   * Set the current user if the user is registered, otherwise set the current user to null
   */
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

  /**
   * Set the current movie if the movie is in the database, otherwise set the current movie to
   * null
   */
  public void setCurrentMovie(final String movie) {
    for (Movie movie1 : movies) {
      if (movie1.getName().equals(movie)) {
        currentMovie = movie1;
        return;
      }
    }

    currentMovie = null;
  }

  public void setCurrentMovieList(final ArrayList<Movie> currentMovieList) {
    this.currentMovieList = currentMovieList;
  }

  public void setNotifications(final ObserverNotification notifications) {
    this.notifications = notifications;
  }

  public ArrayList<User> getUsers() {
    return users;
  }

  public ArrayList<Movie> getMovies() {
    return movies;
  }

  /** Return a list of movies names for the current user that are not banned in his country.*/
  public ArrayList<String> getMoviesTitles() {
    ArrayList<String> moviesTitles = new ArrayList<>();

    String userCountry = currentUser.getCredentials().getCountry();
    currentMovieList.stream().filter(movie -> !movie.getCountriesBanned().contains(userCountry))
        .forEach(movie -> moviesTitles.add(movie.getName()));

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

  public Movie getCurrentMovie() {
    return currentMovie;
  }

  public ArrayList<Movie> getCurrentMovieList() {
    return currentMovieList;
  }

  public ObserverNotification getNotifications() {
    return notifications;
  }

  /** Return true it the user is registered with the specified credentials.*/
  public boolean containsUser(final Credential credentials) {
    for (User user : users) {
      if (user.getCredentials().getName().equals(credentials.getName())) {
        return true;
      }
    }

    return false;
  }

  /** Verify if the user is registered and if the password is correct. */
  public boolean verifyPassword(final Credential credentials) {
    for (User user : users) {
      if (user.getCredentials().equals(credentials)) {
        return true;
      }
    }

    return false;
  }

  /** Add a new user to the database. */
  public void addUser(final User user) {
    users.add(user);
  }

  /** Add a new page to the stack. */
  public void pushPageToStack(final Action action) {
    if (!stackOfPages.isEmpty() && peekPageFromStack().getPage().equals(action.getPage())) {
      return;
    }
    stackOfPages.add(action);
  }

  /** Remove the last page from the stack. */
  public void popPageFromStack() {
    stackOfPages.remove(stackOfPages.size() - 1);
  }

  /** Return the last page from the stack. */
  public Action peekPageFromStack() {
    return stackOfPages.get(stackOfPages.size() - 1);
  }

  /** Check if there is at least 2 pages in the stack (current one and previous one). */
  public boolean hasStackPreviousPage() {
    return stackOfPages.size() > 1;
  }

  /** Clear the stack of pages. */
  public void clearStack() {
    stackOfPages.clear();
  }

  public ArrayList<Action> getStackOfPages() {
    return stackOfPages;
  }
}
