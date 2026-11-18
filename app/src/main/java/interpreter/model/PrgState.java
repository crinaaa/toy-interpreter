package interpreter.model;

import interpreter.model.adt.dictionary.IDictionary;
import interpreter.model.adt.dictionary.MyDictionary;
import interpreter.model.adt.heap.IHeap;
import interpreter.model.adt.heap.MyHeap;
import interpreter.model.adt.list.IList;
import interpreter.model.adt.list.MyList;
import interpreter.model.adt.stack.IStack;
import interpreter.model.adt.stack.MyStack;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;
import interpreter.model.value.StringValue;
import java.io.BufferedReader;
import java.util.Map;

public class PrgState {
    private IStack<IStmt> exeStack;
    private IDictionary<String, IValue> symTable;
    private IList<IValue> out;
    private final IStmt originalProgram;
    private IDictionary<StringValue, BufferedReader> fileTable;

    //added for A4
    private IHeap heap;

    public PrgState(IStack<IStmt> exeStack, IDictionary<String, IValue> symTable,
                    IList<IValue> out, IDictionary<StringValue, BufferedReader> fileTable, IHeap heap, IStmt originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.fileTable = fileTable;
        this.heap = heap;

        this.exeStack.push(originalProgram);
    }

    public static PrgState of(IStmt originalProgram)
    {
        return new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
                new MyDictionary<>(), new MyHeap(), originalProgram);
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
    public IDictionary<StringValue, BufferedReader> getFileTable() { return this.fileTable;}
    public IStmt getOriginalProgram()
    {
        return this.originalProgram;
    }
    public IHeap getHeap() { return this.heap;}

    public void setExeStack(IStack<IStmt> exeStack) { this.exeStack = exeStack;}
    public void setSymTable(IDictionary<String, IValue> symTable) {this.symTable = symTable;}
    public void setOut(IList<IValue> out) {this.out = out;}
    public void setFileTable(IDictionary<StringValue, BufferedReader> fileTable)
            {this.fileTable = fileTable;}

    @Override
    public String toString()
    {
        return "=== Execution Stack ===\n" + exeStack + "\n" +
                "=== Symbol Table ===\n" + symTable + "\n" +
                "=== Output List ===\n" + out + "\n" +
                "=== File Table ===\n" + fileTable + "\n" +
                "=== Heap ===\n" + heap;
    }

    public String toLogString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("ExeStack:\n");
        exeStack.toList().forEach(s -> sb.append(s).append("\n"));

        sb.append("SymTable:\n");
        symTable.toMap().forEach((k, v) -> sb.append(k).append(" --> ").append(v).append('\n'));

        sb.append("Out:\n");
        out.toList().forEach(v -> sb.append(v).append('\n'));

        sb.append("FileTable:\n");
        for (StringValue fileName : fileTable.toMap().keySet()) {
            sb.append(fileName.val()).append("\n");
        }

        sb.append("Heap:\n");
        heap.getContent().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry ->
                        sb.append(entry.getKey())
                                .append("-->")
                                .append(entry.getValue())
                                .append('\n'));

        return sb.toString();
    }
}
