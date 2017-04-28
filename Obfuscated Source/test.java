package obfuscator;

/**
 * Created by alex on 4/1/2017.
 */
public class test {

    public void TestMethod() {
        double cashFlowThreshold = 4;
        double spendingTurnover = 10 ;
        int flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        if (spendingTurnover % 3 != 0)
            this.checkPrimaryCondition(5);
        else
            flagInitiator = 0;
        if (spendingTurnover % 3 != 0)
            this.checkPrimaryCondition(5);
        else
            flagInitiator = 0;
        if (spendingTurnover > Math.pow(cashFlowThreshold, 5))
            flagInitiator = 1;
        else
            this.checkPrimaryCondition(5);
        if (spendingTurnover % 3 != 0)
            this.processData();
        else
            flagInitiator = 0;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (spendingTurnover % 3 != 0)
            this.processData();
        else
            flagInitiator = 0;
        if (spendingTurnover % 3 != 0)
            this.processData();
        else
            flagInitiator = 0;
        this.processData();
        this.computeService();
        System.out.println("testing");
        System.out.println("testing");
        System.out.println("testing");
    }

    public void AnotherTestMethod() {
        double cashFlowThreshold = 4;
        double spendingTurnover = 10 ;
        int flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        if (spendingTurnover > Math.pow(cashFlowThreshold, 5))
            flagInitiator = 1;
        else
            this.checkPrimaryCondition(5);
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (spendingTurnover > Math.pow(cashFlowThreshold, 5))
            flagInitiator = 1;
        else
            this.computeService();
        int a = 10;
        if (spendingTurnover > Math.pow(cashFlowThreshold, 5))
            flagInitiator = 1;
        else
            this.processData();
        int b = 5;
        float c = Math.random();
        if (spendingTurnover > Math.pow(cashFlowThreshold, 5))
            flagInitiator = 1;
        else
            System.out.println(a + b);
    }

    public int processData() {
        double cashFlowThreshold = 4;
        double spendingTurnover = 10 ;
        int flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        return 5 * 5;
    }

    public boolean checkPrimaryCondition(int a) {
        double cashFlowThreshold = 4;
        double spendingTurnover = 10 ;
        int flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        if (a == 0)
            return true;
        else
            return false;
    }

    public void computeService() {
        double cashFlowThreshold = 4;
        double spendingTurnover = 10 ;
        int flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        int a = 1;
        int b = 0;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        a == b ? this.processData() : this.checkPrimaryCondition(2);
    }
}
