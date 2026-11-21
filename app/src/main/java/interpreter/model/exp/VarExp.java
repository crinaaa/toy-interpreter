package interpreter.model.exp;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.dictionary.IDictionary;
import interpreter.model.adt.heap.IHeap;
import interpreter.model.value.IValue;

public record VarExp(String id) implements IExp {
    @Override
    public IValue eval(IDictionary<String, IValue> symTable, IHeap heap) throws ADTException {
        return symTable.lookup(id);
    }

    @Override
    public String toString()
    {
        return id;
    }
}
