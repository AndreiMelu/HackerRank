import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * A classroom has several students, half of whom are boys and half of whom are girls.
 * You need to arrange all of them in a line for the morning assembly such that the following conditions are satisfied:
 * The students must be in order of non-decreasing height.
 * Two boys or two girls must not be adjacent to each other.
 * <p>
 * You have been given the heights of the boys in the array b and the heights of the girls in the array g.
 * Find out whether you can arrange them in an order which satisfies the given conditions.
 * Print "YES" if it is possible, or "NO" if it is not.
 * <p>
 * For example, let's say there are n=3 boys and n=3 girls, where the boys' heights are b=[5,3,8] and the girls' heights are g=[2,4,6].
 * These students can be arranged in the order [g0,b1,g1,b0,g2,b2], which is [2, 3, 4, 5, 6, 8]. Because this is in order of non-decreasing
 * height, and no two boys or two girls are adjacent to each other, this satisfies the conditions. Therefore, the answer is "YES".
 **/


public class ArrangeStudents {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                List<Integer> b = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                String result = ArrangeStudentsUtil.arrangeStudents(a, b);

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

class ArrangeStudentsUtil {

    /*
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     */

    public static String arrangeStudents(List<Integer> a, List<Integer> b) {
        Collections.sort(a);
        Collections.sort(b);
        List ab = new ArrayList<>();
        if (startWith(a, b).equals("a")) {
            for (int i = 0; i < a.size(); i++) {
                ab.add(a.get(i));
                ab.add(b.get(i));
            }
        } else {
            for (int i = 0; i < a.size(); i++) {
                ab.add(b.get(i));
                ab.add(a.get(i));
            }
        }
        List copy = new ArrayList(ab);
        Collections.sort(copy);
        return ab.equals(copy) ? "YES" : "NO";
    }

    public static String startWith(List<Integer> a, List<Integer> b) {
        if (a.get(0) < b.get(0))
            return "a";
        if (a.get(0) > b.get(0))
            return "b";
        else {
            return startWith(a.subList(1, a.size() - 1), b.subList(1, b.size() - 1));
        }
    }
}
