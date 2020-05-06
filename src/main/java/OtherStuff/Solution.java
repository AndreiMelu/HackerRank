package OtherStuff;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'fetchItemsToDisplay' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_STRING_ARRAY items
     *  2. INTEGER sortParameter
     *  3. INTEGER sortOrder
     *  4. INTEGER itemsPerPage
     *  5. INTEGER pageNumber
     */

    public static List<String> fetchItemsToDisplay(List<List<String>> items, int sortParameter, int sortOrder, int itemsPerPage, int pageNumber) {
        if (sortParameter==0) {
            items.sort(new nameSorter());
        }
        else if (sortParameter==1){
            items.sort(new relevanceSorter());
        }
        else { items.sort(new priceSorter());
        }
        if (sortOrder==1){
            Collections.reverse(items);
        }

        List<List<List<String>>> parts = chopped(items, itemsPerPage);
        List<String> result = recursiveAdd(parts.get(pageNumber),itemsPerPage);

        System.out.println(result.get(0));

        return result;

    }
    static <T> List<List<T>> chopped(List<T> list, final int L) {
        List<List<T>> parts = new ArrayList<>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<>(
                    list.subList(i, Math.min(N, i + L)))
            );
        }
        return parts;
    }
    static <String> List<String> recursiveAdd (List<List<String>> list, int itemsPerPage){
        List<String>result= new ArrayList();
        for(int i=0; i<itemsPerPage; i++){
            result.add(list.get(i).get(0));
        }
        return result;
    }

}

class nameSorter implements Comparator<List<String>> {
    @Override
    public int compare(List<String> s1, List<String> s2) {
        return s1.get(0).compareTo(s2.get(0));
    }
}

class relevanceSorter implements Comparator<List<String>> {
    @Override
    public int compare(List<String> s1, List<String> s2) {
        return s1.get(1).compareTo(s2.get(1));
    }
}

class priceSorter implements Comparator<List<String>> {
    @Override
    public int compare(List<String> s1, List<String> s2) {
        return s1.get(2).compareTo(s2.get(2));
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int itemsRows = Integer.parseInt(bufferedReader.readLine().trim());
        int itemsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> items = new ArrayList<>();

        IntStream.range(0, itemsRows).forEach(i -> {
            try {
                items.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int sortParameter = Integer.parseInt(bufferedReader.readLine().trim());
        int sortOrder = Integer.parseInt(bufferedReader.readLine().trim());
        int itemsPerPage = Integer.parseInt(bufferedReader.readLine().trim());
        int pageNumber = Integer.parseInt(bufferedReader.readLine().trim());
        List<String> result = Result.fetchItemsToDisplay(items, sortParameter, sortOrder, itemsPerPage, pageNumber);

        bufferedWriter.write(String.join("\n", result) + "\n");

        bufferedReader.close();
        bufferedWriter.close();
    }
}


