package foxandhounds.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import foxandhounds.Main;
import foxandhounds.model.GameState;
import foxandhounds.model.MapVO;
import foxandhounds.service.command.InputHandler;
import foxandhounds.service.command.performer.PutPerformer;
import foxandhounds.service.game.GameController;
import foxandhounds.service.game.GameStepPerformer;
import foxandhounds.service.input.UserInputReader;
import foxandhounds.service.map.MapReaderFacade;
import foxandhounds.service.map.parser.MapParser;
import foxandhounds.service.map.reader.MapReader;
import foxandhounds.service.map.reader.impl.BufferedReaderMapReader;
import foxandhounds.service.map.validation.impl.MapValidatorComposer;
import foxandhounds.service.util.MapUtil;
import foxandhounds.ui.MapPrinter;
import foxandhounds.ui.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean(initMethod = "start")
    GameController gameController(GameState gameState, GameStepPerformer gameStepPerformer, MapUtil mapUtil) {
        return new GameController(gameState, mapUtil, gameStepPerformer);
    }

    int[] fox = {7, 0};
    int[][] hound = {
            {0, 1},
            {0, 3},
            {0, 5},
            {0, 7}
    };

    @Bean
    GameState gameState(MapValidatorComposer mapValidatorComposer) {
        InputStream is = Main.class.getClassLoader().getResourceAsStream("Map/Board(standard)(numbers).txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        MapReader mapReader = new BufferedReaderMapReader(reader);

        MapParser mapParser = new MapParser(8, 8, fox, hound);

        MapReaderFacade mapReaderFacade = new MapReaderFacade(mapReader, mapParser, mapValidatorComposer);
        MapVO mapVO = mapReaderFacade.readMap();
        return new GameState(mapVO, false);
    }

    @Bean
    MapParser mapParser() {
        return new MapParser(8, 8, fox, hound);
    }

    @Bean
    GameStepPerformer gameStepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        return new GameStepPerformer(userInputReader, inputHandler);
    }

    @Bean
    UserInputReader userInputReader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return new UserInputReader(bufferedReader);
    }

    @Bean
    MapPrinter mapPrinter(MapUtil mapUtil, PrintWrapper printWrapper) {
        return new MapPrinter(4, 4, mapUtil, printWrapper);
    }

    @Bean
    PutPerformer putPerformer() {
        return new PutPerformer();
    }

    @Bean
    PrintWrapper printWrapper() {
        return new PrintWrapper();
    }
}
