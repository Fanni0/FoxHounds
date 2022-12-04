package foxhounds.service.command.performer;

//import java.io.IOException;

import foxandhounds.model.MapVO;
import foxandhounds.service.command.performer.PutPerformer;
import foxandhounds.service.exception.PutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PutPerformerTest {

    private static final int numberOfRows = 2;
    private static final int numberOfColumns = 2;
    private static final int targetRowIndex = 0;
    private static final int validTargetColumnIndex = 0;
    private static final int invalidTargetColumnIndex = 1;
    private static final int targetNumber = 4;

    private static final int[][] input_map = {
            {0, 7},
            {4, 0}
    };
    private static final boolean[][] endrow = {
            {false, false},
            {false, false}
    };
    private static final int[] input_fox = {
            1, 0
    };
    private static final int[][] hound = {
            {0, 1}
    };
    private static final int[][] expected_map = {
            {4, 7},
            {0, 0}
    };
    private static final int[] expected_fox = {
            0, 0
    };

    private static final MapVO input_mapVO = new MapVO(numberOfRows, numberOfColumns, input_map, endrow, input_fox, hound);
    private static final MapVO expected_mapVO = new MapVO(numberOfRows, numberOfColumns, expected_map, endrow, expected_fox, hound);

    private PutPerformer underTest;

    @BeforeEach
    public void setUp(){
        underTest = new PutPerformer();
    }

    /*@Test
    public void TestPerformerShouldPerform() throws PutException, IOException {
        MapVO result = underTest.perform(input_mapVO, targetRowIndex, validTargetColumnIndex, targetNumber);
        Assertions.assertEquals(expected_mapVO, result);
    }*/

    @Test
    public void TestPerformShouldThrowPutException(){
        Assertions.assertThrows(PutException.class, () -> {
            underTest.perform(input_mapVO, targetRowIndex, invalidTargetColumnIndex, targetNumber);
        });
    }
}
