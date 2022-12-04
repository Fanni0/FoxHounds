package foxhounds.service.command.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import foxandhounds.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import foxandhounds.service.command.impl.ExitCommand;

public class ExitCommandTest {

    private static final String exit_command = "exit";
    private static final String not_exit = "not-exit";

    private GameState gameState;

    private ExitCommand underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(null, false);
        underTest = new ExitCommand(gameState);
    }

    @Test
    public void TestCanProcessReturnTrue() {
        //given

        //when
        boolean result = underTest.canProcess(exit_command);
        //then
        assertTrue(result);
    }

    @Test
    public void TestCanProcessReturnFalse() {
        boolean result = underTest.canProcess(not_exit);
        assertFalse(result);
    }

    @Test
    public void TestShouldChangeExitGameState() {
        underTest.process(exit_command);
        assertTrue(gameState.isUserExit());
    }

}
