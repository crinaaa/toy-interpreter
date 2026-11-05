# Testing

The project uses **JUnit 6** for unit testing.

The tests cover the interpreter's main execution components, including the controller, statement implementations, and file operations.

## 1. Controller Tests

`ControllerTest` verifies the behaviour of the controller and the execution of predefined programs.

The tests cover:

- Handling an empty execution stack.
- Executing the predefined examples.
- Verifying the values produced by program execution.
- Checking the controller's display configuration.
- Verifying program state logging.
- Executing programs that perform file operations.
- Checking that opened files are removed from the file table after being closed.

The predefined examples exercise variable declarations, assignments, arithmetic expressions, conditional statements, printing, and file operations.

## 2. File Operation Tests

`ReadFileStmtTest` focuses on the behaviour of the file-reading statement.

The tests cover:

- Reading an integer from a file.
- Handling the end of a file.
- Attempting to read into an undeclared variable.
- Handling variables with an incompatible type.
- Attempting to read from a file that is not open.

Temporary directories and files are used where needed to keep file-related tests isolated.

## 3. Running the Tests

The project uses Gradle's test task to execute the test suite.

On Windows:

```bash
gradlew.bat :app:test
```

On Linux or macOS:

```bash
./gradlew :app:test
```

Gradle automatically compiles the application and executes the tests using the configured JUnit Platform.