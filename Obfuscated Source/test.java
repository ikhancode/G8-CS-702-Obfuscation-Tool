package obfuscator;

/**
 * Created by alex on 4/1/2017.
 */
public class test {

    public void TestMethod() {
        this.computeService();
        this.processData();
        this.checkPrimaryCondition(5);
        System.out.println("testing");
        System.out.println("testing");
        System.out.println("testing");
    }

    public void AnotherTestMethod() {
        this.checkPrimaryCondition(5);
        this.processData();
        this.computeService();
        int a = 10;
        int b = 5;
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
