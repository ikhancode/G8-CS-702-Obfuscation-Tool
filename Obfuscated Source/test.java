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
        int goTo = 0;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        goTo = 0;
        while (goTo != -1) switch(goTo) {
            case 0:
                this.processData();
                goTo = 1;
                break;
            case 1:
                this.computeService();
                goTo = 2;
                break;
            case 2:
                this.checkPrimaryCondition(5);
                goTo = 3;
                break;
            case 3:
                System.out.println("case1");
                goTo = 4;
                break;
            case 4:
                System.out.println("case2");
                goTo = 5;
                break;
            case 5:
                System.out.println("case3");
                goTo = -1;
                break;
            default:
                break;
        }
        if (spendingTurnover > Math.pow(cashFlowThreshold, 5))
            flagInitiator = 1;
        else
            this.processData();
        if (spendingTurnover > Math.pow(cashFlowThreshold, 5))
            flagInitiator = 1;
        else
            this.processData();
        if (spendingTurnover > Math.pow(cashFlowThreshold, 5))
            flagInitiator = 1;
        else
            this.processData();
        if (spendingTurnover % 3 != 0)
            this.processData();
        else
            flagInitiator = 0;
    }

    public void AnotherTestMethod() {
        double cashFlowThreshold = 4;
        double spendingTurnover = 10 ;
        int flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        int goTo = 0;
        int a = 10;
        int b = 5;
        goTo = 2;
        while (goTo != -1) switch(goTo) {
            case 2:
                this.checkPrimaryCondition(5);
                goTo = 3;
                break;
            case 3:
                this.computeService();
                goTo = -1;
                break;
            default:
                break;
        }
        float c = Math.random();
        goTo = 5;
        while (goTo != -1) switch(goTo) {
            case 5:
                this.processData();
                goTo = 6;
                break;
            case 6:
                System.out.println(a + b);
                goTo = 7;
                break;
            case 7:
                System.out.println(b + b);
                goTo = 8;
                break;
            case 8:
                System.out.println(a + a);
                goTo = -1;
                break;
            default:
                break;
        }
        if (spendingTurnover % 3 != 0)
            this.processData();
        else
            flagInitiator = 0;
        if (spendingTurnover % 3 != 0)
            this.processData();
        else
            flagInitiator = 0;
        int c = 25;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        goTo = 10;
        while (goTo != -1) switch(goTo) {
            case 10:
                System.out.println(c + b);
                goTo = 11;
                break;
            case 11:
                System.out.println(c + a);
                goTo = -1;
                break;
            default:
                break;
        }
    }

    public void ThirdMethod() {
        double cashFlowThreshold = 4;
        double spendingTurnover = 10 ;
        int flagInitiator = 0;
        double avgTurnoverRatio = Math.random()*20;
        if (avgTurnoverRatio > spendingTurnover)
            flagInitiator = 1;
        else
            flagInitiator = 0;
        int goTo = 0;
        goTo = 0;
        while (goTo != -1) switch(goTo) {
            case 0:
                this.computeService();
                goTo = 1;
                break;
            case 1:
                this.processData();
                goTo = 2;
                break;
            case 2:
                this.checkPrimaryCondition(5);
                goTo = -1;
                break;
            default:
                break;
        }
        if (spendingTurnover > Math.pow(cashFlowThreshold, 5))
            flagInitiator = 1;
        else
            this.processData();
        for (int i = 0; i < 100; i++) {
            System.out.println("case1");
            System.out.println("case2");
            System.out.println("case3");
        }
        if (spendingTurnover > Math.pow(cashFlowThreshold, 5))
            flagInitiator = 1;
        else
            System.out.println("some stuff");
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
