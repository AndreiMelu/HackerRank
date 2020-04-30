import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MaximumSteakResult {

    /*
     * Complete the 'getMaxStreaks' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY toss as parameter.
     */

    public static List<Integer> getMaxStreaks(List<String> toss) {
        // Return an array of two integers containing the maximum streak of heads and tails respectively
        int headCount = 0;
        int tailCount = 0;
        int maxHeadCount = 0;
        int maxTailCount = 0;

        if (toss.get(0).equals("Heads")) {
            maxHeadCount = 1;
            headCount = 1;
        } else {
            maxTailCount = 1;
            tailCount = 1;
        }
        for (int i = 1; i < toss.size(); i++) {
            if (toss.get(i).equals(toss.get(i - 1))) {
                if (toss.get(i).equals("Heads")) {
                    if (maxHeadCount <= headCount) {
                        maxHeadCount = headCount + 1;
                    }
                    headCount++;
                } else {
                    if (maxTailCount <= tailCount) {
                        maxTailCount = tailCount + 1;
                    }
                    tailCount++;
                }
            } else {
                if (toss.get(i).equals("Heads")) {
                    headCount = 1;
                    if (maxHeadCount==0)
                        maxHeadCount=1;
                } else {
                    tailCount = 1;
                    if (maxTailCount==0)
                        maxTailCount=1;
                }
            }
        }
        return new ArrayList<>(Arrays.asList(maxHeadCount, maxTailCount));
    }

}

public class MaximumSteak {
    public static void main(String[] args) throws IOException {

        List<String> toss1 = new ArrayList<String>(Arrays.asList("Heads", "Tails", "Tails", "Tails", "Heads", "Heads", "Tails"));
        List<String> toss = new ArrayList<String>(Arrays.asList("Heads", "Tails"));
        System.out.println(MaximumSteakResult.getMaxStreaks(toss));
    }
}
