package obfuscator;

/**
 * Created by alex on 4/1/2017.
 */
public class test {

    public void TestMethod() {
        this.processData();
        this.computeService();
        this.checkPrimaryCondition(5);
        System.out.println("testing");
        System.out.println("testing");
        System.out.println("testing");
    }

    public void AnotherTestMethod() {
        int a = 10;
        this.computeService();
        int b = 5;
        this.checkPrimaryCondition(5);
        this.processData();
        System.out.println(a + b);
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

    public void computeService() {
        int a = 1;
        int b = 0;
        a == b ? this.processData() : this.checkPrimaryCondition(2);
    }
}
