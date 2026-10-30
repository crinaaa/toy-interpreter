package interpreter.model.adt.list;

import interpreter.exceptions.ADTException;

import java.util.List;

public interface IList<T> {
    void add(T element);
    T remove(int index) throws ADTException;
    T get(int index) throws ADTException;
    int size();
    String toString();
    List<T> toList();
    boolean isEmpty();
}
