package output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.movie.Movie;
import input.user.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface Output {
  /** Returns the error message. */
  String error();
  /** Returns the current user. */
  User currentUser();

  /** Returns the current movies list. */
  ArrayList<Movie> currentMoviesList();

  /** Writes the output in a JSON file. */
  default void write(ObjectMapper mapper, ArrayNode arrayNode, File output) throws IOException {
    OutputFormat outputFormat = new OutputFormat(error(), currentMoviesList(), currentUser());
    arrayNode.add(mapper.valueToTree(outputFormat));
  }
}
