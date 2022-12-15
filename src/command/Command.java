package command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;

public interface Command {
  boolean isExecutable();
  void executeError(ObjectMapper mapper, ArrayNode arrayNode, File output) throws IOException;
  void executeSuccess(ObjectMapper mapper, ArrayNode arrayNode, File output) throws IOException;
}
