import java.io.*;

public class DayOfTheProgrammer {


    // Complete the dayOfProgrammer function below.
    static String dayOfProgrammer(int year) {
        if (year == 1918) {
            return "26.09.1918";
        } else if ((year < 1918 && year % 4 == 0) || (year > 1918 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0))) {
            return "12.09." + year;
        } else {
            return "13.09." + year;
        }
    }

    static String dayOfProgrammer1(int year) {
        //Initialize DOY, month, and day ints for return string
        int dayOfYear = 256;
        int resultMonth = 0;
        int resultDay = 0;

        //intialize array to hold # of days in each month
        int[] daysPerMonth = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        //conditions to determine months in Feb (if not 28)
        if (year == 1918) {
            daysPerMonth[2] -= 13;
        } else if ((year < 1918 && year % 4 == 0) || (year > 1918 && ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)))) {
            daysPerMonth[2]++;
        }

        //Loop thru DPM to find date for DOY by subtracting days in each month until DOY < DPM
        for (int m = 0; m < daysPerMonth.length; m++) {

            if (dayOfYear <= daysPerMonth[m]) {
                resultMonth = m;

                if (m == 2 && year == 1918) {
                    dayOfYear += 13;
                }
                resultDay = dayOfYear;
                break;
            } else {
                dayOfYear -= daysPerMonth[m];
            }
        }

        return resultDay + ".0" + resultMonth + "." + year;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        String result = dayOfProgrammer(year);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
