package foxandhounds.service.game;

import foxandhounds.service.command.InputHandler;
import foxandhounds.service.input.UserInputReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameStepPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameStepPerformer.class);

    private final UserInputReader userInputReader;
    private final InputHandler inputHandler;

    public GameStepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        this.userInputReader = userInputReader;
        this.inputHandler = inputHandler;
    }

    public void performgamestep() {
        String input = userInputReader.readInput();
        LOGGER.info("Read user input = '{}'", input);
        inputHandler.handleInput(input);

    }
}
