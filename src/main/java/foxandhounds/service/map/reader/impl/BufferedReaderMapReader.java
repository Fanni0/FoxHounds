package foxandhounds.service.map.reader.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import foxandhounds.service.exception.MapReadException;
import foxandhounds.service.map.reader.MapReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BufferedReaderMapReader implements MapReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(BufferedReaderMapReader.class);

    private final BufferedReader reader;

    public BufferedReaderMapReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public List<String> read() throws MapReadException {
        LOGGER.info("Reading the map");

        String line;
        List<String> result = new ArrayList<>();

        try {
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to read map", e);
            throw new MapReadException("Failed to read map");
        }

        return result;
    }
}
