package interpreter.model.adt.stack;

import interpreter.exceptions.ADTException;

import java.util.List;

public interface IStack<T> {
    void push(T element);
    T pop() throws ADTException;
    T top() throws ADTException;
    boolean isEmpty();
    int size();
    List<T> toList();
    String toString();
}
