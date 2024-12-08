package day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day04 {
        public static void main(String[] args) {
            String[] puzzle = readFile().split("\n");
            //System.out.println("puzzle: " + Arrays.toString(puzzle));
            int count = countXMAS(puzzle);
            System.out.println("Total XMAS occurrences: " + count );
            System.out.println("Total X-MAS occurrences: " + countXMASPattern(puzzle));//980 too low //1836 falsch, 3000zu hoch
        }

        static String readFile() {
            try {
                File file = new File("Daten/Werte4.txt");
                Scanner scanner = new Scanner(file);

                StringBuilder fullText = new StringBuilder();
                while (scanner.hasNextLine()) {
                    fullText.append(scanner.nextLine()).append("\n");
                }
                scanner.close();
                return fullText.toString();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();
                return "";
            }
        }

    static int countXMAS(String[] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length(); //alle gleich lang?

        // {row_offset, col_offset}
        int[][] directions = {
                {0, 1},  // Right
                {1, 0},  // Down
                {0, -1}, // Left
                {-1, 0}, // Up
                {1, 1},  // Down-right diagonal
                {1, -1}, // Down-left diagonal
                {-1, 1}, // Up-right diagonal
                {-1, -1} // Up-left diagonal
        };

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int[] dir : directions) {
                    if (checkWord(grid, row, col, dir[0], dir[1])) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    static int countXMASPattern(String[] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length();

        for (int row = 0; row < rows - 1; row++) { // X-MAS  3 Zeilen
            for (int col = 0; col < cols - 1; col++) { // X-MAS 3 Säulen
                if (grid[row].charAt(col) == 'A'){ // A weil zentrum des Patterns
                    if (checkXMASPattern(grid, row, col)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    static boolean checkWord(String[] grid, int startRow, int startCol, int rowDir, int colDir) {
        String word = "XMAS";
        int rows = grid.length;
        int cols = grid[0].length();

        for (int i = 0; i < word.length(); i++) {
            int nextRow = startRow + i * rowDir;
            int nextCol = startCol + i * colDir;

            //ob ende
            if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) {
                return false;
            }

            if (grid[nextRow].charAt(nextCol) != word.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    static boolean checkXMASPattern(String[] grid, int row, int col) {
        String mas = "MAS";

        try {                       //top           left
            boolean topLeft = grid[row - 1].charAt(col - 1) == mas.charAt(0) && grid[row + 1].charAt(col + 1) == mas.charAt(2);
            boolean topRight = grid[row - 1].charAt(col + 1) == mas.charAt(0) && grid[row + 1].charAt(col - 1) == mas.charAt(2);
            boolean bottomLeft = grid[row + 1].charAt(col - 1) == mas.charAt(0) && grid[row - 1].charAt(col + 1) == mas.charAt(2);
            boolean bottomRight = grid[row + 1].charAt(col + 1) == mas.charAt(0) && grid[row - 1].charAt(col - 1) == mas.charAt(2);

            return (topLeft && (bottomLeft ||  topRight)) || ((topRight || bottomLeft) && bottomRight) ;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}
