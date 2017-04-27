package obfuscator;

import java.util.ArrayList;

/**
 * Created by alex on 4/27/2017.
 */
public class ConsecutiveBlockIndexes {
    public ArrayList<Integer> indexes;

    public ConsecutiveBlockIndexes(){
        indexes = new ArrayList<>();
    }

    public void add(Integer integer){
        this.indexes.add(integer);
    }

    public int first(){
        return this.indexes.get(0);
    }

    public int last(){
        return this.indexes.get(this.indexes.size() - 1);
    }

    public int get(int i){
        return this.indexes.get(i);
    }
}
