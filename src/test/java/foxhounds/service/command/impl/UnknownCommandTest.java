package foxhounds.service.command.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import foxandhounds.service.command.impl.UnknownCommand;
import foxandhounds.ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UnknownCommandTest {

    private static final String input = "";
    private static final String unknown_command_message = "Unknown command";

    @Mock
    private PrintWrapper printWrapper;

    private UnknownCommand underTest;

    @BeforeEach
    public void setUp() {
        underTest = new UnknownCommand(printWrapper);
    }

    @Test
    public void TestCanProcessReturnTrue() {
        boolean result = underTest.canProcess(input);
        assertTrue(result);
    }

    @Test
    public void TestProcessPrintUnknownCommand() {
        underTest.process(input);
        verify(printWrapper).printLine(unknown_command_message);
    }

}
