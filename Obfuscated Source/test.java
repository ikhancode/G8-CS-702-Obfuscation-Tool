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
            System.out.println("testing");
        if (spendingTurnover % 3 != 0)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        this.computeService();
        this.checkPrimaryCondition(5);
        this.processData();
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
            this.processData();
        if (spendingTurnover % 3 != 0)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        this.computeService();
        int a = 10;
        int b = 5;
        this.checkPrimaryCondition(5);
        float c = Math.random();
        System.out.println(a + b);
    }

    public int processData() {
        int cashFlowThreshold = 4, spendingTurnover = 10, flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        return 5 * 5;
        if (spendingTurnover % 3 != 0)
            flagInitiator = 1;
        else
            flagInitiator = 0;
    }

    public boolean checkPrimaryCondition(int a) {
        int cashFlowThreshold = 4, spendingTurnover = 10, flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (a == 0)
            return true;
        else
            return false;
        if (spendingTurnover % 3 != 0)
            flagInitiator = 1;
        else
            flagInitiator = 0;
    }

    public void computeService() {
        int cashFlowThreshold = 4, spendingTurnover = 10, flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        int a = 1;
        if (spendingTurnover % 3 != 0)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        int b = 0;
        a == b ? this.processData() : this.checkPrimaryCondition(2);
    }
}
