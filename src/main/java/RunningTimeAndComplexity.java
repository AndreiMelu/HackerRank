import java.util.Scanner;

/**
 * Task
 * A prime is a natural number greater than 1 that has no positive divisors other than 1 and itself.
 * Given a number, n, determine and print whether it's "Prime" or "Not prime".
 * <p>
 * Note: If possible, try to come up with a O(√n) primarity algorithm, or see what sort of optimizations you come up with for an O(n) algorithm. Be sure to check out the Editorial after submitting your code!
 * <p>
 * Input Format
 * <p>
 * The first line contains an integer, T, the number of test cases.
 * Each of the T subsequent lines contains an integer, n, to be tested for primarity.
 */


public class RunningTimeAndComplexity {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int prime = scanner.nextInt();
            System.out.println(checkPrime(prime));
        }
    }

    static String checkPrime(int n) {
        if (n<=1)
            return "Not Prime";
        if (n == 2)
            return "Prime";
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return "Not prime";
        }
        return "Prime";
    }
}