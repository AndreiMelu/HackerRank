import java.util.Scanner;

public class BotSavesPrincess {

    static void displayPathosPrincess(int n, String[] grid) {
        int bX = n / 2, bY = n / 2;
        int pX = 0, pY = 0;

        if (grid[0].charAt(0) == 'p') {
            pX = 0;
            pY = 0;
        } else if (grid[0].charAt(n - 1) == 'p') {
            pX = n - 1;
            pY = 0;
        } else if (grid[n - 1].charAt(0) == 'p') {
            pX = 0;
            pY = n - 1;
        } else {
            pX = n - 1;
            pY = n - 1;
        }

        while (bX != pX && bY != pY) {
            if (pY == 0) {
                System.out.println("UP");
                bY--;
            } else {
                System.out.println("DOWN");
                bY++;
            }

            if (pX == 0) {
                System.out.println("LEFT");
                bX--;
            } else {
                System.out.println("RIGHT");
                bX++;
            }
        }

        return;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m;
        m = in.nextInt();
        String grid[] = new String[m];
        for (int i = 0; i < m; i++) {
            grid[i] = in.next();
        }

        displayPathosPrincess(m, grid);
    }
}