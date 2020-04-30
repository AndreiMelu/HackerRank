import java.util.Scanner;

public class StdinAndStdout {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        String s = scan.next();
        double d = scan.nextDouble();
        scan.close();

        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);
    }
}
