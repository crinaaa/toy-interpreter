package interpreter.model.value;

import interpreter.model.type.StringType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringValueTest {
    @Test
    void testGetTypeReturnsStringType() {
        StringValue value = new StringValue("test.in");
        assertEquals(new StringType(), value.getType());
    }

    @Test
    void testEquals()
    {
        StringValue s1 = new StringValue("file.txt");
        StringValue s2 = new StringValue("file.txt");

        assertEquals(s1, s2);
    }

    @Test
    void testHashCode()
    {
        StringValue s1 = new StringValue("file.txt");
        StringValue s2 = new StringValue("file.txt");

        assertEquals(s1.hashCode(), s2.hashCode());
    }
}