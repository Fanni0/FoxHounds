package foxhounds.service.map.reader.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import foxandhounds.service.exception.MapReadException;
import foxandhounds.service.map.reader.impl.BufferedReaderMapReader;

@ExtendWith(MockitoExtension.class)
public class BufferedReaderTest {


    @Mock
    private BufferedReader bufferedReader;

    private BufferedReaderMapReader undertest;

    @BeforeEach
    public void setUp() {

        undertest = new BufferedReaderMapReader(bufferedReader);
    }

    @Test
    public void testReaderShouldReturnCorrectResultWhenCalled() throws IOException {
        //given
        given(bufferedReader.readLine()).willReturn("test", null);
        //when
        List<String> result = undertest.read();
        //then
        List<String> expected = List.of("test");
        assertEquals(expected, result);
    }

    @Test
    public void testReaderShouldThrowExceptionInCaseOfIOException() throws IOException {
        //given
        given(bufferedReader.readLine()).willThrow(IOException.class);
        //when - then
        assertThrows(MapReadException.class, () -> {
            undertest.read();
        });

    }
}
