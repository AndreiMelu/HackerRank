import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SherlockValidString {
    static String isValid(String s) {

        if (s.isEmpty()) return "NO";
        if (s.length() <= 3) return "YES";

        int[] lettersCount = new int[26];
        for (char c : s.toCharArray()) {
            lettersCount[c - 'a']++;
        }
        Arrays.sort(lettersCount);

        int index = 0;
        while (lettersCount[index] == 0) {
            index++;
        }

        int min = lettersCount[index];
        int max = lettersCount[25];
        if (min == max)
            return "YES";
        if (((max - min == 1) && (max > lettersCount[24])) ||
                (min == 1) && (lettersCount[index + 1] == max))
            return "YES";
        else return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();

    }
}
