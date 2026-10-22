package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.dictionary.MyDictionary;
import interpreter.model.adt.list.MyList;
import interpreter.model.adt.stack.MyStack;
import interpreter.model.type.BoolType;
import interpreter.model.type.IntType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VarDeclStmtTest {
    private PrgState state;

    @BeforeEach
    void setup()
    {
        state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new NopStmt());
    }

    @Test
    void testVarDeclStmtSuccess() throws MyException {
        VarDeclStmt declInt = new VarDeclStmt("x", new IntType());
        VarDeclStmt declBool = new VarDeclStmt("b", new BoolType());

        declBool.execute(state);
        declInt.execute(state);

        assertTrue(state.getSymTable().isDefined("x"));
        assertEquals(new IntValue(0), state.getSymTable().lookup("x"));

        assertTrue(state.getSymTable().isDefined("b"));
        assertEquals(new BoolValue(false), state.getSymTable().lookup("b"));
    }

    @Test
    void testVarDeclStmtAlreadyDeclaredThrowsException() throws MyException {
        VarDeclStmt decl1 = new VarDeclStmt("x", new IntType());
        VarDeclStmt decl2 = new VarDeclStmt("x", new IntType());

        decl1.execute(state);
        assertThrowsExactly(StatementException.class, () -> decl2.execute(state));
    }
}