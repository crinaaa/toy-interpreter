package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public record ReadFileStmt(IExp exp, String varName) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        var symTable = state.getSymTable();

        if (!symTable.isDefined(varName))
            throw new StatementException("readFile: Variable '" + varName + "' is not declared!");

        if (!symTable.lookup(varName).getType().equals(new IntType()))
            throw new StatementException("readFile: Variable '" + varName + "' is not of type INT!");

        var value = exp.eval(symTable);
        if (!(value instanceof StringValue fileName))
            throw new StatementException("readFile: Expression '" + exp + "' is not a string!");

        var fileTable = state.getFileTable();
        if (!fileTable.isDefined(fileName))
            throw new StatementException("readFile: File '" + fileName + "' is not opened in FileTable!");

        BufferedReader reader =  fileTable.lookup(fileName);

        try {
            String line = reader.readLine();
            int num;
            if (line == null)
                num = 0;
            else
                num = Integer.parseInt(line.trim());

            symTable.update(varName, new IntValue(num));
        } catch (IOException e) {
            throw new StatementException("readFile: Could not read from file " + fileName + ": " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new StatementException("readFile: Line in file " + fileName + " is not a valid integer!");
        }

        return state;
    }

    @Override
    public String toString()
    {
        return "readFile(" + exp + ", " + varName + ")";
    }
}