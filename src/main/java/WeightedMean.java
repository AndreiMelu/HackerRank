import java.util.Scanner;

public class WeightedMean {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n * 2];
        double mean = 0.0;
        int amm = 0;

        for (int i = 0; i < n * 2; i++) {
            arr[i] = sc.nextInt();
            sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        }
        for (int i = 0; i < arr.length / 2; i++) {
            mean += arr[i] * arr[(arr.length / 2) + i];
            amm += arr[(arr.length / 2) + i];
        }
        System.out.printf("%.1f", mean / amm);
    }
}
