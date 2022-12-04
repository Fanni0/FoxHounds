package foxandhounds.service.game;

import foxandhounds.model.GameState;
import foxandhounds.service.util.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    private final GameState gameState;
    private final MapUtil maputil;
    private final GameStepPerformer gameStepPerformer;

    public GameController(GameState gameState, MapUtil maputil, GameStepPerformer gameStepPerformer) {
        this.gameState = gameState;
        this.maputil = maputil;
        this.gameStepPerformer = gameStepPerformer;
    }

    public void start() {
        LOGGER.info("Starting game loop.");
        while (istheGameinProgress()) {
            gameStepPerformer.performgamestep();
        }
        LOGGER.info("Game loop finished.");
    }

    private boolean istheGameinProgress() {
        return !gameState.isUserExit() && !maputil.isMapCompleted(gameState.getMapVO());
    }
}
