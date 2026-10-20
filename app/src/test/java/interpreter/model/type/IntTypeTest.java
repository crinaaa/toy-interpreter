package interpreter.model.type;

import interpreter.model.value.IValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;

class IntTypeTest {
    @Test
    void testIntType() {
        IType type1 = new IntType();
        IType type2 = new IntType();

        assertEquals(type1, type2);
        assertEquals("int", type1.toString());

        IValue defaultVal = type1.defaultValue();
        assertEquals(new IntValue(0), defaultVal);
    }

    @Test
    void testBoolType() {
        IType type1 = new BoolType();
        IType type2 = new BoolType();

        assertEquals(type1, type2);
        assertEquals("bool", type1.toString());
        assertEquals(new BoolValue(false), type1.defaultValue());
    }
}