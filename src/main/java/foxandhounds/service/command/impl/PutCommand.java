package foxandhounds.service.command.impl;

import java.io.IOException;
import java.util.regex.Pattern;

import foxandhounds.model.GameState;
import foxandhounds.model.MapVO;
import foxandhounds.service.command.Command;
import foxandhounds.service.command.performer.PutPerformer;
import foxandhounds.service.exception.MapValidationException;
import foxandhounds.service.exception.PutException;
import foxandhounds.service.map.validation.MapValidator;
import foxandhounds.ui.MapPrinter;
import foxandhounds.ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PutCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(PutCommand.class);

    private static final String put_command_regex = "^put [1-8] [1-8] [4,7]$";
    private static final String put_error_message = "Can't write to an occupied position";
    private static final String map_validation_error = "Can't write to that position";

    private final GameState gameState;
    private final PutPerformer putPerformer;
    private final MapValidator mapValidator;
    private final MapPrinter mapPrinter;
    private final PrintWrapper printWrapper;

    private MapVO mapVO;

    public PutCommand(GameState gameState, PutPerformer putPerformer, MapValidator mapValidator, MapPrinter mapPrinter,
                      PrintWrapper printWrapper) {
        this.gameState = gameState;
        this.putPerformer = putPerformer;
        this.mapValidator = mapValidator;
        this.mapPrinter = mapPrinter;
        this.printWrapper = printWrapper;
    }

    @Override
    public boolean canProcess(String input) {
        return Pattern.matches(put_command_regex, input);
    }

    @Override
    public void process(String input) {
        String[] parts = input.split(" ");
        int rowIndex = Integer.parseInt(parts[1]);
        int columnIndex = Integer.parseInt(parts[2]);
        int character = Integer.parseInt(parts[3]);


        LOGGER.info("Performing put command with rowIndex = {}, columnIndex = {}, character = {}", rowIndex, columnIndex, character);

        try {
            MapVO newMap = putPerformer.perform(gameState.getMapVO(), (rowIndex - 1), (columnIndex - 1), character);
            mapValidator.validate(newMap);
            gameState.setMapVO(newMap);

            mapPrinter.printMap(newMap);
        } catch (PutException e) {
            LOGGER.error("Exception occured while performing put operation", e);
            printWrapper.printLine(put_error_message);
        } catch (MapValidationException e) {
            LOGGER.error("Exception occured while validating the updated map after put operation", e);
            printWrapper.printLine(map_validation_error);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
