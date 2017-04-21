package obfuscator;

/**
 * Created by alex on 4/1/2017.
 */
public class test {

    public void TESTMETHOD(int value) {
        this.processData();
        System.out.println("testing");
        System.out.println("testing");
        System.out.println("testing");
        this.checkPrimaryCondition(5);
    }

    public void ANOTHERTESTMETHOD(int value) {
        this.processData();
        int a = 10;
        int b = 5;
        System.out.println(a + b);
        this.checkPrimaryCondition(5);
    }

    public int processData() {
        return 5 * 5;
    }

    public boolean checkPrimaryCondition(int a) {
        if (a == 0)
            return true;
        else
            return false;
    }
}
