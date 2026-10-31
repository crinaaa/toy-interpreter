package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CloseRFileStmtTest {

    @TempDir
    Path dir;

    private PrgState state;
    private ValueExp file;

    @BeforeEach
    void setUp() throws Exception {
        Path f = dir.resolve("numbers.in");
        Files.writeString(f, "7\n");
        file = new ValueExp(new StringValue(f.toString()));
        state = PrgState.of(new NopStmt());
        new VarDeclStmt("x", new IntType()).execute(state);
    }

    @Test
    void testCloseRFileRemovesFileFromFileTable() throws MyException {
        new OpenRFileStmt(file).execute(state);

        StringValue fileName = (StringValue) file.eval(state.getSymTable());
        assertTrue(state.getFileTable().isDefined(fileName));

        CloseRFileStmt closeStmt = new CloseRFileStmt(file);
        closeStmt.execute(state);

        assertFalse(state.getFileTable().isDefined(fileName));
    }

    @Test
    void testCloseRFileNotOpenFileThrowsException() {
        CloseRFileStmt closeStmt = new CloseRFileStmt(file);
        assertThrows(StatementException.class, () -> closeStmt.execute(state));
    }

    @Test
    void testCloseRFileNonStringExpThrowsException() {
        CloseRFileStmt closeStmt = new CloseRFileStmt(new ValueExp(new IntValue(5)));
        assertThrows(StatementException.class, () -> closeStmt.execute(state));
    }
}