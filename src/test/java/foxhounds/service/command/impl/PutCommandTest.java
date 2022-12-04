package foxhounds.service.command.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.io.IOException;

import foxandhounds.model.GameState;
import foxandhounds.model.MapVO;
import foxandhounds.service.command.impl.PutCommand;
import foxandhounds.service.command.performer.PutPerformer;
import foxandhounds.service.exception.MapValidationException;
import foxandhounds.service.exception.PutException;
import foxandhounds.service.map.validation.MapValidator;
import foxandhounds.ui.MapPrinter;
import foxandhounds.ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PutCommandTest {

    private static final String put_command = "put 1 1 4";
    private static final String not_put_command = "not put";
    private static final int rowIndex = 0;
    private static final int columnIndex = 0;
    private static final int character = 4;
    private static final MapVO map = new MapVO(0, 0,null, null, null, null);
    private static final MapVO newmap = new MapVO(0, 0, null, null, null, null);
    private static final String put_error_message = "Can only put 4 or 7";
    private static final String map_validation_error = "Can't write to an occupied position";
    private GameState gameState;

    @Mock
    private PutPerformer putPerformer;

    @Mock
    private MapValidator mapValidator;

    @Mock
    private MapPrinter mapPrinter;

    @Mock
    private PrintWrapper printWrapper;

    private PutCommand underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(map, false);
        underTest = new PutCommand(gameState, putPerformer, mapValidator, mapPrinter, printWrapper);
    }

    @Test
    public void TestProcessTrueWhenValidCommand () {
        boolean result = underTest.canProcess(put_command);
        assertTrue(result);
    }

    @Test
    public void TestProcessFalseWhenInvalidCommand () {
        boolean result = underTest.canProcess(not_put_command);
        assertFalse(result);
    }

    @Test
    public void TestProcessPerformValidCommand() throws PutException, MapValidationException , IOException {
        given(putPerformer.perform(map, rowIndex, columnIndex, character)).willReturn(newmap);
        underTest.process(put_command);
        verify(putPerformer).perform(map, rowIndex, columnIndex, character);
        verify(mapValidator).validate(newmap);
        assertEquals(newmap, gameState.getMapVO());
        verify(mapPrinter).printMap(newmap);
    }

    @Test
    public void TestProcessNewMapValidationFail() throws PutException , IOException{
        doThrow(PutException.class).when(putPerformer).perform(map, rowIndex, columnIndex, character);
        underTest.process(put_command);
        verify(putPerformer).perform(map, rowIndex, columnIndex, character);
        verifyNoInteractions(mapValidator);
        assertEquals(map, gameState.getMapVO());
        verifyNoInteractions(mapPrinter);
        verify(printWrapper).printLine(map_validation_error);
    }

    /*@Test
    public void TestProcessShouldNotPerformInvalidCommand() throws PutException , MapValidationException, IOException{
        given(putPerformer.perform(map, rowIndex, columnIndex, character)).willReturn(newmap);
        doThrow(MapValidationException.class).when(mapValidator).validate(newmap);
        underTest.process(put_command);
        verify(putPerformer).perform(map, rowIndex, columnIndex, character);
        assertEquals(map, gameState.getMapVO());
        verifyNoInteractions(mapPrinter);
        verify(printWrapper).printLine(put_error_message);
    }*/


}
