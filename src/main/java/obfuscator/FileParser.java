package obfuscator;
import com.github.javaparser.ast.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;



/**
 * Created by alex on 4/1/2017.
 */
public class FileParser {

    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        File test = new File("test.java");
        //System.out.println(test.getCanonicalPath());
        FileInputStream in = new FileInputStream(test);

        // parse the file
        CompilationUnit cu = JavaParser.parse(in);

        // prints the resulting compilation unit to default system output
        //System.out.println(cu.toString());

        //Change all methods using visitor
        new MethodChangerVisitor().visit(cu, null);
        //System.out.println(cu.toString());

        //Insert a method into the class
        new ClassChangerVisitor().visit(cu, null);
        System.out.println(cu.toString());

        //Insert predicates using
        new InsertOpaquePredicates().visit(cu, null);
        System.out.println(cu.toString());

        //Make a directory for obfuscated code
        File theDir = new File("Obfuscated Source");
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
            }
            catch(SecurityException se){
                //handle it
            }
            if(result) {
                System.out.println("DIR created");
            }
        }

        File obfuscatedFile = new File(theDir, "test.java");
        BufferedWriter writer = new BufferedWriter( new FileWriter(obfuscatedFile));
        writer.write(cu.toString());
        writer.close();
    }
    private static class MethodChangerVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            // change the name of the method to upper case
            //n.setName(n.getNameAsString().toUpperCase());

            // add a new parameter to the method
           // n.addParameter("int", "value");

            //Insert calls to generated dead methods
            BlockStmt block = n.getBody().get();
            int blockLength = block.getStatements().size();
            NameExpr clazz = new NameExpr("this");

            //Using a random number to decide the position of the call within the method blck
            MethodCallExpr call = new MethodCallExpr(clazz,"processData");
            block.addStatement(ThreadLocalRandom.current().nextInt(0, blockLength),call);
            MethodCallExpr callTwo = new MethodCallExpr(clazz,"checkPrimaryCondition").addArgument("5");
            block.addStatement(ThreadLocalRandom.current().nextInt(0, blockLength),callTwo);
            MethodCallExpr callThree = new MethodCallExpr(clazz,"computeService");
            block.addStatement(ThreadLocalRandom.current().nextInt(0, blockLength),callThree);
        }

    }

    /*
    This is the javaparser's representation of a class. The visit method will insert dead methods into the class
     */
    private static class ClassChangerVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            //Generate a simple method that returns a calculation
            MethodDeclaration fakeMethod = n.addMethod("processData",Modifier.PUBLIC);
            fakeMethod.setType("int");
            BlockStmt fakeMethodContent = new BlockStmt().addStatement(new ReturnStmt("5 * 5"));
            fakeMethod.setBody(fakeMethodContent);

            //Generate a simple method with an if else clause
            MethodDeclaration fakeMethodTwo = n.addMethod("checkPrimaryCondition",Modifier.PUBLIC);
            fakeMethodTwo.setType("boolean");
            fakeMethodTwo.addParameter(new Parameter().setType("int").setName("a"));
            BlockStmt fakeMethodContentTwo = new BlockStmt();

            //Generate the if else statement
            IfStmt ifStatement = new IfStmt();
            ifStatement.setCondition(new NameExpr("a == 0"));
            ifStatement.setThenStmt(new ReturnStmt("true"));
            ifStatement.setElseStmt(new ReturnStmt("false"));
            fakeMethodContentTwo.addStatement(ifStatement);
            fakeMethodTwo.setBody(fakeMethodContentTwo);

            //Generate a method that calls other generated methods
            MethodDeclaration fakeMethodRedirect = n.addMethod("computeService", Modifier.PUBLIC);
            fakeMethodRedirect.setType("void");
            BlockStmt fakeRedirect = new BlockStmt();
            fakeRedirect.addStatement(new NameExpr("int a = 1"));
            fakeRedirect.addStatement(new NameExpr("int b = 0"));

            //Generate an if else clause to choose which method to execute
            ConditionalExpr ifStatementTwo = new ConditionalExpr().setCondition(new NameExpr("a == b"));
            NameExpr clazz = new NameExpr("this");
            MethodCallExpr dataCall = new MethodCallExpr(clazz,"processData");
            MethodCallExpr conditionCall = new MethodCallExpr(clazz,"checkPrimaryCondition").addArgument("2");
            ifStatementTwo.setThenExpr(dataCall);
            ifStatementTwo.setElseExpr(conditionCall);

            fakeRedirect.addStatement(ifStatementTwo);
            fakeMethodRedirect.setBody(fakeRedirect);
        }
    }
    private static class InsertOpaquePredicates extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {

            //Set some fake variables which will be used by the predicates
            n.getBody().get().addStatement(0, new NameExpr("int cashFlowThreshold = 4, spendingTurnover = 10"));
            n.getBody().get().addStatement(1, new NameExpr("double avgTurnoverRatio = Math.random()*20"));

            //This predicate always evaluates to either True or False
            IfStmt pEither = new IfStmt();
            pEither.setCondition(new NameExpr("avgTurnoverRatio > spendingTurnover"));
            pEither.setThenStmt(new ReturnStmt("true"));
            pEither.setElseStmt(new ReturnStmt("false"));
            n.getBody().get().addStatement(2, pEither);

            //This predicate always evaluates to False
            IfStmt pFalse = new IfStmt();
            pFalse.setCondition(new NameExpr("spendingTurnover > (Math.pow(cashFlowThreshold, 3) * avgTurnoverRatio)"));
            pFalse.setThenStmt(new ReturnStmt("true"));
            pFalse.setElseStmt(new ReturnStmt("false"));
            n.getBody().get().addStatement(3, pFalse);

            //This predicate always evaluates to True
            IfStmt pTrue = new IfStmt();
            pTrue.setCondition(new NameExpr("spendingTurnover % 3 != 0"));
            pTrue.setThenStmt(new ReturnStmt("true"));
            pTrue.setElseStmt(new ReturnStmt("false"));
            n.getBody().get().addStatement(4, pTrue);
        }
    }

}

