package at.ac.fhcampuswien;

public class ChatGPT{
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;

    private static final int N = 1;
    private static final int E = 4;
    private static final int S = 2;
    private static final int W = 8;

    private static final int[] DX = {0, 1, 0, -1};
    private static final int[] DY = {-1, 0, 1, 0};
    private static final int[] OPPOSITE = {S, W, N, E};

    private static void carvePassage(int cx, int cy, int[][] grid) {
        int dx, dy, nx, ny;

        // Shuffle the direction array
        shuffleArray(DX);
        shuffleArray(DY);

        for (int i = 0; i < 4; i++) {
            dx = DX[i];
            dy = DY[i];
            nx = cx + dx;
            ny = cy + dy;

            if (isValidCell(nx, ny) && !isVisited(grid, nx, ny)) {
                grid[cx][cy] |= 1 << i; // Setting the direction bit
                grid[nx][ny] |= 1 << OPPOSITE[i]; // Setting the opposite direction bit
                carvePassage(nx, ny, grid);
            }
        }
    }

    private static void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));

            // Swap elements
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static boolean isValidCell(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    private static boolean isVisited(int[][] grid, int x, int y) {
        return grid[x][y] != 0;
    }

    public static void main(String[] args) {
        int[][] grid = new int[WIDTH][HEIGHT];

        carvePassage(0, 0, grid);

        // Display the grid
        System.out.print(" ");
        for (int x = 0; x < (WIDTH * 2); x++) {
            System.out.print("_");
        }
        System.out.println();

        for (int y = 0; y < HEIGHT; y++) {
            System.out.print("|");
            for (int x = 0; x < WIDTH; x++) {
                System.out.print(((grid[x][y] & S) != 0) ? " " : "_");
                if ((grid[x][y] & E) != 0) {
                    System.out.print(((grid[x][y] | grid[x + 1][y]) & S) != 0 ? " " : "_");
                } else {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }
}