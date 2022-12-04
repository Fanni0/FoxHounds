package foxhounds.service.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import foxandhounds.service.input.UserInputReader;

@ExtendWith(MockitoExtension.class)
public class UserInputHandlerTest {

    private static final String input = "input";

    @Mock
    private BufferedReader bufferedReader;

    private UserInputReader underTest;

    @BeforeEach
    public void setUp() {
        underTest = new UserInputReader(bufferedReader);
    }

    @Test
    public void TestUserInputShouldReadInputReadByBufferedReader() throws IOException {
        //given
        given(bufferedReader.readLine()).willReturn(input);
        //when
        String result = underTest.readInput();
        //then
        assertEquals(input, result);
    }

    @Test
    public void TestUserInputShouldReturnNullWhenBufferedReaderThrows() throws IOException {
        doThrow(IOException.class).when(bufferedReader).readLine();
        String result = underTest.readInput();
        assertNull(result);
    }
}
