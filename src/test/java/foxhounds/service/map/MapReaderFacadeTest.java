package foxhounds.service.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.List;

import foxandhounds.model.MapVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import foxandhounds.service.exception.MapParseException;
import foxandhounds.service.exception.MapReadException;
import foxandhounds.service.exception.MapValidationException;
import foxandhounds.service.map.MapReaderFacade;
import foxandhounds.service.map.parser.MapParser;
import foxandhounds.service.map.reader.MapReader;
import foxandhounds.service.map.validation.MapValidator;

@ExtendWith(MockitoExtension.class)
public class MapReaderFacadeTest {

    private static final List<String> raw_map = List.of(
            "row1",
            "row2"
    );

    private static final MapVO map_vo = new MapVO(0, 0, null, null, null, null);

    @Mock
    private MapReader mapReader;

    @Mock
    private MapParser mapParser;

    @Mock
    private MapValidator mapValidator;

    private MapReaderFacade underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapReaderFacade(mapReader, mapParser, mapValidator);
    }

    @Test
    public void TestRedMapShouldReturnMap() throws MapReadException, MapParseException, MapValidationException {
        //given
        given(mapReader.read()).willReturn(raw_map);
        given(mapParser.parse(raw_map)).willReturn(map_vo);
        //when
        MapVO result = underTest.readMap();
        //then
        verify(mapReader).read();
        verify(mapParser).parse(raw_map);
        verify(mapValidator).validate(map_vo);
        assertEquals(map_vo, result);
    }

    @Test
    public void TestReadMapReadFail() throws MapReadException {
        doThrow(MapReadException.class).when(mapReader).read();
        assertThrows(RuntimeException.class, () -> {
            underTest.readMap();
        });
    }

    @Test
    public void TestReadMapParseFail() throws MapReadException, MapParseException {
        given(mapReader.read()).willReturn(raw_map);
        doThrow(MapParseException.class).when(mapParser).parse(raw_map);
        assertThrows(RuntimeException.class, () -> {
            underTest.readMap();
        });
    }

    @Test
    public void TestReadMapValidationFail() throws MapReadException, MapParseException, MapValidationException {
        given(mapReader.read()).willReturn(raw_map);
        given(mapParser.parse(raw_map)).willReturn(map_vo);
        doThrow(MapValidationException.class).when(mapValidator).validate(map_vo);
        assertThrows(RuntimeException.class, () -> {
            underTest.readMap();
        });
    }

}
