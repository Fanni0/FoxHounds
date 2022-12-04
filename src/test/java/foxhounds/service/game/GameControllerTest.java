package foxhounds.service.game;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import foxandhounds.model.GameState;
import foxandhounds.model.MapVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import foxandhounds.service.game.GameController;
import foxandhounds.service.game.GameStepPerformer;
import foxandhounds.service.util.MapUtil;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    private static final MapVO map_vo = new MapVO(0, 0, null, null, null, null);

    private GameState gameState;

    @Mock
    private GameStepPerformer gameStepPerformer;

    @Mock
    private MapUtil mapUtil;

    private GameController underTest;

    @Test
    public void TestLoopUntilUserExit() {
        //given
        gameState = new GameState(null, true);
        underTest = new GameController(gameState, mapUtil, gameStepPerformer);
        //when
        underTest.start();
        //then
        verifyNoInteractions(gameStepPerformer);
        /*verify(mapUtil, times(2)).isMapCompleted(map_vo);
        verify(gameStepPerformer, times(1)).performgamestep();*/
    }

    @Test
    public void TestLoopUntilCompleted() {
        //given
        gameState = new GameState(map_vo, false);
        underTest = new GameController(gameState, mapUtil, gameStepPerformer);
        given(mapUtil.isMapCompleted(map_vo)).willReturn(false, true);
        //when
        underTest.start();
        //then
        verify(mapUtil, times(2)).isMapCompleted(map_vo);
        verify(gameStepPerformer, times(1)).performgamestep();
    }
}
