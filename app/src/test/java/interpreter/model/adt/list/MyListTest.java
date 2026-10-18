package interpreter.model.adt.list;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.MyException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {

    @Test
    void testAdd()
    {
        MyList<String> list = new MyList<>();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());

        list.add("apple");
        list.add("banana");

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void testRemoveSuccessful() throws ADTException
    {
        MyList<String> list = new MyList<>();
        list.add("First");
        list.add("Second");
        list.add("Third");

        assertEquals("Second", list.remove(1));
        assertEquals(2, list.size());
    }

    @Test
    void removeOutOfBounds()
    {
        MyList<Double> list = new MyList<>();
        list.add(10.2);
        list.add(3.4);
        assertThrowsExactly(ADTException.class, () -> list.remove(-1));
    }

    @Test
    void testSuccessfulGet() throws ADTException
    {
        MyList<Double> list = new MyList<>();
        list.add(10.2);
        list.add(3.4);
        assertEquals(10.2, list.get(0));
    }

    @Test
    void testUnsuccessfulGet()
    {
        MyList<Double> list = new MyList<>();
        list.add(10.2);
        list.add(3.4);
        assertThrowsExactly(ADTException.class, () -> list.get(-1));
    }


    @Test
    void testToString()
    {
        MyList<String> list = new MyList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        assertEquals("[one, two, three]", list.toString());
    }
}