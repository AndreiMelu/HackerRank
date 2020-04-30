import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class CompareTriplets {

    static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        List<Integer> score = new ArrayList<>();
        int aScore = 0;
        int bScore = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) < b.get(i))
                bScore++;
            if (a.get(i) > b.get(i))
                aScore++;
        }
        score.add(aScore);
        score.add(bScore);
        return score;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> b = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = compareTriplets(a, b);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
