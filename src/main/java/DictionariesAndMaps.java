import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DictionariesAndMaps {
//Complete this code or write your own from scratch

    public static void main(String[] argh) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Map<String, Integer> phoneBook = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String name = in.next();
            int phone = in.nextInt();
            phoneBook.put(name, phone);
        }
        while (in.hasNext()) {
            String s = in.next();
            int number = phoneBook.getOrDefault(s, -1);
            System.out.println((number == -1) ? "Not found" : s + "="+number);

        }
        in.close();
    }
}