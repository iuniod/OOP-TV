package input.action;

import input.action.filters.Filter;
import input.movie.Movie;
import input.user.Credential;

public final class Action {
  private String type;
  private String page;
  private String movie;
  private String feature;
  private Credential credentials;
  private String startsWith;
  private Filter filters;
  private int count;
  private int rate;
  private String subscribedGenre;

  private Movie addedMovie;

  private String deletedMovie;
  public Action() {
  }

  public void setType(final String type) {
    this.type = type;
  }

  public void setPage(final String page) {
    this.page = page;
  }

  public void setMovie(final String movie) {
    this.movie = movie;
  }

  public void setFeature(final String feature) {
    this.feature = feature;
  }

  public void setCredentials(final Credential credentials) {
    this.credentials = credentials;
  }

  public void setStartsWith(final String startsWith) {
    this.startsWith = startsWith;
  }

  public void setFilters(final Filter filters) {
    this.filters = filters;
  }

  public void setCount(final int count) {
    this.count = count;
  }

  public void setAddedMovie(final Movie addedMovie) {
    this.addedMovie = addedMovie;
  }

  public void setDeletedMovie(final String deletedMovie) {
    this.deletedMovie = deletedMovie;
  }

  public void setRate(final int rate) {
    this.rate = rate;
  }

  public void setSubscribedGenre(final String subscribedGenre) {
    this.subscribedGenre = subscribedGenre;
  }

  public String getType() {
    return type;
  }

  public String getPage() {
    return page;
  }

  public String getMovie() {
    return movie;
  }

  public String getFeature() {
    return feature;
  }

  public Credential getCredentials() {
    return credentials;
  }

  public String getStartsWith() {
    return startsWith;
  }

  public Filter getFilters() {
    return filters;
  }

  public int getCount() {
    return count;
  }

  public int getRate() {
    return rate;
  }

  public String getSubscribedGenre() {
    return subscribedGenre;
  }

  public Movie getAddedMovie() {
    return addedMovie;
  }

  public String getDeletedMovie() {
    return deletedMovie;
  }
}
