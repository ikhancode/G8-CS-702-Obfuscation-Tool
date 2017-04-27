package obfuscator;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.stmt.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 4/27/2017.
 */
public class ControlFlowFlattener {
    public void flat(MethodDeclaration n){
        BlockStmt block = n.getBody().get();

        int methodLength = block.getStatements().size();
        List myList = new ArrayList();

        //Check for blocks of code int he method that can be flattened. (some code can't)
        ArrayList<Integer> methodCallIndexes = new ArrayList<>();
        for (int j = 0; j < methodLength; j++){
            Statement stm = block.getStatement(j);
            boolean canBeFlattened = true;
            for (int i = 0; i < stm.getChildNodes().size(); i++) {
                //System.out.println("bbbb bbbb " + stm.getChildNodes().get(i).getClass().toString());
                Class statementClass = stm.getChildNodes().get(i).getClass();
                if ( statementClass != MethodCallExpr.class ){
                    canBeFlattened = false;
                }
            }
            if(canBeFlattened){
                //System.out.println("Index added: " + j);
                methodCallIndexes.add(j);
            }
        }

        /*
        methodCallIndexes should now contain indexes of all method calls.
        Extract each consecutive sub block of calls to flatten
        */
        ConsecutiveBlockIndexes subBlock = new ConsecutiveBlockIndexes();
        subBlock.add(methodCallIndexes.get(0));
        boolean isLastBlock = false;
        for(int i = 1; i < methodCallIndexes.size(); i++){
            int index = methodCallIndexes.get(i);
            isLastBlock = false;
            //System.out.println("Gotten index: " + index);
            //if its consecutive, put it in the same block
            if((index - 1) == methodCallIndexes.get(i - 1)){
                subBlock.add(index);
            }
            //Otherwise start a new block
            else{
                //subBlocks.add(subBlock);
                this.flattenBlock(subBlock, block);
                System.out.println("New block");
                subBlock = new ConsecutiveBlockIndexes();
                subBlock.add(index);
                isLastBlock = true;
            }
        }

        //do the same for the last block
        if(!isLastBlock){
            this.flattenBlock(subBlock, block);
        }


        block.addStatement(0, new NameExpr("int goTo = 0"));
    }

    private void flattenBlock(ConsecutiveBlockIndexes subBlock, BlockStmt block){
        if(subBlock.indexes.size() > 1){
            SwitchStmt switchStatement = new SwitchStmt();
            switchStatement.setSelector(new NameExpr("goTo"));
            NodeList<Statement> statementList = new NodeList<Statement>();

            WhileStmt whileLoop = new WhileStmt();
            whileLoop.setCondition(new NameExpr("goTo != -1"));
            int counter = 1;    //used to keep track of the last case in the switch statement
            for(int j : subBlock.indexes){
                //System.out.println("Block contains statement index: " + subBlock.get(j));
                if (counter++ != subBlock.indexes.size()) {
                    SwitchEntryStmt myCase = new SwitchEntryStmt();
                    myCase.addStatement(block.getStatement(j));
                    myCase.addStatement(new NameExpr("goTo = " + (j+1)));
                    myCase.addStatement(new NameExpr("break"));
                    myCase.setLabel(new NameExpr(Integer.toString(j)));
                    switchStatement.addEntry(myCase);
                } else {
                    //This is the last case, set goTo to -1 to
                    SwitchEntryStmt myCase = new SwitchEntryStmt();
                    myCase.addStatement(block.getStatement(j));
                    myCase.addStatement(new NameExpr("goTo = -1"));
                    myCase.addStatement(new NameExpr("break"));
                    myCase.setLabel(new NameExpr(Integer.toString(j)));
                    switchStatement.addEntry(myCase);
                }
            }
            //Add a default just in case
            SwitchEntryStmt defaultState = new SwitchEntryStmt();
            defaultState.addStatement(new NameExpr("break"));
            switchStatement.addEntry(defaultState);
            whileLoop.setBody(switchStatement);
            statementList.add(whileLoop);


            for(int statementIndex : subBlock.indexes){
                //Replace exisiting code with placeholder code since the existing code is all in the switch statement
                block.setStatement(statementIndex, new ExpressionStmt(new NameExpr("this.processData()")));
            }
            block.setStatement(subBlock.get(0), new ExpressionStmt(new NameExpr("goTo = " + subBlock.get(0))));
            block.setStatement(subBlock.get(1), whileLoop);
        }

    }
}
