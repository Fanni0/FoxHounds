package foxandhounds.service.command.impl;

import foxandhounds.model.GameState;
import foxandhounds.service.command.Command;
import foxandhounds.ui.MapPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCommand.class);

    private final MapPrinter mapPrinter;
    private final GameState gameState;

    public PrintCommand(MapPrinter mapPrinter, GameState gameState) {
        this.mapPrinter = mapPrinter;
        this.gameState = gameState;
    }

    private static final String print_command = "print";

    @Override
    public boolean canProcess(String input) {

        return print_command.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.info("Performing print command");
        mapPrinter.printMap(gameState.getMapVO());

    }
}
