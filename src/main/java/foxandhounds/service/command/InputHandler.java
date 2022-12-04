package service.command;

import java.util.List;

public class InputHandler {

    private final List<Command> commandList;

    public InputHandler(List<Command> commandList) {
        this.commandList = commandList;
    }

    public void handleInput(String input) {
        for (Command command : commandList) {
            if (command.canProcess(input)) {
                command.process(input);
                break;
            }
        }
    }
}
