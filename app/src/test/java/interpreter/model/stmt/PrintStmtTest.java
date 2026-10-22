package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.dictionary.MyDictionary;
import interpreter.model.adt.list.MyList;
import interpreter.model.adt.stack.MyStack;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintStmtTest {

    private PrgState state;

    @BeforeEach
    void setUp() {
        state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new NopStmt());
    }

    @Test
    void testPrintStmtAppendsToOut() throws MyException {
        PrintStmt print = new PrintStmt(new ValueExp(new IntValue(99)));

        print.execute(state);

        assertEquals(1, state.getOut().size());
        assertEquals(new IntValue(99), state.getOut().get(0));
    }
}