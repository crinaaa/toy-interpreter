package interpreter.model.adt.stack;

import interpreter.exceptions.StackEmptyException;

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
    public T pop() throws StackEmptyException {
        if (this.stack.isEmpty())
            throw new StackEmptyException("Cannot pop from an empty stack!");
        return this.stack.pop();
    }

    @Override
    public T top() throws StackEmptyException {
        if (this.stack.isEmpty())
            throw new StackEmptyException("Cannot get top element from an empty stack!");
        return this.stack.peek();
    }

    @Override
    public boolean Empty() {
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