package service.game;

import service.command.InputHandler;
import service.input.UserInputReader;

public class GameStepPerformer {

    private final UserInputReader userInputReader;
    private final InputHandler inputHandler;

    public GameStepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        this.userInputReader = userInputReader;
        this.inputHandler = inputHandler;
    }

    public void performgamestep() {
        String input = userInputReader.readInput();
        inputHandler.handleInput(input);

    }
}
