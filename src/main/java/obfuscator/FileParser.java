package obfuscator;
import com.github.javaparser.ast.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.AnnotationDeclaration;
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

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;


/**
 * Created by alex on 4/1/2017.
 */
public class FileParser {

    public static void main(String[] args) throws Exception {

        FileParser fileParser = new FileParser();

        //Get all files within the directory that is a java source file
        ArrayList<File> allFilesToObfuscate = fileParser.getFilesToObfuscate();
        allFilesToObfuscate.forEach(System.out::println);

        //Obfuscate them all
        for(File file : allFilesToObfuscate){
            Obfuscate(file);
        }

//        THIS IS CODE USED TO PERFORM TESTS ON THE TEST.JAVA FILE
//        // creates an input stream for the file to be parsed
//        File test = new File("test.java");
//        //System.out.println(test.getCanonicalPath());
//        FileInputStream in = new FileInputStream(test);
//
//        // parse the file
//        CompilationUnit cu = JavaParser.parse(in);
//
//        //Change all methods using visitor
//        new MethodChangerVisitor().visit(cu, null);
//        //System.out.println(cu.toString());
//
//        //Insert a method into the class
//        new ClassChangerVisitor().visit(cu, null);
//        System.out.println(cu.toString());
//
//        //Make a directory for obfuscated code
//        File theDir = new File("Obfuscated Source");
//        if (!theDir.exists()) {
//            System.out.println("creating directory: " + theDir.getName());
//            boolean result = false;
//
//            try{
//                theDir.mkdir();
//                result = true;
//            }
//            catch(SecurityException se){
//                //handle it
//            }
//            if(result) {
//                System.out.println("DIR created");
//            }
//        }
//
//        File obfuscatedFile = new File(theDir, "test.java");
//        BufferedWriter writer = new BufferedWriter( new FileWriter(obfuscatedFile));
//        writer.write(cu.toString());
//        writer.close();
    }

    private static void Obfuscate(File file)throws IOException{

        FileInputStream in = new FileInputStream(file);
        // parse the file
        CompilationUnit cu = JavaParser.parse(in);

        //Insert a method into the class
        new ClassChangerVisitor().visit(cu, null);
        //System.out.println(cu.toString());

        //Change all methods using visitor
        new MethodChangerVisitor().visit(cu, null);
        //System.out.println(cu.toString());



        //Overwrite the source files
        BufferedWriter writer = new BufferedWriter( new FileWriter(file, false));
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

            //Only inject calls if its not the method itself
            if(!n.getNameAsString().equals("processData") && !n.getNameAsString().equals("checkPrimaryCondition") && !n.getNameAsString().equals("computeService")){
                //Using a random number to decide the position of the call within the method blck
                MethodCallExpr call = new MethodCallExpr(clazz,"processData");
                block.addStatement(ThreadLocalRandom.current().nextInt(0, blockLength),call);
                MethodCallExpr callTwo = new MethodCallExpr(clazz,"checkPrimaryCondition").addArgument("5");
                block.addStatement(ThreadLocalRandom.current().nextInt(0, blockLength),callTwo);
                MethodCallExpr callThree = new MethodCallExpr(clazz,"computeService");
                block.addStatement(ThreadLocalRandom.current().nextInt(0, blockLength),callThree);
            }


            new ControlFlowFlattener().flat(n);
            new InsertOpaquePredicates().insertPredicates(n);
        }

    }

    /*
    This is the javaparser's representation of a class. The visit method will insert dead methods into the class
     */
    private static class ClassChangerVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {

            insertMethods(n);
            List<Node> nodes = n.getChildNodes();
            for(Node node : nodes){
                System.out.println(node.getClass());
                if(node.getClass() == ClassOrInterfaceDeclaration.class){
                    insertMethods((ClassOrInterfaceDeclaration) node);
                }
            }
        }

        private void insertMethods(ClassOrInterfaceDeclaration n ){
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
            IfStmt ifStatementTwo = new IfStmt().setCondition(new NameExpr("a == b"));
            NameExpr clazz = new NameExpr("this");
            MethodCallExpr dataCall = new MethodCallExpr(clazz,"processData");
            MethodCallExpr conditionCall = new MethodCallExpr(clazz,"checkPrimaryCondition").addArgument("2");
            ifStatementTwo.setThenStmt(new BlockStmt().addStatement(dataCall));
            ifStatementTwo.setElseStmt(new BlockStmt().addStatement(conditionCall));

            fakeRedirect.addStatement(ifStatementTwo);
            fakeMethodRedirect.setBody(fakeRedirect);
        }
    }


    private ArrayList<File> getFilesToObfuscate() throws URISyntaxException {
        //Grab the path of directory where this was ran (the folder with the source code to be obfuscated)
        String currentDir = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        currentDir = currentDir.substring(0, currentDir.lastIndexOf("/"));
        System.out.println(currentDir);
        File topDirectory = new File(currentDir);
        System.out.println("top directory is a dir? " + topDirectory.isDirectory());
        ArrayList<File> allFilesToObfuscate = new ArrayList<File>();
        addFiles(topDirectory, allFilesToObfuscate);

        return allFilesToObfuscate;
    }

    /*
    This is a helper method used to get all java files from a directory using recursion
     */
    private static void addFiles(File file, Collection<File> allFiles) {
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if(file.isFile()) {
                    String path = file.getAbsolutePath().toLowerCase();
                    System.out.println("path is :" + path);
                    if(path.endsWith(".java")){
                        //System.out.println("accepted");
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return true;
                }

            }
        });
        if (files != null) {
            for (File f : files) {

                //String path = file.getAbsolutePath();
                //System.out.println("Adding " + path);

                if(f.isFile())
                    //not a directory so must be a java file
                    allFiles.add(f);
                else
                    //go into the directory recursively
                    addFiles(f, allFiles);
            }
        }
    }

}

