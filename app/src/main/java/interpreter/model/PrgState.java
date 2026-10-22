package interpreter.model;

import interpreter.model.adt.dictionary.IDictionary;
import interpreter.model.adt.list.IList;
import interpreter.model.adt.stack.IStack;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;

public class PrgState {
    private final IStack<IStmt> exeStack;
    private final IDictionary<String, IValue> symTable;
    private final IList<IValue> out;
    private final IStmt originalProgram;

    public PrgState(IStack<IStmt> exeStack, IDictionary<String, IValue> symTable, IList<IValue> out, IStmt originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;

        this.exeStack.push(originalProgram);
    }

    public IStack<IStmt> getExeStack()
    {
        return this.exeStack;
    }

    public IDictionary<String, IValue> getSymTable()
    {
        return this.symTable;
    }

    public IList<IValue> getOut()
    {
        return this.out;
    }

    public IStmt getOriginalProgram()
    {
        return this.originalProgram;
    }

    @Override
    public String toString()
    {
        return "=== Execution Stack ===\n" + exeStack + "\n" +
                "=== Symbol Table ===\n" + symTable + "\n" +
                "=== Output List ===\n" + out + "\n";
    }
}
