import at.ac.fhcampuswien.Generator_v2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int sizeBoard;
        while (true) {
            System.out.print("\u001B[33m Enter the Size of the Maze (bigger than 20 and even): ");
            try {
                sizeBoard = scn.nextInt();
                if (sizeBoard % 2 == 0 && sizeBoard > 20) break;
                if (sizeBoard % 2 != 0) System.out.println("\u001B[31m <Enter an even number!>");
                if (sizeBoard <= 20) System.out.println("\u001B[31m <Enter a bigger number than 20!>");
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31m <Please put in a number!>");
                scn.nextLine();
            }
        }
        Generator_v2 maze = new Generator_v2(sizeBoard);
        maze.generateMaze();
    }
}