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

class ArithExpTest {
    private IDictionary<String, IValue> symTable;

    @BeforeEach
    void setUp() {
        symTable = new MyDictionary<>();
    }

    @Test
    void testArithExpOperations() throws MyException {
        IExp e1 = new ValueExp(new IntValue(10));
        IExp e2 = new ValueExp(new IntValue(2));

        IExp add = new ArithExp(ArithOp.ADD, e1, e2);
        IExp sub = new ArithExp(ArithOp.SUB, e1, e2);
        IExp mul = new ArithExp(ArithOp.MUL, e1, e2);
        IExp div = new ArithExp(ArithOp.DIV, e1, e2);

        assertEquals(new IntValue(12), add.eval(symTable));
        assertEquals(new IntValue(8), sub.eval(symTable));
        assertEquals(new IntValue(20), mul.eval(symTable));
        assertEquals(new IntValue(5), div.eval(symTable));

        assertEquals("(10 + 2)", add.toString());
    }

    @Test
    void testArithExpDivisionByZero() {
        IExp e1 = new ValueExp(new IntValue(10));
        IExp e2 = new ValueExp(new IntValue(0));
        IExp div = new ArithExp(ArithOp.DIV, e1, e2);

        assertThrowsExactly(ExpressionException.class, () -> div.eval(symTable));
    }

    @Test
    void invalidFirstOperand()
    {
        IExp e1 = new ValueExp(new BoolValue(false));
        IExp e2 = new ValueExp(new IntValue(5));
        IExp add = new ArithExp(ArithOp.ADD, e1, e2);
        assertThrowsExactly(ExpressionException.class, () -> add.eval(symTable));
    }

    @Test
    void invalidSecondOperand()
    {
        IExp e1 = new ValueExp(new IntValue(5));
        IExp e2 = new ValueExp(new BoolValue(false));
        IExp add = new ArithExp(ArithOp.ADD, e1, e2);
        assertThrowsExactly(ExpressionException.class, () -> add.eval(symTable));
    }
}