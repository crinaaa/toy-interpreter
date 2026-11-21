package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class WhileStmtTest {
    private PrgState state;
    private final IStmt body = new PrintStmt(new ValueExp(new IntValue(1)));

    @BeforeEach
    void setUp()
    {
        state = PrgState.of(new NopStmt());
    }

    @Test
    void falseConditionPushesNothing() throws MyException
    {
        new WhileStmt(new ValueExp(new BoolValue(false)), body).execute(state);
        assertEquals(new NopStmt(), state.getExeStack().pop());
        assertTrue(state.getExeStack().isEmpty());
    }

    @Test
    void trueConditionPushesTheLoopThenTheBody() throws MyException {
        var loop = new WhileStmt(new ValueExp(new BoolValue(true)), body);
        loop.execute(state);
        assertEquals(body, state.getExeStack().pop());
        assertEquals(loop, state.getExeStack().pop());
    }

    @Test
    void nonBooleanConditionThrows() {
        var loop = new WhileStmt(new ValueExp(new IntValue(1)), body);
        assertThrows(StatementException.class, () -> loop.execute(state));
    }
}