import java.io.*;
import java.util.stream.IntStream;

class Suit {

    /*
     * Complete the 'getRoundResult' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. CHARACTER winning_suit
     *  2. CHARACTER suit1
     *  3. INTEGER number1
     *  4. CHARACTER suit2
     *  5. INTEGER number2
     */

    public static String getRoundResult(char winning_suit, char suit1, int number1, char suit2, int number2) {
        // Write your code here
        if (suit1 == winning_suit) {
            if (suit2 != winning_suit) {
                return "Player 1 wins";
            } else {
                if (number1 > number2) {
                    return "Player 1 wins";
                } else if (number1 < number2) {
                    return "Player 2 wins";
                } else {
                    return "Draw";
                }
            }
        }else if (suit2 == winning_suit) {
                return "Player 2 wins";
        } else {
            if (number1 > number2) {
                return "Player 1 wins";
            } else if (number1 < number2) {
                return "Player 2 wins";
            } else {
                return "Draw";
            }
        }
    }
}

public class WinningSuit {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        char winning_suit = bufferedReader.readLine().charAt(0);

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, n).forEach(nItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                char suit1 = firstMultipleInput[0].charAt(0);

                int number1 = Integer.parseInt(firstMultipleInput[1]);

                char suit2 = firstMultipleInput[2].charAt(0);

                int number2 = Integer.parseInt(firstMultipleInput[3]);

                String result = Suit.getRoundResult(winning_suit, suit1, number1, suit2, number2);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}



