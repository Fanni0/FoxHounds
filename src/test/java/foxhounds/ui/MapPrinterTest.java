package foxhounds.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import foxandhounds.model.MapVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import foxandhounds.service.util.MapUtil;
import foxandhounds.ui.MapPrinter;
import foxandhounds.ui.PrintWrapper;

@ExtendWith(MockitoExtension.class)
public class MapPrinterTest {

    private static final int number_of_rows = 4;
    private static final int number_of_columns = 4;
    private static final int box_width = 2;
    private static final int box_height = 2;

    private static final int[][] map = {
            {0, 7, 0, 7},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {4, 0, 0, 0}
    };

    private static final MapVO map_vo = new MapVO(number_of_rows, number_of_columns, map,null, null, null);

    private static final List<List<Integer>> rows_as_lists = List.of(
            List.of(0, 7, 0, 7),
            List.of(0, 0, 0, 0),
            List.of(0, 0, 0, 0),
            List.of(4, 0, 0, 0)
            );

    private static final List<String> map_as_string = List.of(
            "| |1|2|3|4|",
            "___________",
            "1|| |7| |7|",
            "2|| | | | |",
            "3|| | | | |",
            "4||4| | | |"
    );

    @Mock
    private MapUtil mapUtil;

    @Mock
    private PrintWrapper printWrapper;

    @Captor
    private ArgumentCaptor<String> printwrapperArgumentCaptor;

    private MapPrinter underTest;

    @BeforeEach
    public void setUp(){
        underTest = new MapPrinter(box_width, box_height, mapUtil, printWrapper);
    }

    @Test
    public void TestPrintMapCorrectPrintWrapper(){
        for (int i = 0; i <rows_as_lists.size(); i++) {
            given(mapUtil.getRowofMap(map_vo,i)).willReturn(rows_as_lists.get(i));
        }
        underTest.printMap(map_vo);

        verify(printWrapper, times(6)).printLine(printwrapperArgumentCaptor.capture());
        assertEquals(map_as_string, printwrapperArgumentCaptor.getAllValues());
    }
}
