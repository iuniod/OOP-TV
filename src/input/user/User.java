package input.user;

import input.movie.Movie;

import java.util.ArrayList;

public class User {
  private Credential credentials;
  private int tokenAccount = 0;
  private int numFreePremiumMovies = 15;
  private ArrayList<Movie> purchasedMovies = new ArrayList<>();
  private ArrayList<Movie> watchedMovies = new ArrayList<>();
  private ArrayList<Movie> likedMovies = new ArrayList<>();
  private ArrayList<Movie> ratedMovies = new ArrayList<>();

  public User() {
  }

  public User(final Credential credentials) {
    this.credentials = credentials;
  }

  public void setCredentials(final Credential credentials) {
    this.credentials = credentials;
  }

  public void setTokenAccount(final int tokenAccount) {
    this.tokenAccount = tokenAccount;
  }

  public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
    this.numFreePremiumMovies = numFreePremiumMovies;
  }

  public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
    this.purchasedMovies = purchasedMovies;
  }

  public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
    this.watchedMovies = watchedMovies;
  }

  public void setLikedMovies(final ArrayList<Movie> likedMovies) {
    this.likedMovies = likedMovies;
  }

  public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
    this.ratedMovies = ratedMovies;
  }

  public Credential getCredentials() {
    return credentials;
  }

  public String getName() {
    if (credentials == null) {
      return "";
    }
    return credentials.getName();
  }

  public String getCountry() {
    if (credentials == null) {
      return "";
    }
    return credentials.getCountry();
  }

  public int getTokenAccount() {
    return tokenAccount;
  }

  public int getNumFreePremiumMovies() {
    return numFreePremiumMovies;
  }

  public ArrayList<Movie> getPurchasedMovies() {
    return purchasedMovies;
  }

  public ArrayList<Movie> getWatchedMovies() {
    return watchedMovies;
  }

  public ArrayList<Movie> getLikedMovies() {
    return likedMovies;
  }

  public ArrayList<Movie> getRatedMovies() {
    return ratedMovies;
  }
}
