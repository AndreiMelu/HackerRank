import java.util.Scanner;
import java.util.StringTokenizer;

public class Princess {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        StringTokenizer st = new StringTokenizer(scanner.nextLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        String[] grid = new String[n];
        for (int row = 0; row < n; row++) {
            grid[row] = scanner.nextLine();
        }

        nextMove(n, r, c, grid);

        scanner.close();
    }

    private static void nextMove(int n, int r, int c, String[] grid) {
        String strNextMove = "";

        int pRow = -1;
        int pCol = -1;
        for (int row = 0; row < n; row++) {
            int pos = grid[row].indexOf('p');
            if (pos >= 0) {
                pRow = row;
                pCol = pos;
                break;
            }
        }

        if (pRow == -1 || pCol == -1) {
            return;
        }

        if (r == pRow) {
            int hMove = c - pCol;
            if (hMove > 0) {
                strNextMove = "LEFT";
            } else if (hMove < 0) {
                strNextMove = "RIGHT";
            }
        } else {
            int vMove = r - pRow;
            if (vMove > 0) {
                strNextMove = "UP";
            } else if (vMove < 0) {
                strNextMove = "DOWN";
            }
        }

        grid[r].replace('m', '-');
        switch (strNextMove) {
            case "UP":
                grid[--r].toCharArray()[c] = 'm';
                break;

            case "DOWN":
                grid[++r].toCharArray()[c] = 'm';
                break;

            case "LEFT":
                grid[r].toCharArray()[--c] = 'm';
                break;

            case "RIGHT":
                grid[r].toCharArray()[++c] = 'm';
                break;
        }

        System.out.println(strNextMove);
    }
}
