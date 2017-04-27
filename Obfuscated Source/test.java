package obfuscator;

/**
 * Created by alex on 4/1/2017.
 */
public class test {

    public void TestMethod() {
        int goTo = 0;
        goTo = 0;
        while (goTo != -1) switch(goTo) {
            case 0:
                System.out.println("case1");
                goTo = 1;
                break;
            case 1:
                System.out.println("case2");
                goTo = 2;
                break;
            case 2:
                System.out.println("case3");
                goTo = 3;
                break;
            case 3:
                this.processData();
                goTo = -1;
                break;
            default:
                break;
        }
        this.processData();
        this.processData();
    }

    public void AnotherTestMethod() {
        int goTo = 0;
        int a = 10;
        int b = 5;
        goTo = 2;
        while (goTo != -1) switch(goTo) {
            case 2:
                System.out.println(a + b);
                goTo = 3;
                break;
            case 3:
                System.out.println(b + b);
                goTo = 4;
                break;
            case 4:
                System.out.println(a + a);
                goTo = -1;
                break;
            default:
                break;
        }
        this.processData();
        int c = 25;
        goTo = 6;
        while (goTo != -1) switch(goTo) {
            case 6:
                System.out.println(c + b);
                goTo = 7;
                break;
            case 7:
                System.out.println(c + a);
                goTo = 8;
                break;
            case 8:
                this.processData();
                goTo = -1;
                break;
            default:
                break;
        }
        this.processData();
    }

    public void ThirdMethod() {
        int goTo = 0;
        for (int i = 0; i < 100; i++) {
            System.out.println("case1");
            System.out.println("case2");
            System.out.println("case3");
        }
        goTo = 1;
        while (goTo != -1) switch(goTo) {
            case 1:
                System.out.println("some stuff");
                goTo = 2;
                break;
            case 2:
                this.processData();
                goTo = -1;
                break;
            default:
                break;
        }
    }

    public int processData() {
        return 5 * 5;
    }
}
