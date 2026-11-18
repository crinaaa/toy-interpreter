package interpreter.model.adt.heap;

import interpreter.exceptions.ADTException;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MyHeapTest {
    @Test
    void testAllocate()
    {
        MyHeap heap = new MyHeap();
        int address1 = heap.allocate(new IntValue(10));
        int address2 = heap.allocate(new IntValue(20));;

        assertEquals(1, address1);
        assertEquals(2, address2);

        assertTrue(heap.isDefined(address1));
        assertTrue(heap.isDefined(address2));
    }

    @Test
    void testGet() throws ADTException {
        MyHeap heap = new MyHeap();
        int address = heap.allocate(new IntValue(10));
        IValue value = heap.get(address);

        assertEquals(new IntValue(10), value);
    }

    @Test
    void testUpdate() throws ADTException {
        MyHeap heap = new MyHeap();

        int address = heap.allocate(new IntValue(20));
        heap.update(address, new IntValue(10));
        assertEquals(new IntValue(10), heap.get(address));
    }

    @Test
    void testGetForInvalidAddress() throws ADTException {
        MyHeap heap = new MyHeap();
        assertThrows(ADTException.class, () -> heap.get(100));
    }

    @Test
    void testUpdateInvalidAddress()
    {
        MyHeap heap = new MyHeap();
        assertThrows(ADTException.class, () -> heap.update(100, new IntValue(10)));
    }

    @Test
    void testIsDefined()
    {
        MyHeap heap = new MyHeap();
        assertFalse(heap.isDefined(1));

        int address = heap.allocate(new IntValue(10));
        assertTrue(heap.isDefined(address));
    }

    @Test
    void testGetContent()
    {
        MyHeap heap = new MyHeap();
        heap.allocate(new IntValue(10));
        heap.allocate(new IntValue(20));
        Map<Integer, IValue> content = heap.getContent();

        assertEquals(2, content.size());
        assertEquals(new IntValue(10), content.get(1));
        assertEquals(new IntValue(20), content.get(2));
    }

    @Test
    void testSetContent() throws ADTException {
        MyHeap heap = new MyHeap();
        heap.allocate(new IntValue(10));

        Map<Integer, IValue> newContent = new HashMap<>();
        newContent.put(5, new IntValue(50));
        newContent.put(7, new IntValue(70));

        heap.setContent(newContent);

        assertFalse(heap.isDefined(1));
        assertTrue(heap.isDefined(5));
        assertTrue(heap.isDefined(7));

        assertEquals(new IntValue(50), heap.get(5));
        assertEquals(new IntValue(70), heap.get(7));
    }

    @Test
    void testAllocateAfterSetContent() throws ADTException {
        MyHeap heap = new MyHeap();

        heap.allocate(new IntValue(10));
        heap.allocate(new IntValue(20));

        Map<Integer, IValue> newContent = new HashMap<>();
        newContent.put(1, new IntValue(10));

        heap.setContent(newContent);

        int newAddress = heap.allocate(new IntValue(30));

        assertEquals(3, newAddress);
        assertEquals(new IntValue(30), heap.get(newAddress));
    }
}