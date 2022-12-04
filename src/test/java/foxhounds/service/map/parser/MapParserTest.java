package foxhounds.service.map.parser;


//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import foxandhounds.model.MapVO;
import foxandhounds.service.exception.MapParseException;
import foxandhounds.service.map.parser.MapParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MapParserTest {

    private static final int number_of_rows = 2;
    private static final int number_of_columns = 2;

    private static final List<String> valid_rawmap = List.of(
            "07",
            "40"
    );
    private static final List<String> invalid_rawmap_less_rows = List.of(
            "07"
    );
    private static final List<String> invalid_rawmap_less_columns = List.of(
            "07",
            "4"
    );
    private static final List<String> invalid_rawmap_invalid_characters = List.of(
            "07",
            "ab"
    );

    private static final int[][] map = {
            {0, 7},
            {4, 0}
    };

    private static final boolean[][] endrow = {
            {true, true},
            {false, false}
    };

    private static final int[] fox = {
            0,0
    };

    private static final int[][] hound = {
            {0,0},
            {0,0}
    };

    private static final MapVO expected_map_vo = new MapVO(number_of_rows, number_of_columns, map, endrow, fox, hound);

    private MapParser underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapParser(number_of_rows, number_of_columns, fox, hound);
    }

    /*@Test
    public void testParseMapShouldReturnNewParsedMap() throws MapParseException {
        // given

        // when
        MapVO result = underTest.parse(valid_rawmap);

        // then
        assertEquals(expected_map_vo,result);
    }*/

    @Test
    public void testParseMapShouldThrowMapParsingExceptionWhenThereAreNotEnoughRows() throws MapParseException {
        // given in setup

        // when - then
        assertThrows(MapParseException.class, () -> {
            underTest.parse(invalid_rawmap_less_rows);
        });
    }

    @Test
    public void testParseMapShouldThrowMapParsingExceptionWhenThereAreNotEnoughColumns() {
        // given in setup

        // when - then
        assertThrows(MapParseException.class, () -> {
            underTest.parse(invalid_rawmap_less_columns);
        });
    }

    @Test
    public void testParseMapShouldThrowMapParsingExceptionWhenThereAreInvalidCharacters() {
        // given in setup

        // when - then
        assertThrows(MapParseException.class, () -> {
            underTest.parse(invalid_rawmap_invalid_characters);
        });
    }

}