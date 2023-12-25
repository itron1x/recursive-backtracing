package maze;

import java.util.ArrayList;
import java.util.Collections;

public class FileGenerator {
        ArrayList<Integer> directions = new ArrayList<>();
        int length;
        int rows;
        int height = 10;
        int width = 10;
        public int[][] gameBoard;

        public FileGenerator(int[][] gameBoard, int length, int rows) {
            this.gameBoard = gameBoard;
            this.length = length;
            this.rows = rows;
        }

        public void generateDirections() {
            for (int i = 1; i <= 4; i++) {
                directions.add(i);
            }
        }

        public void recursion(int x, int y) {
            Collections.shuffle(directions);
            for (int i = 0; i < directions.size(); i++) {
                //North
                if (directions.get(i) == 1 && x - 2 <= 0) continue;
                if (directions.get(i) == 1 && gameBoard[x - 2][y] != 2 && gameBoard[x - 1][y] != 2  && gameBoard[x - 2][y] != 0) {
                    gameBoard[x - 1][y] = 0;
                    gameBoard[x - 2][y] = 0;
                    recursion(x - 2, y);
                }
                //South
                if (directions.get(i) == 2 && x + 2 >= rows - 1) continue;
                if (directions.get(i) == 2 && gameBoard[x + 2][y] != 2 && gameBoard[x + 1][y] != 2 && gameBoard[x + 2][y] != 0) {
                    gameBoard[x + 1][y] = 0;
                    gameBoard[x + 2][y] = 0;
                    recursion(x + 2, y);
                }
                //East
                if (directions.get(i) == 3 && y + 2 >= length - 1) continue;
                if (directions.get(i) == 3 && gameBoard[x][y + 2] != 2 && gameBoard[x][y + 1] != 2 && gameBoard[x][y + 2] != 0) {
                    gameBoard[x][y + 1] = 0;
                    gameBoard[x][y + 2] = 0;
                    recursion(x, y + 2);
                }
                //West
                if (directions.get(i) == 4 && y - 2 <= 0) continue;
                if (directions.get(i) == 4 && gameBoard[x][y - 2] != 2 && gameBoard[x][y - 1] != 2 && gameBoard[x][y - 2] != 0) {
                    gameBoard[x][y - 1] = 0;
                    gameBoard[x][y - 2] = 0;
                    recursion(x, y - 2);
                }
            }
        }

        public void printMaze(int[][] gameBoard) {
            System.out.println();
            char space = (char) 32;
            char wallChar = (char) 217;
            char raute = (char) 35;
            for (int i = 0; i < rows - 1; i++) {
                for (int j = 0; j < length - 1; j++) {
                    if (gameBoard[i][j] == 0) {
                        System.out.printf("\u001B[36m %2s", space);
                    } else if (gameBoard[i][j] == 2) System.out.printf( "\u001B[33m %2s", raute);
                    else System.out.printf("\u001B[36m %2s", wallChar);
                }
                System.out.println();
            }
        }

        public void generateMaze() {
            generateDirections();
            recursion(1, 1);
            printMaze(gameBoard);
        }
    }
