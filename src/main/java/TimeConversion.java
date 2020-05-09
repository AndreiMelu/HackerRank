import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class TimeConversion {

    static String timeConversion(String s) {
        if (s.substring(8).equals("PM")) {
            if (s.substring(0, 2).equals("12")) {
                s = s.substring(0, 8);
            } else {
                int hours = Integer.parseInt(s.substring(0, 2)) + 12;
                s = hours + s.substring(2, 8);
            }
        } else if (s.substring(0, 2).equals("12")) {
            s = "00" + s.substring(2, 8);
        } else s = s.substring(0, 8);
        return s;
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scan.nextLine();
        String result = timeConversion(s);

        bw.write(result);
        bw.newLine();

        bw.close();
    }
}

