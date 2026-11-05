# Toy Language Interpreter

A Java implementation of a toy programming language interpreter, developed as part of my Computer Science coursework at Babeș-Bolyai University.

The project demonstrates the fundamental components of an interpreter, including expression evaluation, statement execution, program state management, and file operations.

## Features

The implemented toy language supports:

- **Variable declarations and assignments** — variables with integer, Boolean, and string types.
- **Arithmetic expressions** — addition, subtraction, multiplication, and division.
- **Relational expressions** — less than, less than or equal to, equality, inequality, greater than, and greater than or equal to.
- **Conditional statements** — `if-then-else` execution.
- **Sequential statements** — combining multiple statements into a program.
- **Output operations** — printing evaluated expressions.
- **File operations** — opening files, reading integer values, and closing files.

The interpreter also includes custom implementations of fundamental data structures and a mechanism for logging program execution.

## Architecture

The project follows a layered structure, separating the language model, execution logic, repository, and user interface.

| Component | Responsibility |
|---|---|
| Model | Represents program states, statements, expressions, types, and values. |
| ADTs | Provides custom implementations of stacks, lists, and dictionaries. |
| Controller | Coordinates statement execution. |
| Repository | Stores program states and handles execution logging. |
| View | Provides a text-based menu for selecting and running examples. |

For more details, see [Architecture](docs/architecture.md).

## Technologies

- Java 25
- Gradle
- JUnit 6

## Getting Started

### Prerequisites

- JDK 25
- Git

The project includes the Gradle Wrapper, so a separate Gradle installation is not required.

### Clone the repository

```bash
git clone https://github.com/crinaaa/toy-interpreter.git
cd toy-interpreter
```

### Run the application

On Windows:

```bash
gradlew.bat :app:run
```

On Linux or macOS:

```bash
./gradlew :app:run
```

The application displays a text-based menu containing five predefined example programs. Select an example to execute it or choose the exit option to close the application.

### Run the tests

On Windows:

```bash
gradlew.bat :app:test
```

On Linux or macOS:

```bash
./gradlew :app:test
```

## Project Structure

```text
toy-interpreter/
├── app/
│   └── src/
│       ├── main/java/interpreter/
│       │   ├── controller/
│       │   ├── exceptions/
│       │   ├── model/
│       │   │   ├── adt/
│       │   │   ├── exp/
│       │   │   ├── stmt/
│       │   │   ├── type/
│       │   │   └── value/
│       │   ├── repository/
│       │   ├── view/
│       │   └── Main.java
│       └── test/java/interpreter/
├── docs/
│   ├── architecture.md
│   └── testing.md
├── gradle/
├── gradlew
├── gradlew.bat
└── settings.gradle.kts
```

## Documentation

- [Architecture](docs/architecture.md) — overview of the main components and their responsibilities.
- [Testing](docs/testing.md) — description of the test coverage and how to run the tests.