package input.user;

public final class Notification {
  private String movieName;
  private String message;

  public Notification(final String movieName, final String message) {
    this.movieName = movieName;
    this.message = message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public void setMovieName(final String movieName) {
    this.movieName = movieName;
  }

  public String getMessage() {
    return message;
  }

  public String getMovieName() {
    return movieName;
  }
}
