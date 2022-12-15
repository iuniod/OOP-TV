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
    public static void main(final String[] args) throws IOException {
        final File input = new File(args[0]);
         File output = new File(args[1]);
        String test = args[0].substring(args[0].lastIndexOf('/') + 1, args[0].lastIndexOf('.'));
         File myOutput = new File("checker/resources/out/" + test + ".json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        ArrayNode arrayNode = mapper.createArrayNode();

//      read the input file
        System.out.println("Reading input file..." + test);
        InputFormat inputFormat = mapper.readValue(input, InputFormat.class);
        Database database = Database.getInstance();
        database.setDatabase(inputFormat);
        Database.getInstance().setCurrentPage("HomepageNeautentificat");
        Database.getInstance().setCurrentUser(null);
        Database.getInstance().setMovies(null);
//        iterate through each command action
        for (Action action : inputFormat.getActions()) {
            AbstractFactory factory = CommandFactory.getAction(action.getType());
            if (factory == null) {
                //NU AVEM NICI ON PAGE NIGI CHANGE PAGE CA COMENZI
                continue;
            } else {
                //AVEM ON PAGE SAU CHANGE PAGE CA COMENZI
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
