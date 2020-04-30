import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Rez {

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int diagonalDifference(List<List<Integer>> arr) {
        int principalDiagonal = 0;
        int secondaryDiagonal = 0;
        for (int i = 0; i < arr.size(); i++) {
            principalDiagonal += arr.get(i).get(i);
        }
        for (int i = 0; i < arr.size(); i++) {
            secondaryDiagonal += arr.get(i).get(arr.size() - 1 - i);
        }

        return Math.negateExact(Math.max(principalDiagonal, secondaryDiagonal) - Math.min(principalDiagonal, secondaryDiagonal));
    }

    public static void plusMinus(int[] arr) {
        double positive;
        double negative;
        double totalPositive = 0;
        double totalNegative = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0)
                totalPositive++;
            else if (arr[i] < 0)
                totalNegative++;
        }
        positive = totalPositive / arr.length;
        negative = totalNegative / arr.length;

        System.out.println(positive);
        System.out.println(negative);
        System.out.println(1 - (positive + negative));
    }

}

public class DiagonalDifference {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Rez.diagonalDifference(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

