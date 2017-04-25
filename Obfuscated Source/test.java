package obfuscator;

/**
 * Created by alex on 4/1/2017.
 */
public class test {

    public void TestMethod() {
        int cashFlowThreshold = 4, spendingTurnover = 10;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            return true;
        else
            return false;
        if (spendingTurnover > (Math.pow(cashFlowThreshold, 3) * avgTurnoverRatio))
            return true;
        else
            return false;
        if (spendingTurnover % 3 != 0)
            return true;
        else
            return false;
        this.checkPrimaryCondition(5);
        this.computeService();
        this.processData();
        System.out.println("testing");
        System.out.println("testing");
        System.out.println("testing");
    }

    public void AnotherTestMethod() {
        int cashFlowThreshold = 4, spendingTurnover = 10;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            return true;
        else
            return false;
        if (spendingTurnover > (Math.pow(cashFlowThreshold, 3) * avgTurnoverRatio))
            return true;
        else
            return false;
        if (spendingTurnover % 3 != 0)
            return true;
        else
            return false;
        this.computeService();
        int a = 10;
        this.checkPrimaryCondition(5);
        int b = 5;
        this.processData();
        System.out.println(a + b);
    }

    public int processData() {
        int cashFlowThreshold = 4, spendingTurnover = 10;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            return true;
        else
            return false;
        if (spendingTurnover > (Math.pow(cashFlowThreshold, 3) * avgTurnoverRatio))
            return true;
        else
            return false;
        if (spendingTurnover % 3 != 0)
            return true;
        else
            return false;
        return 5 * 5;
    }

    public boolean checkPrimaryCondition(int a) {
        int cashFlowThreshold = 4, spendingTurnover = 10;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            return true;
        else
            return false;
        if (spendingTurnover > (Math.pow(cashFlowThreshold, 3) * avgTurnoverRatio))
            return true;
        else
            return false;
        if (spendingTurnover % 3 != 0)
            return true;
        else
            return false;
        if (a == 0)
            return true;
        else
            return false;
    }

    public void computeService() {
        int cashFlowThreshold = 4, spendingTurnover = 10;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            return true;
        else
            return false;
        if (spendingTurnover > (Math.pow(cashFlowThreshold, 3) * avgTurnoverRatio))
            return true;
        else
            return false;
        if (spendingTurnover % 3 != 0)
            return true;
        else
            return false;
        int a = 1;
        int b = 0;
        a == b ? this.processData() : this.checkPrimaryCondition(2);
    }
}
