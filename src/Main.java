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

public final class Main {
    /** Main method. Iterates through the input file and executes the commands. */
    public static void main(final String[] args) throws IOException {
        final File input = new File(args[0]);
         File output = new File(args[1]);
        String test = args[0].substring(args[0].lastIndexOf("\\") + 1);
         File myOutput = new File("checker/resources/out/" + test);
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
                continue;
            } else {
                Command command = factory.getAction(action);
                if (command == null) {
                    continue;
                } else {
                    if (command.isExecutable()) {
                        command.executeSuccess(mapper, arrayNode, myOutput);
                    } else {
                        command.executeError(mapper, arrayNode, myOutput);
                    }
                }
            }
        }

        ObjectWriter objectWriter = mapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(output, arrayNode);
        objectWriter.writeValue(myOutput, arrayNode);
    }
}
