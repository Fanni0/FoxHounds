package foxhounds.service.game;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import foxandhounds.service.command.InputHandler;
import foxandhounds.service.game.GameStepPerformer;
import foxandhounds.service.input.UserInputReader;

@ExtendWith(MockitoExtension.class)
public class GameStepPerformerTest {

    private static final String input = "input";

    @Mock
    private UserInputReader userInputReader;

    @Mock
    private InputHandler inputHandler;

    private GameStepPerformer underTest;

    @BeforeEach
    public void setUp() {
        underTest = new GameStepPerformer(userInputReader, inputHandler);
    }

    @Test
    public void TestShouldReadInputAndHandle() {
        //given
        given(userInputReader.readInput()).willReturn(input);
        //when
        underTest.performgamestep();
        //then
        verify(userInputReader).readInput();
        verify(inputHandler).handleInput(input);
    }
}
