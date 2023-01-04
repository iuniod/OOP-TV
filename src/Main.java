import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import command.AbstractFactory;
import command.Command;
import command.CommandFactory;
import database.Database;
import input.InputFormat;
import input.action.Action;

import java.io.File;
import java.io.IOException;

import static database.Constants.*;

public final class Main {
  private Main() {
  }
  /**
   * Main method. Iterates through the input file and executes the commands.
   */
  public static void main(final String[] args) throws IOException {
    final File input = new File(args[0]);
    File output = new File(args[1]);
    ObjectMapper mapper = new ObjectMapper();
    mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    ArrayNode arrayNode = mapper.createArrayNode();

//      read the input file
    InputFormat inputFormat = mapper.readValue(input, InputFormat.class);
    Database database = Database.getInstance();
    database.setDatabase(inputFormat);
//        iterate through each command action
    for (Action action : inputFormat.getActions()) {
      AbstractFactory factory = CommandFactory.getAction(action.getType());
      if (factory == null) {
        System.out.println(INVALID_COMMAND_TYPE);
      } else {
        Command command = factory.getAction(action);
        if (command == null) {
          System.err.println(INVALID_COMMAND);
        } else {
          if (command.isExecutable()) {
            command.executeSuccess(mapper, arrayNode);
          } else {
            command.executeError(mapper, arrayNode);
          }
        }
      }
    }

    ObjectWriter objectWriter = mapper.writerWithDefaultPrettyPrinter();
    objectWriter.writeValue(output, arrayNode);
  }
}
