package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.dictionary.IDictionary;
import interpreter.model.adt.dictionary.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationalExpTest {

    private IDictionary<String, IValue> symTable;

    @BeforeEach
    void setUp()
    {
        symTable = new MyDictionary<>();
    }

    @Test
    void testLessThan() throws MyException {
        // 3<5 true
        RelationalExp trueExp = new RelationalExp(RelOp.LT, new ValueExp(new IntValue(3)),
                new ValueExp(new IntValue(5)));
        assertEquals(new BoolValue(true), trueExp.eval(symTable));

        //5<3 false
        RelationalExp falseExp = new RelationalExp(RelOp.LT,
                new ValueExp(new IntValue(5)),
                new ValueExp(new IntValue(3)));
        assertNotEquals(new BoolValue(true), falseExp.eval(symTable));
    }

    @Test
    void testLessThanOrEqual() throws MyException {
        // 3<=3 true
        RelationalExp trueExp = new RelationalExp(RelOp.LE, new ValueExp(new IntValue(3)),
                new ValueExp(new IntValue(3)));
        assertEquals(new BoolValue(true), trueExp.eval(symTable));

        //5<=3 false
        RelationalExp falseExp = new RelationalExp(RelOp.LE,
                new ValueExp(new IntValue(5)),
                new ValueExp(new IntValue(3)));
        assertNotEquals(new BoolValue(true), falseExp.eval(symTable));
    }

    @Test
    void testEqual() throws MyException {
        //3 == 3 - true
        RelationalExp trueExp = new RelationalExp(RelOp.EQ,
                new ValueExp(new IntValue(3)),
                new ValueExp(new IntValue(3)));
        assertEquals(new BoolValue(true), trueExp.eval(symTable));

        // 3 == 5 - false
        RelationalExp falseExp = new RelationalExp(RelOp.EQ,
                new ValueExp(new IntValue(3)),
                new ValueExp(new IntValue(5)));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }

    @Test
    void testNotEqual() throws MyException {
        //3 != 5 - true
        RelationalExp trueExp = new RelationalExp(RelOp.NE,
                new ValueExp(new IntValue(3)),
                new ValueExp(new IntValue(5)));
        assertEquals(new BoolValue(true), trueExp.eval(symTable));

        // 3 != 3 - false
        RelationalExp falseExp = new RelationalExp(RelOp.NE,
                new ValueExp(new IntValue(3)),
                new ValueExp(new IntValue(3)));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }

    @Test
    void testGreaterThan() throws MyException {
        // 5 > 3 -> true
        RelationalExp trueExp = new RelationalExp(RelOp.GT, new ValueExp(new IntValue(5)), new ValueExp(new IntValue(3)));
        assertEquals(new BoolValue(true), trueExp.eval(symTable));

        // 3 > 5 -> false
        RelationalExp falseExp = new RelationalExp(RelOp.GT, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }

    @Test
    void testGreaterThanOrEqual() throws MyException {
        // 5 >= 5 -> true
        RelationalExp trueExp = new RelationalExp(RelOp.GE, new ValueExp(new IntValue(5)), new ValueExp(new IntValue(5)));
        assertEquals(new BoolValue(true), trueExp.eval(symTable));

        // 3 >= 5 -> false
        RelationalExp falseExp = new RelationalExp(RelOp.GE, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }
    @Test
    void testFirstOperandNotIntegerThrowsException() {
        RelationalExp exp = new RelationalExp(
                RelOp.LT,
                new ValueExp(new StringValue("hello")),
                new ValueExp(new IntValue(5))
        );

        assertThrowsExactly(ExpressionException.class, () -> exp.eval(symTable));
    }

    @Test
    void testSecondOperandNotIntegerThrowsException() {
        RelationalExp exp = new RelationalExp(
                RelOp.LT,
                new ValueExp(new IntValue(5)),
                new ValueExp(new BoolValue(true))
        );

        assertThrowsExactly(ExpressionException.class, () -> exp.eval(symTable));
    }
}