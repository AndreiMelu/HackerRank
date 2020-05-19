import java.util.Scanner;

import static java.lang.String.format;

public class BinomialDistribution1 {
    static Scanner scanner = new Scanner(System.in);
    public static final double BR = 1.09;
    public static final double GR = 1;

    static int factorial(int n) {
        if (n == 0)
            return 1;
        return n * factorial(n - 1);
    }

    static double combination(int r, int n) {
        return (factorial(n) / (factorial(r) * factorial(n - r)));
    }

    public static void main(String[] args) {
        double p = (BR) / (BR + GR);
        double q = 1 - p;
        String s = "";
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            double probability = 0.0;
            for (int i = n; i >= 3; i--) {
                probability += combination(i, n) * Math.pow(p, i) * Math.pow(q, n - i);
            }
            s += format("%.3f ", probability);
        }
        s.substring(0, s.length() - 1);
        System.out.println(s);
        scanner.close();
    }

    static class Solution6 {
        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            double ratio = 1.09;

            double p = ratio / (1 + ratio);
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            /* Calculate result */
            double result = 0;
            for (int x = 3; x <= n; x++) {
                result += binomial(n, x, p);
            }
            System.out.format("%.3f", result);
            scanner.close();
        }

        private static Double binomial(int n, int x, double p) {
            if (p < 0 || p > 1 || n < 0 || x < 0 || x > n) {
                return null;
            }
            return combinations(n, x) * Math.pow(p, x) * Math.pow(1 - p, n - x);
        }

        private static Long combinations(int n, int x) {
            if (n < 0 || x < 0 || x > n) {
                return null;
            }
            return factorial(n) / (factorial(x) * factorial(n - x));
        }

        private static Long factorial(int n) {
            if (n < 0) {
                return null;
            }
            long result = 1;
            while (n > 0) {
                result *= n--;
            }
            return result;
        }
    }

    static void evenOddStringIndex() {
        int cases = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            String even = "";
            String odd = "";
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 0) {
                    even += s.charAt(i);
                } else {
                    odd += s.charAt(i);
                }
            }
            System.out.println(even + " " + odd);
        }
    }
}
