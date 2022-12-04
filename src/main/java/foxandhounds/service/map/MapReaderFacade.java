package foxandhounds.service.map;

import java.util.List;

import foxandhounds.model.MapVO;
import foxandhounds.service.exception.MapParseException;
import foxandhounds.service.exception.MapReadException;
import foxandhounds.service.exception.MapValidationException;
import foxandhounds.service.map.parser.MapParser;
import foxandhounds.service.map.reader.MapReader;
import foxandhounds.service.map.validation.MapValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
