package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.dictionary.IDictionary;
import interpreter.model.adt.dictionary.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicExpTest {
    private IDictionary<String, IValue> symTable;

    @BeforeEach
    void setup()
    {
        symTable = new MyDictionary<>();
    }

    @Test
    void testLogicOperations() throws MyException {
        IExp t = new ValueExp(new BoolValue(true));
        IExp f = new ValueExp(new BoolValue(false));

        IExp andExp = new LogicExp(LogicOp.AND, t, f);
        IExp orExp = new LogicExp(LogicOp.OR, t, f);

        assertEquals(new BoolValue(false), andExp.eval(symTable));
        assertEquals(new BoolValue(true), orExp.eval(symTable));

        assertEquals("(true && false)", andExp.toString());
    }

    @Test
    void testLogicExpInvalidFirstOperand()
    {
        IExp o1 = new ValueExp(new IntValue(6));
        IExp o2 = new ValueExp(new BoolValue(true));
        IExp e = new LogicExp(LogicOp.AND, o1, o2);

        assertThrowsExactly(MyException.class, () -> e.eval(symTable));
    }

    @Test
    void testLogicExpInvalidSecondOperand()
    {
        IExp o1 = new ValueExp(new BoolValue(true));
        IExp o2 = new ValueExp(new IntValue(6));
        IExp e = new LogicExp(LogicOp.OR, o1, o2);

        assertThrowsExactly(MyException.class, () -> e.eval(symTable));
    }
}