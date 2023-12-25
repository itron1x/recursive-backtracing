package maze;

import java.util.ArrayList;
import java.util.Collections;

public class Generator_v2 {
    ArrayList<Integer> directions = new ArrayList<>();
    int size;
    public int[][] gameBoard;

    public Generator_v2(int size) {
        this.gameBoard = new int[size][size];
        this.size = size;
    }

    public void generateDirections() {
        for (int i = 1; i <= 4; i++) {
            directions.add(i);
        }
    }

    public void fillGameboard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameBoard[i][j] = 1;
            }
        }
    }
    public void recursion(int x, int y) {
        Collections.shuffle(directions);
        for (int i = 0; i < directions.size(); i++) {
            //North
            if (directions.get(i) == 1 && x - 2 <= 0) continue;
            if (directions.get(i) == 1 && gameBoard[x - 2][y] != 0) {
                gameBoard[x - 1][y] = 0;
                gameBoard[x - 2][y] = 0;
                recursion(x - 2, y);
            }
            //South
            if (directions.get(i) == 2 && x + 2 >= size - 1) continue;
            if (directions.get(i) == 2 && gameBoard[x + 2][y] != 0) {
                gameBoard[x + 1][y] = 0;
                gameBoard[x + 2][y] = 0;
                recursion(x + 2, y);
            }
            //East
            if (directions.get(i) == 3 && y + 2 >= size - 1) continue;
            if (directions.get(i) == 3 && gameBoard[x][y + 2] != 0) {
                gameBoard[x][y + 1] = 0;
                gameBoard[x][y + 2] = 0;
                recursion(x, y + 2);
            }
            //West
            if (directions.get(i) == 4 && y - 2 <= 0) continue;
            if (directions.get(i) == 4 && gameBoard[x][y - 2] != 0) {
                gameBoard[x][y - 1] = 0;
                gameBoard[x][y - 2] = 0;
                recursion(x, y - 2);
            }
        }
    }

    public void printMaze(int[][] gameBoard) {
        System.out.println();
        char space = (char) 32;
        char raute = (char) 217;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (gameBoard[i][j] == 0) {
                    System.out.printf("\u001B[36m %2s", space);
                } else System.out.printf("\u001B[36m %2s", raute);
            }
            System.out.println();
        }
    }

    public void generateMaze() {
        generateDirections();
        fillGameboard();
        recursion(1, 1);
        printMaze(gameBoard);
    }
}