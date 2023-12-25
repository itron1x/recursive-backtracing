package maze;

import maze.FileGenerator;
import maze.Generator_v2;

import java.io.File;
import java.io.FileNotFoundException;
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

        List<String> returnList = new ArrayList<>();
        try {
            Scanner scnLine = new Scanner(new File("src/main/resources/map.txt"));
            while (scnLine.hasNextLine()) {
                returnList.add(scnLine.nextLine());
                returnList.add(" ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
        int loadedSizeBoard = returnList.get(0).length();
        int rows = 0;
        List<String> intList = new ArrayList<>();
        for (String s : returnList) {
            if (s.charAt(0) != ' ') {
                for (int i = 0; i < s.length(); i++) {
                    intList.add(String.valueOf(s.charAt(i)));
                }
                rows++;
            }
        }
        int[][] loadedGameBoard = new int[rows][returnList.get(0).length()];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < returnList.get(0).length(); j++) {
                loadedGameBoard[i][j] = Integer.parseInt(intList.get((i * returnList.get(0).length())+ j));
            }
        }

        String affirm;
        System.out.print("\u001B[33m Load from File? (<Yes/No>): ");
        affirm = scn.next();
        if (affirm.equals("Yes") || affirm.equals("y") || affirm.equals("Y")) {
            FileGenerator loadedMaze = new FileGenerator(loadedGameBoard, loadedSizeBoard, rows);
            loadedMaze.generateMaze();
        } else System.out.println("File not loaded!");
    }
}