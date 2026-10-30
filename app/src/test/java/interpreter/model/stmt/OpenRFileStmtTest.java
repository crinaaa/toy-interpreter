package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class OpenRFileStmtTest {

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

    @AfterEach
    void tearDown() throws IOException, MyException {
        StringValue fileName = (StringValue) file.eval(state.getSymTable());
        if (state.getFileTable().isDefined(fileName)) {
            BufferedReader reader = state.getFileTable().lookup(fileName);
            reader.close();
        }
    }

    @Test
    void testOpenRFileAddsToFileTable() throws MyException {
        OpenRFileStmt stmt = new OpenRFileStmt(file);
        stmt.execute(state);

        StringValue fileName = (StringValue) file.eval(state.getSymTable());
        assertTrue(state.getFileTable().isDefined(fileName));
    }

    @Test
    void testOpenRFileNotStringExpThrows() {
        OpenRFileStmt stmt = new OpenRFileStmt(new ValueExp(new IntValue(10)));
        assertThrows(StatementException.class, () -> stmt.execute(state));
    }

    @Test
    void testOpenRFileAlreadyOpenThrows() {
        assertDoesNotThrow(() -> new OpenRFileStmt(file).execute(state));
        assertThrows(StatementException.class, () -> new OpenRFileStmt(file).execute(state));
    }

    @Test
    void testOpenRFileNonExistentFileThrows() {
        ValueExp missingExp = new ValueExp(new StringValue(dir.resolve("missing.in").toString()));
        assertThrows(StatementException.class, () -> new OpenRFileStmt(missingExp).execute(state));
    }
}