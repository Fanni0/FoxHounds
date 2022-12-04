package foxhounds.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import foxandhounds.model.MapVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import foxandhounds.service.util.MapUtil;

public class MapUtilTest {
    private static final int numberOfRows = 2;
    private static final int numberOfColumns = 2;
    private static final int[][] map = {
            {7, 0},
            {4, 0}
    };
    private static final int[][] completed_map = {
            {4, 0},
            {0, 7}
    };
    private static final boolean[][] endrow = {
            {true, true},
            {false, false}
    };

    private static final MapVO mapVO = new MapVO(numberOfRows, numberOfColumns, map, endrow, null, null);
    private static final MapVO CompleteMapVO = new MapVO(numberOfRows, numberOfColumns, completed_map, endrow, null, null);

    private static final int firstRowIndex = 0;
    private static final int firstColumnIndex = 0;

    private static final List<Integer> firstRowList = List.of(7, 0);
    private static final List<Integer> firstColumnList = List.of(7, 4);
    private static final List<Boolean> firstEndrowList = List.of(true, true);

    private MapUtil undertest;

    @BeforeEach
    public void setup() {
        undertest = new MapUtil();
    }

    @Test
    public void testgetRowofMapShouldReturnCorrectListWhenCalled() {
        //given

        //when
        List<Integer> result = undertest.getRowofMap(mapVO, firstRowIndex);
        //then

        assertEquals(firstRowList, result);
    }

    @Test
    public void TestGetColumnOfMapShouldWork(){
        List<Integer> result = undertest.getColumnofMap(mapVO, firstColumnIndex);
        assertEquals(firstColumnList, result);
    }

    @Test
    public void TestGetEndrowReturnsTrueWhenFirstrow(){
        List<Boolean> result = undertest.getEndrow(mapVO, firstRowIndex);
        assertEquals(firstEndrowList, result);
    }

    @Test
    public void TestIsMapCompletedShouldReturnTrueWhenComplete(){
        boolean result = undertest.isMapCompleted(CompleteMapVO);
        assertTrue(result);
    }

    @Test
    public void TestIsCompleteShouldReturnFalseWhenIncomplete(){
        boolean result = undertest.isMapCompleted(mapVO);
        assertFalse(result);
    }
}
