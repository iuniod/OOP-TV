package input.action.filters;

public final class Sort {
  private String rating = null;
  private String duration = null;

  public Sort() {
  }

  public void setRating(final String rating) {
    this.rating = rating;
  }

  public void setDuration(final String duration) {
    this.duration = duration;
  }

  public String getRating() {
    return rating;
  }

  public String getDuration() {
    return duration;
  }
}
