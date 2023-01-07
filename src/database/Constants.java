package database;

public final class Constants {
  private Constants() {
  }

//  type of action
  public static final String ON_PAGE = "on page";
  public static final String CHANGE_PAGE = "change page";
  public static final String BACK = "back";
  public static final String DATABASE = "database";

//  name of pages
  public static final String HOMEPAGENEAUTENTIFICAT = "HOMEPAGENEAUTENTIFICAT";
  public static final String LOGIN = "LOGIN";
  public static final String REGISTER = "REGISTER";
  public static final String HOMEPAGEAUTENTIFICAT = "HOMEPAGEAUTENTIFICAT";
  public static final String LOGOUT = "LOGOUT";
  public static final String MOVIES = "MOVIES";
  public static final String UPGRADES = "UPGRADES";
  public static final String SEE_DETAILS = "SEE DETAILS";
  public static final String SUBSCRIBE = "SUBSCRIBE";

//  name of features
  public static final String SEARCH = "SEARCH";
  public static final String FILTER = "FILTER";
  public static final String PURCHASE = "PURCHASE";
  public static final String WATCH = "WATCH";
  public static final String LIKE = "LIKE";
  public static final String RATE = "RATE";
  public static final String BUY_TOKENS = "BUY TOKENS";
  public static final String BUY_PREMIUM_ACCOUNT = "BUY PREMIUM ACCOUNT";
  public static final String ADD = "ADD";
  public static final String DELETE = "DELETE";

//  output messages
  public static final String OUTPUT_ERROR = "Error";
  public static final String ERROR = "ERROR";
  public static final String SUCCESS = "SUCCESS";
  public static final String INVALID_COMMAND_TYPE = "Invalid command type";
  public static final String INVALID_COMMAND = "Invalid command";
  public static final String NO_RECOMMENDATION = "No recommendation";
  public static final String RECOMMENDATION = "Recommendation";

//  types of accounts
  public static final String PREMIUM = "premium";
  public static final String STANDARD = "standard";

//  useful strings
  public static final String INCREASING = "increasing";

  public static final String ZERO_DECIMAL = "0.00";


  public static final int MAX_FREE_PREMIUM_MOVIES = 15;
  public static final int MAX_RATE = 5;
  public static final int PRICE_PREMIUM = 10;
}
