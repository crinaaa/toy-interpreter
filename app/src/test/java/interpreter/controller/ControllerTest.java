package interpreter.controller;

import interpreter.exceptions.MyException;
import interpreter.model.adt.dictionary.MyDictionary;
import interpreter.model.adt.list.MyList;
import interpreter.model.adt.stack.MyStack;
import interpreter.model.exp.ArithExp;
import interpreter.model.exp.ArithOp;
import interpreter.model.exp.ValueExp;
import interpreter.model.exp.VarExp;
import interpreter.model.PrgState;
import interpreter.model.stmt.*;
import interpreter.model.type.BoolType;
import interpreter.model.type.IntType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import interpreter.repository.IRepository;
import interpreter.repository.Repository;
import interpreter.view.Examples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private PrgState run(IStmt program) throws MyException {
        PrgState state = PrgState.of(program);
        IRepository repo = new Repository(state);
        // turn off displayFlag so tests execute quietly without printing intermediate states
        Controller controller = new Controller(repo, false);
        controller.allStep();
        return state;
    }

    @Test
    void testOneStepOnEmptyStackThrowsException() throws MyException {
        PrgState state = PrgState.of(new NopStmt());
        state.getExeStack().pop();
        Controller controller = new Controller(new Repository(state), false);

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
        Controller controller = new Controller(new Repository(), true);
        assertTrue(controller.getDisplayFlag());

        controller.setDisplayFlag(false);
        assertFalse(controller.getDisplayFlag());
    }
}