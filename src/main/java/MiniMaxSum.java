import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MiniMaxSum {
    // Complete the miniMaxSum function below.
    static void miniMaxSum(int[] arr) {
        Arrays.sort(arr);
        long max = 0;
        long min = 0;
        for (int i = 1; i < arr.length; i++) {
            max += arr[i];
        }
        for (int i = 0; i < arr.length - 1; i++) {
            min += arr[i];
        }
        System.out.println(min + " " + max);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        int arr[] = {256741038, 623958417, 714532089, 467905213, 938071625};
        int[] arr = new int[5];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < 5; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        miniMaxSum(arr);

        scanner.close();
    }
}
