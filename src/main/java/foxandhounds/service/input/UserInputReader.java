package foxandhounds.service.input;

import java.io.BufferedReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserInputReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInputReader.class);

    private final BufferedReader reader;

    public UserInputReader(BufferedReader reader) {
        this.reader = reader;
    }

    public String readInput() {
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("Exception occurred while reading user input", e);
        }
        return input;
    }
}
