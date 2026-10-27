package interpreter.view;

import interpreter.controller.Controller;
import interpreter.model.PrgState;
import interpreter.model.adt.dictionary.MyDictionary;
import interpreter.model.adt.list.MyList;
import interpreter.model.adt.stack.MyStack;
import interpreter.model.exp.ArithExp;
import interpreter.model.exp.ArithOp;
import interpreter.model.exp.ValueExp;
import interpreter.model.exp.VarExp;
import interpreter.model.stmt.*;
import interpreter.model.type.BoolType;
import interpreter.model.type.IntType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import interpreter.repository.IRepository;
import interpreter.repository.Repository;

import java.util.Locale;
import java.util.Scanner;

public class View {

    public void start()
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            printMenu();
            System.out.print("Select option: ");
            String input = scanner.nextLine().trim();

            switch (input)
            {
                case "1" -> runProgram(getExample1());
                case "2" -> runProgram(getExample2());
                case "3" -> runProgram(getExample3());
                case "0" -> {
                    System.out.println("Exiting application!");
                    return;
                }
                default -> System.out.println("Invalid option! Pretty please try again!");
            }
        }
    }

    private void printMenu() {
        System.out.println("1. Run example 1");
        System.out.println("2. Run example 2");
        System.out.println("3. Run example 3");
        System.out.println("0. Exit");
    }

    private void runProgram(IStmt program)
    {
        PrgState state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), program);
        Controller controller = new Controller(new Repository(state), true);

        try {
            controller.allStep();
            System.out.println("Execution completed successfully!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private IStmt getExample1()
    {
        return new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))
                )
        );
    }

    private IStmt getExample2()
    {
        return new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a",
                                new ArithExp(ArithOp.ADD, new ValueExp(new IntValue(2)),
                                        new ArithExp(ArithOp.MUL, new ValueExp(new IntValue(3)),
                                                new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",
                                        new ArithExp(ArithOp.ADD, new VarExp("a"),
                                                new ValueExp(new IntValue(1)))),
                                        new PrintStmt(new VarExp("b"))))));
    }

    private IStmt getExample3()
    {
        return  new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),
                                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));
    }
}
