import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MigratoryBirds {

    // Complete the migratoryBirds function below.
    static int migratoryBirds(List<Integer> arr) {
        Collections.sort(arr);
        return (int) sortByValue(getInitialMap(arr)).keySet().toArray()[0];
    }

    private static Map<Integer, Integer> getInitialMap(List<Integer> arr) {
        Map<Integer, Integer> birdMap = new TreeMap<>();
        for (int i = 1; i <= arr.get(arr.size() - 1); i++) {
            birdMap.put(i, 0);
        }
        for (Integer integer : arr) {
            birdMap.put(integer, birdMap.get(integer) + 1);
        }
        return birdMap;
    }

    public static Map<Integer, Integer> sortByValue(final Map<Integer, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted((Map.Entry.<Integer, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = migratoryBirds(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
