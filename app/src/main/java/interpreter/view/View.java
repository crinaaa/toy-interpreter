package interpreter.view;

import interpreter.controller.Controller;
import interpreter.model.adt.dictionary.MyDictionary;
import interpreter.model.adt.list.MyList;
import interpreter.model.adt.stack.MyStack;
import interpreter.model.PrgState;
import interpreter.model.stmt.IStmt;
import interpreter.repository.Repository;

import java.util.Scanner;

public class View {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("Select option: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> runProgram(Examples.getExample1());
                case "2" -> runProgram(Examples.getExample2());
                case "3" -> runProgram(Examples.getExample3());
                case "0" -> {
                    System.out.println("Exiting application.");
                    return;
                }
                default -> System.out.println("Invalid option! Please try again.\n");
            }
        }
    }

    private void printMenu() {
        System.out.println("==========================================");
        System.out.println("         Toy Language Interpreter         ");
        System.out.println("==========================================");
        System.out.println("1 - Run Example 1: int v; v=2; print(v)");
        System.out.println("2 - Run Example 2: int a; int b; a=2+3*5; b=a+1; print(b)");
        System.out.println("3 - Run Example 3: bool a; int v; a=true; (if a then v=2 else v=3); print(v)");
        System.out.println("0 - Exit");
        System.out.println("==========================================");
    }

    private void runProgram(IStmt program) {
        PrgState state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), program);
        Controller controller = new Controller(new Repository(state), true);

        try {
            controller.allStep();
            System.out.println("\nExecution completed successfully.");
            System.out.println("Final Output: " + state.getOut() + "\n");
        } catch (Exception e) {
            System.out.println("\nExecution error caught by view: " + e.getMessage() + "\n");
        }
    }
}