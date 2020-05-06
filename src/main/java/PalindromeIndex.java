
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class PalindromeIndex {

    // Complete the palindromeIndex function below.
//    Optimized
//    static int palindromeIndex(String s) {
//        for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
//            if(s.charAt(i) != s.charAt(j)) {
//                if(s.charAt(i+1) != s.charAt(j))
//                    return j;
//                else if(s.charAt(i) != s.charAt(j-1))
//                    return i;
//                else {
//                    if(s.charAt(i+2) != s.charAt(j-1))
//                        return j;
//                    if(s.charAt(i+1) != s.charAt(j-2))
//                        return i;
//                }
//            }
//        }
//        return -1;
//    }

    static int palindromeIndex(String s) {
        if (isPalindrome(s))
            return -1;
        for (int i = 0; i < s.length(); i++) {
            String s1 = s.substring(0, i) + s.substring(i + 1);
            if (isPalindrome(s1))
                return i;
        }
        return -1;
    }

    static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = palindromeIndex(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
