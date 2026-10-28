package interpreter.model.type;

import interpreter.model.value.StringValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringTypeTest {
    @Test
    void testDefaultValue()
    {
        StringType stringType = new StringType();
        assertEquals(new StringValue(""), stringType.defaultValue());
    }

    @Test
    void testEquals()
    {
        StringType t1 = new StringType();
        StringType t2 = new StringType();
        assertEquals(t1, t2);
    }

    @Test
    void testHashCode()
    {
        StringType t1 = new StringType();
        StringType t2 = new StringType();
        assertEquals(t1.hashCode(), t2.hashCode());
    }
}