package foxhounds.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import foxandhounds.service.util.CollectionUtil;

public class CollectionUtilTest {

    private static final List<Integer> list_of_numbers = List.of(0, 7, 0, 4, 7);
    private static final List<Integer> list_of_valid_values = List.of(0, 7, 4);
    private static final List<Integer> list_of_invalid_values = List.of(1, 2, 3);

    private CollectionUtil underTest;

    @BeforeEach
    public void setUp() {
        underTest = new CollectionUtil();
    }

    @Test
    public void TestCollectValuesShouldReturn() {
        List<Integer> result = underTest.collectValues(list_of_numbers);
        assertEquals(list_of_numbers, result);
    }

    @Test
    public void TestContainsOnlyValidCharacters() {
        boolean result = underTest.contains(list_of_valid_values);
        assertTrue(result);
    }

    @Test
    public void TestContainsInvalidCharacters() {
        boolean result = underTest.contains(list_of_invalid_values);
        assertFalse(result);
    }
}
