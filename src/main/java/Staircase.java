import java.util.Scanner;

public class Staircase {
    // Complete the staircase function below.
    static void staircase(int n) {
        String space = " ";
        String symbol = "#";
        for (int i = 0; i < n; i++) {
            String rez="";
            for (int j = 0; j < n - i - 1; j++) {
                rez += space;
            }
            for (int j = 0; j < i + 1; j++) {
                rez+=symbol;
            }
            System.out.println(rez);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        staircase(n);

        scanner.close();
    }
}
