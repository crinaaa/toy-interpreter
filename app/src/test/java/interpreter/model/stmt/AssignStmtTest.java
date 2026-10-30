package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.dictionary.MyDictionary;
import interpreter.model.adt.list.MyList;
import interpreter.model.adt.stack.MyStack;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignStmtTest {
    private PrgState state;

    @BeforeEach
    void setup()
    {
        state = PrgState.of(new NopStmt());
    }

    @Test
    void testAssignStmtSuccess() throws MyException {
        new VarDeclStmt("v", new IntType()).execute(state);
        AssignStmt assign = new AssignStmt("v", new ValueExp(new IntValue(10)));
        assign.execute(state);
        assertEquals(new IntValue(10), state.getSymTable().lookup("v"));
    }

    @Test
    void testAssignStmtNotDeclaredThrowsException() {
        AssignStmt assign = new AssignStmt("v", new ValueExp(new IntValue(10)));
        assertThrowsExactly(StatementException.class, () -> assign.execute(state));
    }

    @Test
    void testAssignStmtTypeMismatchThrowsException() throws MyException {
        new VarDeclStmt("v", new IntType()).execute(state);
        AssignStmt assign = new AssignStmt("v", new ValueExp(new BoolValue(true)));
        assertThrowsExactly(StatementException.class, () -> assign.execute(state));
    }
}