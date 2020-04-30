import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArrayManipulation {

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        long arr[] = new long[n];
        long rez = 0;
        for (int row = 0; row < queries.length; row++) {
            for (int i = queries[row][0]; i <= queries[row][1]; i++) {
                arr[i-1] += queries[row][2];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (rez < arr[i]) {
                rez = arr[i];
            }
        }
        return rez;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
