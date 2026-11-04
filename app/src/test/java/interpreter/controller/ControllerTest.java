package interpreter.controller;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.stmt.*;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import interpreter.repository.IRepository;
import interpreter.repository.Repository;
import interpreter.view.Examples;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @TempDir
    Path tempDir;

    private PrgState run(IStmt program) throws MyException {
        PrgState state = PrgState.of(program);
        String logPath = tempDir.resolve("test_log.txt").toString();
        IRepository repo = new Repository(state, logPath);

        Controller controller = new Controller(repo, false);
        controller.allStep();
        return state;
    }

    @Test
    void testOneStepOnEmptyStackThrowsException() throws MyException {
        PrgState state = PrgState.of(new NopStmt());
        state.getExeStack().pop();

        String logPath = tempDir.resolve("empty_stack_log.txt").toString();
        Controller controller = new Controller(new Repository(state, logPath), false);

        assertThrows(MyException.class, () -> controller.oneStep(state));
    }

    @Test
    void testExample1ExecutionPrintsTwo() throws MyException {
        PrgState state = run(Examples.getExample1());

        assertTrue(state.getExeStack().isEmpty());
        assertEquals(1, state.getOut().size());
        assertEquals(new IntValue(2), state.getOut().get(0));
    }

    @Test
    void testExample2ExecutionPrintsEighteen() throws MyException {
        PrgState state = run(Examples.getExample2());

        assertTrue(state.getExeStack().isEmpty());
        assertEquals(1, state.getOut().size());
        assertEquals(new IntValue(18), state.getOut().get(0));
    }

    @Test
    void testExample3ExecutionPrintsTwo() throws MyException {
        PrgState state = run(Examples.getExample3());

        assertTrue(state.getExeStack().isEmpty());
        assertEquals(1, state.getOut().size());
        assertEquals(new IntValue(2), state.getOut().get(0));
    }

    @Test
    void testDisplayFlagGetterAndSetter() {
        PrgState state = PrgState.of(new NopStmt());
        String logPath = tempDir.resolve("flag_test_log.txt").toString();
        Controller controller = new Controller(new Repository(state, logPath), true);

        assertTrue(controller.getDisplayFlag());

        controller.setDisplayFlag(false);
        assertFalse(controller.getDisplayFlag());
    }

    @Test
    void testLogPrgStateExecWritesToLogFile() throws MyException, IOException {
        Path logFile = tempDir.resolve("test_log.txt");
        PrgState state = PrgState.of(new NopStmt());
        IRepository repo = new Repository(state, logFile.toString());

        repo.logPrgStateExec();

        String content = Files.readString(logFile);
        assertTrue(content.contains("ExeStack:"));
        assertTrue(content.contains("SymTable:"));
        assertTrue(content.contains("Out:"));
        assertTrue(content.contains("FileTable:"));
    }

    @Test
    void testExample4ExecutionReadsAndPrintsValues() throws Exception {
        Path testFile = Path.of("test.in");
        Files.writeString(testFile, "15\n50\n");

        try {
            PrgState state = run(Examples.EX4);

            assertTrue(state.getExeStack().isEmpty());
            assertEquals(2, state.getOut().size());
            assertEquals(new IntValue(15), state.getOut().get(0));
            assertEquals(new IntValue(50), state.getOut().get(1));
            assertFalse(state.getFileTable().isDefined(new StringValue("test.in")));
        } finally {
            Files.deleteIfExists(testFile);
        }
    }

    @Test
    void testExample5ExecutionPrintsFive() throws MyException {
        PrgState state = run(Examples.EX5);

        assertTrue(state.getExeStack().isEmpty());
        assertEquals(1, state.getOut().size());
        assertEquals(new IntValue(5), state.getOut().get(0));
    }
}