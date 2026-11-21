package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.dictionary.IDictionary;
import interpreter.model.adt.heap.IHeap;
import interpreter.model.value.IValue;

public record ValueExp(IValue e) implements IExp {
    @Override
    public IValue eval(IDictionary<String, IValue> symTable, IHeap heap) throws ExpressionException
    {
        return e;
    }

    @Override
    public String toString()
    {
        return e.toString();
    }
}
