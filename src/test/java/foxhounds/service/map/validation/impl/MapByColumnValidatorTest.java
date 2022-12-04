package foxhounds.service.map.validation.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;

import foxandhounds.model.MapVO;
import foxandhounds.service.exception.InvalidColumnException;
import foxandhounds.service.map.validation.impl.MapByColumnValidator;
import foxandhounds.service.util.CollectionUtil;
import foxandhounds.service.util.MapUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MapByColumnValidatorTest {

    private static final MapVO mapVO = new MapVO(1, 1, null, null, null, null);
    private static final int columnIndex = 0;
    private static final List<Integer> columnValues = Collections.emptyList();
    private static final List<Integer> collectedValues = Collections.emptyList();

    @Mock
    private MapUtil mapUtil;

    @Mock
    private CollectionUtil collectionUtil;

    private MapByColumnValidator underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapByColumnValidator(collectionUtil, mapUtil);
    }

    @Test
    public void TestValidateShouldReturnValidColumns(){
        //given
        given(mapUtil.getColumnofMap(mapVO, columnIndex)).willReturn(columnValues);
        given(collectionUtil.collectValues(columnValues)).willReturn(collectedValues);
        given(collectionUtil.contains(collectedValues)).willReturn(true);
        //when
        underTest.validate(mapVO);
        //then
    }

    @Test
    public void TestValidateShouldThrowWhenInvalidColumn(){
        given(mapUtil.getColumnofMap(mapVO, columnIndex)).willReturn(columnValues);
        given(collectionUtil.collectValues(columnValues)).willReturn(collectedValues);
        given(collectionUtil.contains(collectedValues)).willReturn(false);
        assertThrows(InvalidColumnException.class, () -> {
            underTest.validate(mapVO);
        });
    }
}
