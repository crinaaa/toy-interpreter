package interpreter.model.adt.stack;

import interpreter.exceptions.StackEmptyException;
import java.util.List;

public interface IStack<T> {
    void push(T element);
    T pop() throws StackEmptyException;
    T top() throws StackEmptyException;
    boolean Empty();
    int size();
    List<T> toList();
    String toString();
}
