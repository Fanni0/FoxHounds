package foxandhounds.service.command.impl;

import foxandhounds.service.command.Command;
import foxandhounds.ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnknownCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnknownCommand.class);

    private static final String unknown_command_message = "Unknown command";

    private final PrintWrapper printWrapper;

    public UnknownCommand(PrintWrapper printWrapper) {
        this.printWrapper = printWrapper;
    }

    @Override
    public boolean canProcess(String input) {
        return true;
    }

    @Override
    public void process(String input) {
        LOGGER.info("Performing unknown command");
        printWrapper.printLine(unknown_command_message);
    }
}
