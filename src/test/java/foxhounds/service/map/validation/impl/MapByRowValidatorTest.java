package foxhounds.service.map.validation.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;

import foxandhounds.model.MapVO;
import foxandhounds.service.exception.InvalidRowException;
import foxandhounds.service.exception.MapValidationException;
import foxandhounds.service.map.validation.impl.MapByRowValidator;
import foxandhounds.service.util.CollectionUtil;
import foxandhounds.service.util.MapUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MapByRowValidatorTest {
    private static final MapVO mapVO = new MapVO(1, 1, null, null, null, null);
    private static final int rowIndex = 0;
    private static final List<Integer> rowValues = Collections.emptyList();
    private static final List<Integer> collectedValues = Collections.emptyList();

    @Mock
    private MapUtil mapUtil;

    @Mock
    private CollectionUtil collectionUtil;

    private MapByRowValidator underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapByRowValidator(mapUtil, collectionUtil);
    }

    @Test
    public void TestValidationShouldReturnValidRow() throws MapValidationException {
        given(mapUtil.getRowofMap(mapVO, rowIndex)).willReturn(rowValues);
        given(collectionUtil.collectValues(rowValues)).willReturn(collectedValues);
        given(collectionUtil.contains(collectedValues)).willReturn(true);
        underTest.validate(mapVO);
    }

    @Test
    public void TestValidateShouldThrowWhenInvalidRow() {
        given(mapUtil.getRowofMap(mapVO, rowIndex)).willReturn(rowValues);
        given(collectionUtil.collectValues(rowValues)).willReturn(collectedValues);
        given(collectionUtil.contains(collectedValues)).willReturn(false);
        assertThrows(InvalidRowException.class, () -> {
            underTest.validate(mapVO);
        });
    }
}
