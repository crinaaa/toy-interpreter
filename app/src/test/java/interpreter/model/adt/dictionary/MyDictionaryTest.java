package interpreter.model.adt.dictionary;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MyDictionaryTest {
    @Test
    void testPutAndLookup() throws ADTException
    {
        MyDictionary<String, Integer> dict = new MyDictionary<>();
        dict.put("a", 1);
        dict.put("b", 2);

        assertEquals(1, dict.lookup("a"));
        assertEquals(2, dict.lookup("b"));
    }

    @Test
    void testIsDefined() {
        MyDictionary<String, Integer> dict = new MyDictionary<>();
        dict.put("x", 100);

        assertTrue(dict.isDefined("x"));
        assertFalse(dict.isDefined("y"));
    }

    @Test
    void testUpdateSuccessful() throws ADTException {
        MyDictionary<String, Integer> dict = new MyDictionary<>();
        dict.put("a", 10);
        dict.update("a", 20);

        assertEquals(20, dict.lookup("a"));
    }

    @Test
    void testUpdateThrowsException() {
        MyDictionary<String, Integer> dict = new MyDictionary<>();
        assertThrowsExactly(ADTException.class, () -> dict.update("unkown", 50));
    }

    @Test
    void testLookupThrowsException() {
        MyDictionary<String, Integer> dict = new MyDictionary<>();
       assertThrowsExactly(ADTException.class, () -> dict.lookup("missing"));
    }

    @Test
    void testRemoveSuccessful() throws ADTException {
        MyDictionary<String, Integer> dict = new MyDictionary<>();
        dict.put("key", 99);
        assertTrue(dict.isDefined("key"));

        dict.remove("key");
        assertFalse(dict.isDefined("key"));
    }

    @Test
    void testRemoveThrowsException() {
        MyDictionary<String, Integer> dict = new MyDictionary<>();
        assertThrowsExactly(ADTException.class, () -> dict.remove("key"));
    }

    @Test
    void testGetContent() {
        MyDictionary<String, Integer> dict = new MyDictionary<>();
        dict.put("a", 1);
        dict.put("b", 2);

        Map<String, Integer> content = dict.getContent();
        assertEquals(2, content.size());
        assertEquals(1, content.get("a"));
        assertEquals(2, content.get("b"));
    }

    @Test
    void testToString() {
        MyDictionary<String, Integer> dict = new MyDictionary<>();
        dict.put("a", 1);

        assertEquals("{a=1}", dict.toString());
    }
}