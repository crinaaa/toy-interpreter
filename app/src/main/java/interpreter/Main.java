package interpreter;

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

public class Main {
    public static void main(String[] args) {
        // Example 1: int v; v=2; print(v)
//        IStmt ex1 = new CompStmt(
//                new VarDeclStmt("v", new IntType()),
//                new CompStmt(
//                        new AssignStmt("v", new ValueExp(new IntValue(2))),
//                        new PrintStmt(new VarExp("v"))
//                )
//        );

        // Ex2: int a; int b; a = 2 + 3 * 5; b = a + 1; print(b)
//        IStmt ex1 = new CompStmt(new VarDeclStmt("a", new IntType()),
//                new CompStmt(new VarDeclStmt("b", new IntType()),
//                        new CompStmt(new AssignStmt("a",
//                                new ArithExp(ArithOp.ADD, new ValueExp(new IntValue(2)),
//                                        new ArithExp(ArithOp.MUL, new ValueExp(new IntValue(3)),
//                                                new ValueExp(new IntValue(5))))),
//                                new CompStmt(new AssignStmt("b",
//                                        new ArithExp(ArithOp.ADD, new VarExp("a"),
//                                                new ValueExp(new IntValue(1)))),
//                                        new PrintStmt(new VarExp("b"))))));

        // Ex3: bool a; int v; a = true; (if a then v = 2 else v = 3); print(v)
        IStmt ex1 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),
                                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));

        PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex1);
        IRepository repo1 = new Repository(prg1);
        Controller controller1 = new Controller(repo1);

        try {
            System.out.println("--- Running Example 1 ---");
            controller1.allStep();
        } catch (Exception e) {
            System.out.println("Execution error: " + e.getMessage());
        }
    }
}