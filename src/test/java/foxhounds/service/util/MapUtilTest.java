package foxhounds.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import model.MapVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.util.MapUtil;

public class MapTest {
    private MapUtil undertest;

    @BeforeEach
    public void setup() {
        undertest = new MapUtil();
    }

    @Test
    public void testgetRowofMapShouldReturnCorrectListWhenCalled() {
        //given
        int[][] values = {
                {7, 0},
                {4, 0}
        };
        boolean[][] endrow = {
                {true, true},
                {false, false}
        };
        MapVO mapVO = new MapVO(2, 2, values, null);
        //when
        List<Integer> result = undertest.getRowofMap(mapVO, 0);
        //then
        List<Integer> expected = List.of(7, 0);
        assertEquals(expected, result);
    }
}
