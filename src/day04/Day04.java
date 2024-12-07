package day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day04 {
        public static void main(String[] args) {
            String[] puzzle = readFile().split("\n");
            System.out.println("puzzle: " + Arrays.toString(puzzle));
            //int count = countXMAS(puzzle);
            //System.out.println("Total XMAS occurrences: " + count );
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
}
