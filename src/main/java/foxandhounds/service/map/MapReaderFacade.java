package service.map;

import java.util.List;

import model.MapVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.exception.MapParseException;
import service.exception.MapReadException;
import service.exception.MapValidationException;
import service.map.parser.MapParser;
import service.map.reader.MapReader;
import service.map.validation.MapValidator;

public class MapReaderFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapReaderFacade.class);

    private final MapReader mapReader;
    private final MapParser mapParser;
    private final MapValidator mapValidator;

    public MapReaderFacade(MapReader mapReader, MapParser mapParser, MapValidator mapValidator) {
        this.mapReader = mapReader;
        this.mapParser = mapParser;
        this.mapValidator = mapValidator;
    }

    public MapVO readMap() {

        try {
            List<String> rows = mapReader.read();
            MapVO mapVO = mapParser.parse(rows);
            mapValidator.validate(mapVO);

            return mapVO;
        } catch (MapReadException e) {
            LOGGER.error("Failed to read map");
            throw new RuntimeException("Failed to read map");
        } catch (MapParseException e) {
            LOGGER.error("Failed to parse map", e);
            throw new RuntimeException("Failed to parse map", e);
        } catch (MapValidationException e) {
            LOGGER.error("Failed to validate map.");
            throw new RuntimeException("The loaded map was invalid");
        }
        //return result;
    }
}
