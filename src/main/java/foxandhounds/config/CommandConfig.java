package foxandhounds.config;

import java.util.List;

import foxandhounds.model.GameState;
import foxandhounds.service.command.Command;
import foxandhounds.service.command.InputHandler;
import foxandhounds.service.command.impl.ExitCommand;
import foxandhounds.service.command.impl.PrintCommand;
import foxandhounds.service.command.impl.PutCommand;
import foxandhounds.service.command.impl.UnknownCommand;
import foxandhounds.service.command.performer.PutPerformer;
import foxandhounds.service.map.validation.impl.MapValidatorComposer;
import foxandhounds.ui.MapPrinter;
import foxandhounds.ui.PrintWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfig {

    @Autowired
    private GameState gameState;
    @Autowired
    private MapPrinter mapPrinter;
    @Autowired
    private PutPerformer putPerformer;
    @Autowired
    private PrintWrapper printWrapper;
    @Autowired
    private MapValidatorComposer mapValidatorComposer;

    @Bean
    public Command printCommand() {
        return new PrintCommand(mapPrinter, gameState);
    }

    @Bean
    public Command putCommand() {
        return new PutCommand(gameState, putPerformer, mapValidatorComposer, mapPrinter, printWrapper);
    }

    @Bean
    public Command exitCommand() {
        return new ExitCommand(gameState);
    }

    @Bean
    public Command unknownCommand() {
        return new UnknownCommand(printWrapper);
    }

    @Bean
    public InputHandler inputHandler(List<Command> commandList) {
        return new InputHandler(commandList);
    }
}
