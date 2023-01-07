package database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.movie.Movie;
import input.user.User;
import output.Output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

import static database.Constants.*;

class Genre {
  private final String genre;
  private int likes;

  Genre(final String genre) {
    this.genre = genre;
  }

  public void addLike(final int numLikes) {
    likes += numLikes;
  }

  public String getGenre() {
    return genre;
  }

  public int getLikes() {
    return likes;
  }
}

public final class Recomandation implements Output {
  private ArrayList<Genre> genres = new ArrayList<>();
  private ArrayList<Movie> movies = new ArrayList<>();

  /** Recommends a movie to a user based on the most liked genre.*/
  public void recommend(final ObjectMapper mapper, final ArrayNode arrayNode) throws IOException {
    User user = Database.getInstance().getCurrentUser();
    if (user != null && user.getCredentials().getAccountType().equalsIgnoreCase(PREMIUM)) {
      write(mapper, arrayNode);
    }
  }

  @Override
  public String error() {
    return null;
  }

  @Override
  public User currentUser() {
    Database database = Database.getInstance();
    User user = database.getCurrentUser();
    sortGenres(user);

    if (genres.isEmpty()) {
      user.addNotification(NO_RECOMMENDATION, RECOMMENDATION);
      return user;
    }

    sortMovies();
    if (movies.isEmpty()) {
      user.addNotification(NO_RECOMMENDATION, RECOMMENDATION);
      return user;
    }

    for (Genre genre : genres) {
      for (Movie movie : movies) {
        if (movie.getGenres().contains(genre.getGenre())) {
          user.addNotification(movie.getName(), RECOMMENDATION);
          return user;
        }
      }
    }

    user.addNotification(NO_RECOMMENDATION, RECOMMENDATION);
    return user;
  }

  @Override
  public ArrayList<Movie> currentMoviesList() {
    return null;
  }

  private void sortGenres(final User user) {
    //    add all genres one time in the genres list using a HashSet
    HashSet<String> genresSet = new HashSet<>();
    Database.getInstance().getMovies().stream().flatMap(movie -> movie.getGenres().stream())
        .forEach(genresSet::add);
    genresSet.forEach(genre -> genres.add(new Genre(genre)));

//    count the number of likes for each genre from each liked movie
    user.getLikedMovies().forEach(movie -> movie.getGenres()
                                  .forEach(genre -> genres.stream()
                                  .filter(g -> g.getGenre().equals(genre)).findAny()
                                  .orElse(null).addLike(movie.getNumLikes())));


//    sort lexicographically
    genres.sort(Comparator.comparing(Genre::getGenre));
//    sort by likes descending
    genres.sort(Comparator.comparing(Genre::getLikes).reversed());

//    remove genres with 0 likes
    ArrayList<Genre> genresToRemove = new ArrayList<>();
    genres.stream().filter(genre -> genre.getLikes() == 0).forEach(genresToRemove::add);
    genres.removeAll(genresToRemove);
  }

  private void sortMovies() {
    Database database = Database.getInstance();
    movies = database.getMovies().stream().filter(
        movie -> !movie.getCountriesBanned().contains(database.getCurrentUser().getCredentials()
                 .getCountry())).collect(Collectors.toCollection(java.util.ArrayList::new));
//    remove movies that the user has already seen
    movies.removeAll(database.getCurrentUser().getWatchedMovies());

//    sort according to the number of likes descending
    movies.sort((o1, o2) -> o2.getNumLikes() - o1.getNumLikes());
  }
}
