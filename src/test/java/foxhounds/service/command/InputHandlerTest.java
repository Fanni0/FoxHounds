package foxhounds.service.command.impl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.command.Command;
import service.command.InputHandler;

@ExtendWith(MockitoExtension.class)
public class InputHandlerTest {

    private static final String input = "input";

    @Mock
    private Command command1;

    @Mock
    private Command command2;

    private InputHandler underTest;

    @BeforeEach
    public void setUp() {
        underTest = new InputHandler(List.of(command1, command2));
    }

    @Test
    public void TestInputHandlerShouldReturnFirstCommand() {
        //given
        given(command1.canProcess(input)).willReturn(true);
        //when
        underTest.handleInput(input);
        //then
        verify(command1).canProcess(input);
        verify(command1).process(input);
        verifyNoInteractions(command2);
    }

    /*@Test
    public void TestInputShouldRunValidCommand(){
        //given
        given(command1.canProcess(input)).willReturn(false);
        given(command2.canProcess(input)).willReturn(false);
        //when
        underTest.handleInput(input);
        //then
        verify(command1).canProcess(input);
        verifyNoInteractions(command1);
        verify(command2).canProcess(input);
        verifyNoInteractions(command2);
    }*/
}
