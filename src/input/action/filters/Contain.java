package input.action.filters;

import java.util.ArrayList;

public class Contain {
  private ArrayList<String> actors = new ArrayList<>();
  private ArrayList<String> genre = new ArrayList<>();

  public Contain() {
  }

  public void setActors(final ArrayList<String> actors) {
    this.actors = actors;
  }

  public void setGenre(final ArrayList<String> genre) {
    this.genre = genre;
  }

  public ArrayList<String> getActors() {
    return actors;
  }

  public ArrayList<String> getGenre() {
    return genre;
  }
}
