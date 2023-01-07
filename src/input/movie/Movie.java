package input.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import database.Database;
import input.user.User;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static database.Constants.ZERO_DECIMAL;

public final class Movie {
  private String name;
  private int year;
  private int duration;
  private ArrayList<String> genres;
  private ArrayList<String> actors;
  private ArrayList<String> countriesBanned;
  private int numLikes = 0;
  private double rating = 0;
  private int numRatings = 0;

  @JsonIgnore
  private final HashMap<User, Double> ratings = new HashMap<>();

  public Movie() {
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setYear(final int year) {
    this.year = year;
  }

  public void setDuration(final int duration) {
    this.duration = duration;
  }

  public void setGenres(final ArrayList<String> genres) {
    this.genres = genres;
  }

  public void setActors(final ArrayList<String> actors) {
    this.actors = actors;
  }

  public void setCountriesBanned(final ArrayList<String> countriesBanned) {
    this.countriesBanned = countriesBanned;
  }

  public void setNumLikes(final int numLikes) {
    this.numLikes = numLikes;
  }

  public void setRating(final double rating) {
    this.rating = rating;
  }

  public void setNumRatings(final int numRatings) {
    this.numRatings = numRatings;
  }

  public String getName() {
    return name;
  }

  public String getYear() {
    return String.valueOf(year);
  }

  public int getDuration() {
    return duration;
  }

  public ArrayList<String> getGenres() {
    return genres;
  }

  public ArrayList<String> getActors() {
    return actors;
  }

  public ArrayList<String> getCountriesBanned() {
    return countriesBanned;
  }

  public int getNumLikes() {
    return numLikes;
  }

  public double getRating() {
    return rating;
  }

  public int getNumRatings() {
    return numRatings;
  }

  /** Add a rating to the movie and update the rating and number of ratings */
  public void addRating(final int rate) {
    ratings.put(Database.getInstance().getCurrentUser(), (double) rate);

    numRatings++;

    rating = 0;
    for (Double rateValue : ratings.values()) {
      rating += rateValue;
    }

    rating /= ratings.size();
    new DecimalFormat(ZERO_DECIMAL).format(rating);
  }

  /** Update a rating */
  public void updateRating(final int rate) {
    ratings.put(Database.getInstance().getCurrentUser(), (double) rate);

    rating = 0;
    for (Double rateValue : ratings.values()) {
      rating += rateValue;
    }

    rating /= ratings.size();
    new DecimalFormat(ZERO_DECIMAL).format(rating);
  }

  /** Check if a movie is in a list of movies. */
  public static boolean isInList(final ArrayList<Movie> list, final String movie) {
    for (Movie movie1 : list) {
      if (movie1.getName().equalsIgnoreCase(movie)) {
        return true;
      }
    }

    return false;
  }
}
