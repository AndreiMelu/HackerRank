import java.util.Scanner;

public class NewYearChaos {

    static void minimumBribes(int[] q) {
        int count = 0;

        //always keep track of min three vlaues observed
        //Compare currently seeing value with this three values.
        int midOfThree = Integer.MAX_VALUE;
        int maxOfThree = Integer.MAX_VALUE;
        int minOfThree = Integer.MAX_VALUE;

        //iterating from left to right
        for (int i = q.length - 1; i >= 0; i--) {
            //person has no way to move more than two positions -> wrong
            if ((q[i] - i) > 3) {
                System.out.println("Too chaotic");
                return;
            } else {
                //means current value has passed at least 3 values -> wrong
                if (q[i] > maxOfThree) {
                    System.out.println("Too chaotic");
                    return;
                } else if (q[i] > midOfThree) { //means -> current value has bribed 2 ppl
                    count = count + 2;
                } else if (q[i] > minOfThree) { //means-> current value has bribed 1 person.
                    count = count + 1;
                }

                //Now adjust minThree values comparing, taking the current vlaue to account
                if (q[i] < minOfThree) {
                    maxOfThree = midOfThree;
                    midOfThree = minOfThree;
                    minOfThree = q[i];
                } else if (q[i] < midOfThree) {
                    maxOfThree = midOfThree;
                    midOfThree = q[i];
                } else if (q[i] < maxOfThree) {
                    maxOfThree = q[i];
                }
            }
        }
        System.out.println(count);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
