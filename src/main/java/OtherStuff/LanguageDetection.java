package OtherStuff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class LanguageDetection {
    public static void main(String[] args) {
        BufferedReader in;
        List<String> lines = new LinkedList<>();
        Set<String> foreignWords = new LinkedHashSet<>();
        try {
            in = new BufferedReader(new FileReader(args[0]));
            while (in.ready()) {
                lines.add(in.readLine());
            }
            String text = lines.get(0);
            List<String> dictionary = Arrays.asList(lines.get(1).split(" "));


            //            Start
            List<String> words = Arrays.asList(text.split(" "));
            String betweenString = "";

            boolean firstQ = false;
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '\"') {
                    firstQ = !firstQ;
                    if (firstQ) {
                        i++;
                        while (text.charAt(i) != '\"') {
                            betweenString += text.charAt(i);
                            i++;
                        }
                        betweenString += " ";
                    }
                }
            }

            Set<String> between = new LinkedHashSet<>(Arrays.asList(betweenString.replaceAll("\"", "").split(" ")));

            for (String foreignWord : words) {
                for (String dict : dictionary) {

                    if (dict.replaceAll("\"", "").toLowerCase().equalsIgnoreCase(foreignWord.replaceAll("\"", "").toLowerCase())) {
                        for (String betw : between) {
                            if (dict.replaceAll("\"", "").toLowerCase().equalsIgnoreCase(betw.toLowerCase())) {
                                foreignWords.addAll(between);
                            }
                        }
                        foreignWords.add(foreignWord.replaceAll("\"", ""));
                    }
                }
            }

            if (foreignWords.isEmpty()) {
                foreignWords.add("0");
            }
//            End


            for (String word : foreignWords) {
                System.out.println(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }
}