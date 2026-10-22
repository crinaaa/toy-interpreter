package interpreter.model.adt.stack;

import interpreter.exceptions.ADTException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class MyStack<T> implements IStack<T>{
    private final Deque<T> stack;

    public MyStack()
    {
        this.stack = new ArrayDeque<>();
    }

    @Override
    public void push(T element) {
        this.stack.push(element);
    }

    @Override
    public T pop() throws ADTException {
        if (this.stack.isEmpty())
            throw new ADTException("Cannot pop from an empty stack!");
        return this.stack.pop();
    }

    @Override
    public T top() throws ADTException {
        if (this.stack.isEmpty())
            throw new ADTException("Cannot get top element from an empty stack!");
        return this.stack.peek();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    @Override
    public List<T> toList() {
        return this.stack.stream().toList();
    }

    @Override
    public String toString()
    {
        return this.stack.toString();
    }
}