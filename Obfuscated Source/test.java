package obfuscator;

/**
 * Created by alex on 4/1/2017.
 */
public class test {

    public void TESTMETHOD(int value) {
        System.out.println("testing");
        System.out.println("testing");
        System.out.println("testing");
        this.processData();
    }

    public void ANOTHERTESTMETHOD(int value) {
        int a = 10;
        int b = 5;
        System.out.println(a + b);
        this.processData();
    }

    public int processData() {
        return 5 * 5;
    }
}
