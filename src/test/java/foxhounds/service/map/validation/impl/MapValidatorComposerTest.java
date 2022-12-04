package foxhounds.service.map.validation.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.List;

import foxandhounds.model.MapVO;
import foxandhounds.service.exception.MapValidationException;
import foxandhounds.service.map.validation.MapValidator;
import foxandhounds.service.map.validation.impl.MapValidatorComposer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MapValidatorComposerTest {
    private static final MapVO mapVO = new MapVO(0, 0, null, null, null, null);

    @Mock
    private MapValidator mapValidator1;

    @Mock
    private MapValidator mapValidator2;

    private MapValidatorComposer underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapValidatorComposer(List.of(mapValidator1, mapValidator2));
    }

    @Test
    public void TestValidateShouldNotThrowWhenOthersOkay() throws MapValidationException {
        underTest.validate(mapVO);
        verify(mapValidator1).validate(mapVO);
        verify(mapValidator2).validate(mapVO);
    }

    @Test
    public void TestValidateShouldThrowWhenOthersThrow() throws MapValidationException {
        doThrow(MapValidationException.class).when(mapValidator2).validate(mapVO);
        assertThrows(MapValidationException.class, () -> {
            underTest.validate(mapVO);
        });
    }
}
