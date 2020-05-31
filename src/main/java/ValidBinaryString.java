import java.io.*;

/**
 * You have been given a binary string containing only the characters "0" and "1".
 * A binary string is considered valid if each of its substrings of at
 * least a certain length contains at least one "1" character.
 * Given the binary string, and the minimum length of substring, determine how many
 * characters of the string need to be changed in order to make the binary string valid.
 * <p>
 * Note that string w is a substring of string  if it has a non-zero length and can be read starting from some position in string w.
 * For example, string "010" has six substrings: "0", "1", "0", "01", "01", "010".
 * <p>
 * For example, let's say the binary string  = "0001" of length n = 4, and the minimum substring length is d = 2.
 * This is currently not valid because one of its substrings, "000", of length 3 has no "1"s in it.
 * By changing the second character to a "1", the string becomes "0101".
 * In this case, all of its substrings of length 2 or more has at least one "1" character: "01", "10", and "01".
 * Because this required 1 change, the answer is 1.
 **/

public class ValidBinaryString {
    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int d = Integer.parseInt(bufferedReader.readLine().trim());

        int result = MinimumMoves.minimumMoves(s, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

class MinimumMoves {
    static int result = 0;

    public static int minimumMoves(String s, int d) {
        if (d == 1) {
            int count = 0;
            for (char c : s.toCharArray()) {
                if (c == '0')
                    count++;
            }
            return count;
        }
        if (d == s.length()) {
            return s.indexOf('1') == -1 ? 1 : 0;
        }
        for (int index = 0; index + d <= s.length(); index++) {
            if (s.substring(index, index + d).indexOf('1') == -1) {
                result++;
                addChar(s, index + d - 1, d);
                break;
            }
        }
        return result;
    }

    public static void addChar(String s, int index, int d) {
        s = s.substring(0, index) + '1' + s.substring(index + 1);
        minimumMoves(s.substring(index), d);
    }
}
