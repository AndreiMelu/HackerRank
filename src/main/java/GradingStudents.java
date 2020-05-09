import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class GradingStudents {
    public static List<Integer> gradingStudents(List<Integer> grades) {
        // Write your code here
        List finalGrades = new ArrayList<Integer>();
        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i) < 38) {
                finalGrades.add(grades.get(i));
            } else {
                if (grades.get(i) % 5 > 2) {
                    finalGrades.add(((grades.get(i) / 5)) * 5 + 5);
                } else {
                    finalGrades.add(grades.get(i));
                }
            }
        }
//        Simplified
//        for (int grade : grades)
//            finalGrades.add((grade < 38 || grade % 5 < 3 ? grade : grade + (5 - (grade % 5))));
        return finalGrades;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int gradesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> grades = IntStream.range(0, gradesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = gradingStudents(grades);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
