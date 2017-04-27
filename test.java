package obfuscator;

/**
 * Created by alex on 4/1/2017.
 */
public class test {
    public void TestMethod(){
        System.out.println("case1");
        System.out.println("case2");
        System.out.println("case3");
    }

    public void AnotherTestMethod(){
        int a = 10;
        int b = 5;
        System.out.println(a+b);

    }

    public void ThirdMethod () {
        for (int i  = 0; i < 100 ; i++){
            System.out.println("case1");
            System.out.println("case2");
            System.out.println("case3");
        }
    }
}
