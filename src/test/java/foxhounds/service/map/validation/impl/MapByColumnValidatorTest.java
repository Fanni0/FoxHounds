package foxhounds.service.map.validation.impl;

import java.util.Collections;
import java.util.List;

import foxandhounds.model.MapVO;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MapByRowValidatorTest {

    private static final MapVO mapVO = new MapVO(1, 1, null, null, null, null);
    private static final int columnIndex = 0;
    private static final List<Integer> columnValues = Collections.emptyList();
    private static final List<Integer> collectedValues = Collections.emptyList();
}
