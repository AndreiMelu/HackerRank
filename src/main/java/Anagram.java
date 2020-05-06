import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Anagram {
    // Complete the anagram function below.
    static int anagram(String s) {

        int[] count = new int[26];
        if (s.length() % 2 == 0) {
            for (int i = 0; i < s.length() / 2; i++) {
                count[s.charAt(i) - 'a']++;
            }
            for (int i = s.length() / 2; i < s.length(); i++) {
                count[s.charAt(i) - 'a']--;
            }
            int sum = 0;
            for (int i = 0; i < 26; i++) {
                sum += Math.abs(count[i]);
            }
            return sum / 2;
        }
        else return -1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = anagram(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
