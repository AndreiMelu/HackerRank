import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MeanMedianMode {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Double> list = new ArrayList<>();

        double sum = 0, mean, median, mode;
        int c, max = 0;

        double value;
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            value = sc.nextDouble();
            sum += value;
            list.add(value);
        }

        mean = sum / n;

        Collections.sort(list);
        median = getMedian(list, n);
        mode = getMode(list, max, n);

        System.out.println(mean);
        System.out.println(median);
        System.out.println((int) mode);
        sc.close();
    }

    private static double getMedian(ArrayList<Double> list, int n) {
        double median;
        int a = n - 1;
        if (n % 2 == 0) {
            median = (list.get((a / 2)) + list.get((a / 2) + 1)) / 2;
        } else {
            median = (list.get(a / 2)) / 2;
        }
        return median;
    }

    private static double getMode(ArrayList<Double> list, int max, int n) {
        double mode;
        int c;
        mode = list.get(0);
        for (int j = 0; j < n; j++) {
            double itr = list.get(j);
            c = 0;
            for (int k = 0; k < n; k++) {
                if ((itr == list.get(k))) {
                    c++;
                    if (c > max) {
                        max = c;
                        if (max == 1) {
                            mode = list.get(0);
                        }
                        mode = itr;
                    }
                }
            }
        }
        return mode;
    }

}