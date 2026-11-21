package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.model.adt.dictionary.IDictionary;
import interpreter.model.adt.heap.IHeap;
import interpreter.model.value.IValue;

public interface IExp {
    IValue eval(IDictionary<String, IValue> symTable, IHeap heap) throws MyException;
}
