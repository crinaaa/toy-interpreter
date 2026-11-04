package interpreter.model.stmt;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.type.StringType;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileStmtTest {
    @TempDir
    Path dir;

    private PrgState state;
    private ValueExp file;
    private StringValue fileVal;

    @BeforeEach
    void setUp() throws Exception {
        Path f = dir.resolve("test.in");
        Files.writeString(f, "42\n");
        fileVal = new StringValue(f.toString());
        file = new ValueExp(fileVal);

        state = PrgState.of(new NopStmt());
        new VarDeclStmt("v", new IntType()).execute(state);
        new OpenRFileStmt(file).execute(state);
    }

    @AfterEach
    void tearDown() throws IOException, ADTException {
        if (state != null && state.getFileTable().isDefined(fileVal)) {
            BufferedReader reader = state.getFileTable().lookup(fileVal);
            reader.close();
        }
    }

    @Test
    void testReadFileReadsValueAndEOFZero() throws Exception {
        ReadFileStmt stmt = new ReadFileStmt(file, "v");

        // read line 1 -> 42
        stmt.execute(state);
        assertEquals(new IntValue(42), state.getSymTable().lookup("v"));

        // read EOF -> 0
        stmt.execute(state);
        assertEquals(new IntValue(0), state.getSymTable().lookup("v"));
    }

    @Test
    void testReadFileUndeclaredVarThrows() {
        ReadFileStmt stmt = new ReadFileStmt(file, "undeclared");
        assertThrows(StatementException.class, () -> stmt.execute(state));
    }

    @Test
    void testReadFileNonIntVarThrows() throws Exception {
        new VarDeclStmt("s", new StringType()).execute(state);
        ReadFileStmt stmt = new ReadFileStmt(file, "s");
        assertThrows(StatementException.class, () -> stmt.execute(state));
    }

    @Test
    void testReadFileNotOpenInTableThrows() throws Exception {
        new CloseRFileStmt(file).execute(state);
        ReadFileStmt stmt = new ReadFileStmt(file, "v");
        assertThrows(StatementException.class, () -> stmt.execute(state));
    }
}