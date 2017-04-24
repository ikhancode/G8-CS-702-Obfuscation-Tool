package obfuscator;

/**
 * Created by alex on 4/1/2017.
 */
public class test {

    public void TESTMETHOD(int value) {
        System.out.println("case1");
        System.out.println("case2");
        System.out.println("case3");
        this.processData();
    }

    public void ANOTHERTESTMETHOD(int value) {
        int a = 10;
        int b = 5;
        System.out.println(a + b);
        this.processData();
    }
}
