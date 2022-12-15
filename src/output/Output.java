package output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.movie.Movie;
import input.user.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface Output {
  String error();
  User currentUser();

  ArrayList<Movie> currentMoviesList();

  default void write(ObjectMapper mapper, ArrayNode arrayNode, File output) throws IOException {
    OutputFormat outputFormat = new OutputFormat(error(), currentMoviesList(), currentUser());
    arrayNode.add(mapper.valueToTree(outputFormat));
  }
}
