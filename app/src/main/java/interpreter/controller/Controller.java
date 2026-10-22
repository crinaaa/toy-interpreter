package interpreter.controller;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.stack.IStack;
import interpreter.model.stmt.IStmt;
import interpreter.repository.IRepository;

public class Controller {
    private final IRepository repo;
    private boolean displayFlag;

    public Controller(IRepository repo)
    {
        this.repo = repo;
        this.displayFlag = true;
    }

    public Controller(IRepository repo, boolean displayFlag)
    {
        this.repo = repo;
        this.displayFlag = displayFlag;
    }

    public void setDisplayFlag (boolean displayFlag)
    {
        this.displayFlag = displayFlag;
    }

    public boolean getDisplayFlag()
    {
        return this.displayFlag;
    }

    public IRepository getRepo()
    {
        return this.repo;
    }

    public void display(PrgState state)
    {
        if (displayFlag)
            System.out.println(state);
    }

    public PrgState oneStep(PrgState state) throws MyException
    {
        var stack = state.getExeStack();
        if (stack.isEmpty())
            throw new MyException("program state is empty");
        IStmt crtStmt = stack.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws MyException
    {
        PrgState prg = repo.getCrtPrg();
        display(prg);
        while (!prg.getExeStack().isEmpty())
        {
            oneStep(prg);
            display(prg);
        }
    }
}
