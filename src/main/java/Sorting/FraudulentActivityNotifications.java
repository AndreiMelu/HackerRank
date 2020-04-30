package Sorting;

import java.io.IOException;

import static java.lang.Math.round;

public class FraudulentActivityNotifications {

    static int activityNotifications(int[] expenditure, int d) {
        int rez = 0;
        int sum = 0;
        for (int i = 0; i < d; i++) {
            sum += expenditure[i];
        }
        if (expenditure[d] >= round(sum / d * 2))
            rez++;
        for (int i = 1; i < expenditure.length - d; i++) {
            sum = sum - expenditure[i - 1] + expenditure[i + d - 1];
            if (expenditure[d] >= round(sum / d * 2))
                rez++;
        }
        return rez;
    }


    public static void main(String[] args) throws IOException {
        int[] expenditure = {2, 3, 4, 2, 3, 6, 8, 4, 5};
        int rez = activityNotifications(expenditure, 5);
        System.out.println(rez);
    }
}
