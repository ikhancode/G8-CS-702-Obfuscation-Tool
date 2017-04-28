package obfuscator;

import java.util.ArrayList;

/**
 * Created by alex on 4/27/2017.
 * This is a simple data class representing indexes of consecutive statements in a method
 */
public class ConsecutiveBlockIndexes {

    public ArrayList<Integer> indexes;

    public ConsecutiveBlockIndexes(){
        indexes = new ArrayList<>();
    }

    //adds an index
    public void add(Integer integer){
        this.indexes.add(integer);
    }

    public int first(){
        return this.indexes.get(0);
    }

    public int last(){
        return this.indexes.get(this.indexes.size() - 1);
    }

    //gets an index
    public int get(int i){
        return this.indexes.get(i);
    }
}
