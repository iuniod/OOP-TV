package input.action;

import input.action.filters.Filter;
import input.user.Credential;

public class Action {
  private String type;
  private String page;
  private String movie;
  private String feature;
  private Credential credentials;
  private String startsWith;
  private Filter filters;
  private int count;
  private int rate;

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

  public void setRate(final int rate) {
    this.rate = rate;
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

  public String toString() {
    return "ActionInputData{" +
            "type='" + type + '\'' +
            ", page='" + page + '\'' +
            ", feature='" + feature + '\'';
  }
}
