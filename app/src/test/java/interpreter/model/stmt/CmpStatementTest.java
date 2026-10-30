package interpreter.model.stmt;

import interpreter.exceptions.ADTException;
import interpreter.model.PrgState;
import interpreter.model.adt.dictionary.MyDictionary;
import interpreter.model.adt.list.MyList;
import interpreter.model.adt.stack.MyStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CmpStatementTest {
    private PrgState state;

    @BeforeEach
    void setup() throws ADTException {
        state = PrgState.of(new NopStmt());
        state.getExeStack().pop();
    }

    @Test
    void testCompStmtExecutionPushesInReverseOrder() throws ADTException {
        IStmt s1 = new NopStmt();
        IStmt s2 = new NopStmt();
        CompStmt comp = new CompStmt(s1, s2);
        comp.execute(state);

        assertEquals(2, state.getExeStack().size());
        assertEquals(s1, state.getExeStack().pop());
        assertEquals(s2, state.getExeStack().pop());
    }

    @Test
    void testCompStmtToString() {
        CompStmt comp = new CompStmt(new NopStmt(), new NopStmt());
        assertEquals("(nop; nop)", comp.toString());
    }
}