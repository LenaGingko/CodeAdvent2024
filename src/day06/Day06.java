package day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day06 {

    public static void main(String[] args) {
        String[] guardMap = readFile().split("\n");
        //System.out.println("puzzle: " + Arrays.toString(puzzle));

    }

    static String readFile() {
        try {
            File file = new File("Daten/Werte6.txt");
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
