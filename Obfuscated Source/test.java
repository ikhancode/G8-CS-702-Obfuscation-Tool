package obfuscator;

/**
 * Created by alex on 4/1/2017.
 */
public class test {

    public void TestMethod() {
        int cashFlowThreshold = 4, spendingTurnover = 10, flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (spendingTurnover > (Math.pow(cashFlowThreshold, 3) * avgTurnoverRatio))
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (spendingTurnover % 3 != 0)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        this.processData();
        this.computeService();
        this.checkPrimaryCondition(5);
        System.out.println("testing");
        System.out.println("testing");
        System.out.println("testing");
    }

    public void AnotherTestMethod() {
        int cashFlowThreshold = 4, spendingTurnover = 10, flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (spendingTurnover > (Math.pow(cashFlowThreshold, 3) * avgTurnoverRatio))
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (spendingTurnover % 3 != 0)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        this.computeService();
        int a = 10;
        this.processData();
        this.checkPrimaryCondition(5);
        int b = 5;
        System.out.println(a + b);
    }

    public int processData() {
        int cashFlowThreshold = 4, spendingTurnover = 10, flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (spendingTurnover > (Math.pow(cashFlowThreshold, 3) * avgTurnoverRatio))
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (spendingTurnover % 3 != 0)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        return 5 * 5;
    }

    public boolean checkPrimaryCondition(int a) {
        int cashFlowThreshold = 4, spendingTurnover = 10, flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (spendingTurnover > (Math.pow(cashFlowThreshold, 3) * avgTurnoverRatio))
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (spendingTurnover % 3 != 0)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (a == 0)
            return true;
        else
            return false;
    }

    public void computeService() {
        int cashFlowThreshold = 4, spendingTurnover = 10, flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (spendingTurnover > (Math.pow(cashFlowThreshold, 3) * avgTurnoverRatio))
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (spendingTurnover % 3 != 0)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        int a = 1;
        int b = 0;
        a == b ? this.processData() : this.checkPrimaryCondition(2);
    }
}
