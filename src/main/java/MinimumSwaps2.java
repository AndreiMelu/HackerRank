import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MinimumSwaps2 {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {

//        int[] sortedArr = Arrays.stream(arr)
//                .boxed()
//                .sorted()
//                .collect(Collectors.toList())
//                .stream().mapToInt(i -> (int) i).toArray();
        int swap = 0;
        int min;
        for (int i = 0; i < arr.length; i++) {
            int index = 0;
            min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }
            if (min < arr[i]) {
                arr[index] = arr[i];
                arr[i] = min;
                swap++;
            }
        }
        return swap;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
