package obfuscator;
import com.github.javaparser.ast.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.EnumSet;


/**
 * Created by alex on 4/1/2017.
 */
public class FileParser {

    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        File test = new File("test.java");
        System.out.println(test.getCanonicalPath());
        FileInputStream in = new FileInputStream(test);

        // parse the file
        CompilationUnit cu = JavaParser.parse(in);

        // prints the resulting compilation unit to default system output
        System.out.println(cu.toString());

        //Change all methods using visitor
        new MethodChangerVisitor().visit(cu, null);
        //System.out.println(cu.toString());

        //Insert a method into the class
        /*new ClassChangerVisitor().visit(cu, null);
        System.out.println(cu.toString());
        */

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
            n.setName(n.getNameAsString().toUpperCase());

            // add a new parameter to the method
            n.addParameter("int", "value");
            BlockStmt block = n.getBody().get();

            NameExpr clazz = new NameExpr("this");
            MethodCallExpr call = new MethodCallExpr(clazz,"processData");
            block.addStatement(call);


            this.flat(n);

        }
        public void flat(MethodDeclaration n){
            BlockStmt block = n.getBody().get();
            System.out.println(block);
            int goTo=0;
            boolean myStat=true;
            while(myStat) {
                switch (goTo) {
                    case 0:
                        goTo = 2;
                        System.out.println("Case 0 has been visited");
                        continue;
                    case 1:
                        System.out.println("Case 1 has been visited");
                        myStat = false;
                        break;
                    case 2:
                        goTo = 1;
                        System.out.println("Case 2 has been visited");
                        continue;
                    default:
                        return;
                }
            }

        }
    }
    /*
    private static class ClassChangerVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            MethodDeclaration fakeMethod = n.addMethod("processData",Modifier.PUBLIC);
            fakeMethod.setType("int");
            BlockStmt fakeMethodContent = new BlockStmt().addStatement(new ReturnStmt("5 * 5"));
            fakeMethod.setBody(fakeMethodContent);
        }
    }
    */


}

