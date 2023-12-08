import at.ac.fhcampuswien.Generator_v2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int sizeBoard;
        while (true) {
            System.out.print("Enter a Size (bigger than 20) of the Maze: ");
            try {
                sizeBoard = scn.nextInt();
                if (sizeBoard % 2 == 0 && sizeBoard >= 20) break;
            } catch (InputMismatchException e) {
                System.out.println("<Please put in a number!>");
                scn.nextLine();
            }
        }
        Generator_v2 maze = new Generator_v2(sizeBoard);
        maze.generateMaze();
    }
}