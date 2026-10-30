package interpreter.model.adt.list;

import interpreter.exceptions.ADTException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IList<T>{
    private final List<T> list;

    public MyList()
    {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        this.list.add(element);
    }

    @Override
    public T remove(int index) throws ADTException {
        if (index < 0 || index >= this.list.size())
            throw new ADTException("Index out of bounds!");
        return this.list.remove(index);
    }

    @Override
    public T get(int index) throws ADTException {
        if (index < 0 || index >= this.list.size())
            throw new ADTException("Index out of bounds!");
        return this.list.get(index);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public String toString() {
        return this.list.toString();
    }

    @Override
    public List<T> toList() {
        return new ArrayList<>(this.list);
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }
}
