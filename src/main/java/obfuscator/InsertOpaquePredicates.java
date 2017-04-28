package obfuscator;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by alex on 4/27/2017.
 */
public class InsertOpaquePredicates  {

    public void insertPredicates(MethodDeclaration n) {

        //Call the block of code.
        BlockStmt block =  n.getBody().get();

        //Set some fake variables which will be used by the predicates
        block.addStatement(0, new NameExpr("double cashFlowThreshold = 4"));
        block.addStatement(1, new NameExpr("double spendingTurnover = 10 "));
        block.addStatement(2, new NameExpr("int flagInitiator = 0"));
        block.addStatement(3, new NameExpr("double avgTurnoverRatio = Math.random()*20"));

        int methodLength = block.getStatements().size();
        ArrayList<Integer> methodCallIndexes = new ArrayList<>();

        //This for loop works out all of the method calls in the block and records the indexes
        for (int j = 0; j < methodLength; j++){
            Statement stm = block.getStatement(j);
            boolean hasVarDec = false;
            for (int i = 0; i < stm.getChildNodes().size(); i++) {
                //System.out.println("bbbb bbbb " + stm.getChildNodes().get(i).getClass().toString());
                if ( stm.getChildNodes().get(i).getClass() != MethodCallExpr.class){
                    hasVarDec = true;
                }
            }
            if(!hasVarDec && stm.getClass() != ReturnStmt.class){
                methodCallIndexes.add(j);
            }
        }

        //Wrap the applicable method calls in opaque predicates
        for(int i : methodCallIndexes){
            boolean isTruePredicate = new Random().nextBoolean();
            if(isTruePredicate){
                //This predicate always evaluates to True
                IfStmt pTrue = new IfStmt();
                pTrue.setCondition(new NameExpr("spendingTurnover % 3 != 0"));
                pTrue.setThenStmt(block.getStatement(i));
                pTrue.setElseStmt(new ExpressionStmt(new NameExpr("flagInitiator = 0")));
                block.setStatement(i, pTrue);
            }else{
                //This predicate always evaluates to False
                IfStmt pFalse = new IfStmt();
                pFalse.setCondition(new NameExpr("spendingTurnover > Math.pow(cashFlowThreshold, 5)"));
                pFalse.setThenStmt(new ExpressionStmt(new NameExpr("flagInitiator = 1")));
                pFalse.setElseStmt(block.getStatement(i));
                block.setStatement(i, pFalse );
            }
        }

        //Insert a predicate at a random point of the method. This predicate evaluates to either True or False
        IfStmt pEither = new IfStmt();
        pEither.setCondition(new NameExpr("avgTurnoverRatio > spendingTurnover"));
        pEither.setThenStmt(new ExpressionStmt(new NameExpr("flagInitiator = 1")));
        pEither.setElseStmt(new ExpressionStmt(new NameExpr("flagInitiator = 0")));
        block.addStatement(ThreadLocalRandom.current().nextInt(4, methodLength), pEither);

    }
}
