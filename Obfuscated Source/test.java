package obfuscator;

/**
 * Created by alex on 4/1/2017.
 */
public class test {

    public void TESTMETHOD(int value) {
        switch(goTo) {
            case 0:
                System.out.println("case1");
                goTo = 1;
            case 1:
                System.out.println("case2");
                goTo = 2;
            case 2:
                System.out.println("case3");
                goTo = 3;
            case 3:
                this.processData();
            default:
                break;
        }
    }

    public void ANOTHERTESTMETHOD(int value) {
        switch(goTo) {
            case 0:
                int a = 10;
                goTo = 1;
            case 1:
                int b = 5;
                goTo = 2;
            case 2:
                System.out.println(a + b);
                goTo = 3;
            case 3:
                this.processData();
            default:
                break;
        }
    }

    public void THIRDMETHOD(int value) {
        switch(goTo) {
            case 0:
                for (int i = 0; i < 100; i++) {
                    System.out.println("case1");
                    System.out.println("case2");
                    System.out.println("case3");
                }
                goTo = 1;
            case 1:
                this.processData();
            default:
                break;
        }
    }
}
