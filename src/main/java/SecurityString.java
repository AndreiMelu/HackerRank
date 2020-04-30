import java.io.*;

class Rezz{

    /*
     * Complete the 'getStrength' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING password
     *  2. INTEGER weight_a
     */

    public static int getStrength(String password, int weight_a) {
        int rez = 0;
        char[] asChar = password.toCharArray();
        for (char c : asChar) {
            if (c - 97 + weight_a > 25) {
                rez += c - 97 + weight_a - 26;
            } else {
                rez += c - 97 + weight_a;
            }
        }
        return rez;
    }

}

public class SecurityString {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String password = bufferedReader.readLine();

        int weight_a = Integer.parseInt(bufferedReader.readLine().trim());

        int strength = Rezz.getStrength(password, weight_a);

        bufferedWriter.write(String.valueOf(strength));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
