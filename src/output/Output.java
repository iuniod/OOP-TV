package output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import input.movie.Movie;
import input.user.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface Output {
  default User currentUser() {
    return Database.getInstance().getCurrentUser();
  }

  default ArrayList<Movie> currentMoviesList() {
    User user = currentUser();
    ArrayList<Movie> list = new ArrayList<Movie>();
    if (user == null) {
      return list;
    }

    for (Movie movie : Database.getInstance().getMovies())
      if(!movie.getCountriesBanned().contains(user.getCountry()))
        list.add(movie);

    return list;
  }

  default void write(ObjectMapper mapper, String error, ArrayNode arrayNode, File output) throws IOException {
    OutputFormat outputFormat = new OutputFormat(error, currentMoviesList(), currentUser());
    arrayNode.add(mapper.valueToTree(outputFormat));
  }
}
