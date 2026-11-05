# Architecture

This document describes the architecture of the Toy Language Interpreter and the responsibilities of its main components.

The application is organized into layers to separate the representation of the toy language from its execution and user interaction.

## 1. Model

The `model` package contains the elements needed to represent and execute programs written in the toy language.

### Program State

`PrgState` represents the state of a program during execution. It maintains:

- **Execution stack** — stores the statements that still need to be executed.
- **Symbol table** — maps variable names to their current values.
- **Output list** — stores the values produced by print statements.
- **File table** — keeps track of currently opened files and their associated readers.
- **Original program** — stores the initial statement of the program.

The program state is updated as statements are executed.

### Statements

The `stmt` package defines the statements supported by the language. Each statement implements the `IStmt` interface and provides an `execute()` method.

Implemented statements include:

- Variable declaration
- Variable assignment
- Compound (sequential) statements
- Conditional statements
- Print statements
- File opening, reading, and closing

### Expressions

The `exp` package contains the expressions that can be evaluated by the interpreter.

The implemented expression types include:

- Constant values
- Variable references
- Arithmetic expressions
- Relational expressions

Expressions are evaluated using the current program state.

### Types and Values

The `type` and `value` packages represent the types and values supported by the toy language, including integers, Booleans, and strings.

## 2. Abstract Data Types

The `model.adt` package contains custom implementations of the data structures used by the interpreter:

- **Stack** — stores statements waiting to be executed.
- **Dictionary** — stores variable bindings and opened file references.
- **List** — stores program output.

These implementations are accessed through interfaces, keeping their usage separate from their internal representation.

## 3. Controller

The `Controller` coordinates program execution.

Its main responsibilities include:

- Retrieving the current program state from the repository.
- Executing one statement at a time.
- Running the program until its execution stack becomes empty.
- Logging program states during execution.
- Optionally displaying execution information.

The controller delegates the actual execution of each statement to its corresponding implementation.

## 4. Repository

The repository stores the program state and manages execution logs.

Its responsibilities include:

- Providing access to the current program state.
- Recording program state information in a log file.

This separates state storage and logging from the controller's execution logic.

## 5. View

The `view` package provides a text-based interface for interacting with the application.

It contains:

- `TextMenu` — displays the available commands and handles user selection.
- `RunExampleCommand` — executes a selected example program.
- `ExitCommand` — terminates the application.
- `Examples` — defines the predefined programs available in the menu.

Each example is associated with its own repository and controller.

## 6. Execution Flow

The execution process follows these steps:

1. The application initializes the predefined examples.
2. The user selects an example from the menu.
3. A repository and controller are created for that example.
4. The controller retrieves the current program state.
5. The next statement is removed from the execution stack and executed.
6. The statement updates the program state as needed.
7. Execution continues until there are no more statements to execute.
8. The final output and execution information can be inspected.

## 7. Exception Handling

The project uses custom exceptions to handle errors that may occur during program execution.

These include errors related to invalid operations on data structures, expression evaluation, statement execution, and file operations.

This allows invalid operations to be reported explicitly instead of silently producing incorrect results.