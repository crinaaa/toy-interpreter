package interpreter.model.value;

import interpreter.model.type.BoolType;
import interpreter.model.type.IntType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntValueTest {
    @Test
    void testIntValue() {
        IntValue val1 = new IntValue(15);
        IntValue val2 = new IntValue(15);
        IntValue val3 = new IntValue(99);

        assertEquals(15, val1.val());

        assertEquals(val1, val2);
        assertNotEquals(val1, val3);


        assertEquals(new IntType(), val1.getType());
        assertEquals("15", val1.toString());
    }

    @Test
    void testBoolValue() {
        BoolValue val1 = new BoolValue(true);
        BoolValue val2 = new BoolValue(true);
        BoolValue val3 = new BoolValue(false);

        assertTrue(val1.val());

        assertEquals(val1, val2);
        assertNotEquals(val1, val3);

        assertEquals(new BoolType(), val1.getType());
        assertEquals("true", val1.toString());
    }
}