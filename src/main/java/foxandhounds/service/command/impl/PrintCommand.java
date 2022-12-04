package service.command.impl;

import model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.command.Command;
import ui.MapPrinter;

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
        mapPrinter.printMap(gameState.getMapVO());

    }
}
