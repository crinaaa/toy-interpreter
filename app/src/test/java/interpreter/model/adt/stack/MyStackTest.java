package interpreter.model.adt.stack;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.StackEmptyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    @Test
    void testPushAndSize() {
        MyStack<Integer> stack = new MyStack<>();
        assertTrue(stack.Empty());
        assertEquals(0, stack.size());

        stack.push(10);
        stack.push(20);

        assertFalse(stack.Empty());
        assertEquals(2, stack.size());
    }

    @Test
    void testSuccessfulTop() throws ADTException {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.top());
        assertEquals(2, stack.size());
    }

    @Test
    void testUnsuccessfulTop() {
        MyStack<Integer> stack = new MyStack<>();
        assertThrowsExactly(ADTException.class, stack::top);
    }

    @Test
    void testSuccessfulPop() throws ADTException {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.pop());
        assertEquals(1, stack.size());
    }

    @Test
    void testUnsuccessfulPop() {
        MyStack<Integer> stack = new MyStack<>();
        assertThrowsExactly(ADTException.class, stack::top);
    }

    @Test
    void testToList() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        List<Integer> list = stack.toList();

        assertEquals(3, list.size());
        assertEquals(2, list.get(1));
    }

    @Test
    void testToString() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals("[3, 2, 1]", stack.toString());
    }
}