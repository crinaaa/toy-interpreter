package interpreter.model.value;

import interpreter.model.type.IntType;
import interpreter.model.type.RefType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefValueTest {

    @Test
    void typeIsRefOfTheLocationType()
    {
        assertEquals(new RefType(new IntType()),
                new RefValue(1, new IntType()).getType());
    }

    @Test
    void sameAddressAndTypeAreEqual()
    {
        assertEquals(new RefValue(1, new IntType()),
                new RefValue(1, new IntType()));
    }

    @Test
    void differentAddressNotEqual()
    {
        assertNotEquals(new RefValue(1, new IntType()),
                new RefValue(2, new IntType()));
    }
}