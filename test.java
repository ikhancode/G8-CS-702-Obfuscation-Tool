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
        System.out.println(b+b);
        System.out.println(a+a);
        int c = 25;
        System.out.println(c+b);
        System.out.println(c+a);

    }

    public void ThirdMethod () {
        for (int i  = 0; i < 100 ; i++){
            System.out.println("case1");
            System.out.println("case2");
            System.out.println("case3");
        }
        System.out.println("some stuff");
    }
}
