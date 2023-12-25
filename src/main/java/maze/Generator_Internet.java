package maze;

import java.util.*;

public class Generator_Internet {
    int size;
    int width;
    int height;
    public int[][] gameBoard;

    public Generator_Internet(int size) {
        this.size = size;
        this.width = size;
        this.height = size;
        this.gameBoard = new int[size][size];
        for (int[] game : gameBoard) {
            Arrays.fill(game, 1);
        }
    }

    public Integer[] randomDirection() {
        ArrayList<Integer> rndm = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            rndm.add(i + 1);
        }
        Collections.shuffle(rndm);
        return rndm.toArray(new Integer[4]);
    }

    public void recursive(int x, int y) {
        Integer[] rndmDir = randomDirection();
        for (Integer integer : rndmDir) {
            switch (integer) {
                case 1: //Up
                    if (x - 2 <= 0) continue;
                    if (gameBoard[x - 2][y] != 0) {
                        gameBoard[x - 1][y] = 0;
                        gameBoard[x - 2][y] = 0;
                        recursive(x - 2, y);
                    }
                    break;
                case 2: // Down
                    if (x + 2 >= height - 1) continue;
                    if (gameBoard[x + 2][y] != 0) {
                        gameBoard[x + 1][y] = 0;
                        gameBoard[x + 2][y] = 0;
                        recursive(x + 2, y);
                    }
                    break;
                case 3: //Left
                    if (y - 2 <= 0) continue;
                    if (gameBoard[x][y - 2] != 0) {
                        gameBoard[x][y - 1] = 0;
                        gameBoard[x][y - 2] = 0;
                        recursive(x, y - 2);
                    }
                    break;
                case 4: //Right
                    if (y + 2 >= width - 1) continue;
                    if (gameBoard[x][y + 2] != 0) {
                        gameBoard[x][y + 1] = 0;
                        gameBoard[x][y + 2] = 0;
                        recursive(x, y + 2);
                    }
                    break;
            }
        }
    }

    public void printMaze() {
        char space = (char) 32;
        char raute = (char) 217;
        for (int i = 0; i < height - 1; i++) {
            for (int j = 0; j < width - 1; j++) {
                if (gameBoard[i][j] == 0) {
                    System.out.printf("%3s", space);
                } else System.out.printf("%3s", raute);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Start here
        Generator_Internet maze = new Generator_Internet(40);
        maze.recursive(1, 1);
        maze.printMaze();
    }
}