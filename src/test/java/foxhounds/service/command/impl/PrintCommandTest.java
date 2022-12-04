package foxhounds.service.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import model.GameState;
import model.MapVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.command.impl.PrintCommand;
import ui.MapPrinter;

@ExtendWith(MockitoExtension.class)
public class PrintCommandTest {
    private static final String print_command = "print";
    private static final String not_print = "not-print";

    private static final MapVO MAP_VO = new MapVO(0, 0, null, null);

    private GameState gameState;

    @Mock
    private MapPrinter mapPrinter;

    private PrintCommand underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(MAP_VO, false);
        underTest = new PrintCommand(mapPrinter, gameState);

    }

    @Test
    public void TestCanProcessReturnTrue() {
        //given

        //when
        boolean result = underTest.canProcess(print_command);
        //then
        assertTrue(result);
    }

    @Test
    public void TestCanProcessReturnFalse() {
        boolean result = underTest.canProcess(not_print);
        assertFalse(result);
    }

    @Test
    public void TestShoudPrintMap() {
        underTest.process(print_command);
        verify(mapPrinter).printMap(MAP_VO);
    }

}
