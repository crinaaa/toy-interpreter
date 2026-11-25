package interpreter.model.stmt;

import interpreter.controller.Controller;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.*;
import interpreter.model.type.IntType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import interpreter.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class WhileStmtTest {
    private PrgState state;
    private final IStmt body = new PrintStmt(new ValueExp(new IntValue(1)));

    @BeforeEach
    void setUp()
    {
        state = PrgState.of(new NopStmt());
    }

    @Test
    void falseConditionPushesNothing() throws MyException
    {
        new WhileStmt(new ValueExp(new BoolValue(false)), body).execute(state);
        assertEquals(new NopStmt(), state.getExeStack().pop());
        assertTrue(state.getExeStack().isEmpty());
    }

    @Test
    void trueConditionPushesTheLoopThenTheBody() throws MyException {
        var loop = new WhileStmt(new ValueExp(new BoolValue(true)), body);
        loop.execute(state);
        assertEquals(body, state.getExeStack().pop());
        assertEquals(loop, state.getExeStack().pop());
    }

    @Test
    void nonBooleanConditionThrows() {
        var loop = new WhileStmt(new ValueExp(new IntValue(1)), body);
        assertThrows(StatementException.class, () -> loop.execute(state));
    }

    @TempDir
    Path tempDir;

    @Test
    void whileExecutesSeveralIterations() throws MyException, MyException {
        IStmt program = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v",
                                new ValueExp(new IntValue(3))),
                        new CompStmt(
                                new WhileStmt(
                                        new RelationalExp(
                                                RelOp.GT,
                                                new VarExp("v"),
                                                new ValueExp(new IntValue(0))
                                        ),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("v")),
                                                new AssignStmt(
                                                        "v",
                                                        new ArithExp(
                                                                ArithOp.SUB,
                                                                new VarExp("v"),
                                                                new ValueExp(new IntValue(1))
                                                        )
                                                )
                                        )
                                ),
                                new PrintStmt(new VarExp("v"))
                        )
                )
        );

        PrgState state = PrgState.of(program);
        Repository repository = new Repository(
                state,
                tempDir.resolve("test.log").toString()
        );
        Controller controller = new Controller(repository, false);

        controller.allStep();

        assertEquals(
                List.of(
                        new IntValue(3),
                        new IntValue(2),
                        new IntValue(1),
                        new IntValue(0)
                ),
                state.getOut().toList()
        );
    }
}