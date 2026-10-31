package interpreter.repository;

import interpreter.exceptions.MyException;
import interpreter.exceptions.RepositoryException;
import interpreter.model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private final List<PrgState> states;
    private final String logFilePath;

//    public Repository()
//    {
//        this.states = new ArrayList<>();
//    }

    public Repository(PrgState initialState, String logFilePath)
    {
        this.states = new ArrayList<>();
        this.states.add(initialState);
        this.logFilePath = logFilePath;
    }

    @Override
    public PrgState getCrtPrg() {
        return states.getFirst();
    }

    @Override
    public void addProgram(PrgState program) {
        states.add(program);
    }

    @Override
    public void logPrgStateExec() throws MyException {
        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
            logFile.println(getCrtPrg().toLogString());
        } catch (IOException e) {
            throw new RepositoryException("Could not write to log file " + logFilePath + ": "
                    + e.getMessage());
        }
    }
}
