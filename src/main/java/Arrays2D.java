import java.util.Arrays;
import java.util.Scanner;

public class Arrays2D {
//    https://www.hackerrank.com/challenges/30-2d-arrays/problem

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }
        scanner.close();

        int a = 0;
        int[] sum = new int[16];
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 4; j++) {
                sum[i] = arr[j][a] + arr[j][a + 1] + arr[j][a + 2] +
                        arr[j + 1][a + 1] +
                        arr[j + 2][a] + arr[j + 2][a + 1] + arr[j + 2][a + 2];
                i++;
            }
            i--; a++;
        }
        Arrays.sort(sum);
        System.out.println("\n" + sum[15]);
    }
}

