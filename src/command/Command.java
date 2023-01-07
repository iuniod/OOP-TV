package command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;

public interface Command {
  /**
   * Verifies that the command is valid.
   *
   * @return true if the command is valid, false otherwise.
   */
  boolean isExecutable();

  /**
   * Executes the command if it is not valid and writes the result to the given file.
   **/
  void executeError(ObjectMapper mapper, ArrayNode arrayNode) throws IOException;

  /**
   * Executes the command if it is valid and writes the result to the given file.
   **/
  void executeSuccess(ObjectMapper mapper, ArrayNode arrayNode) throws IOException;
}
