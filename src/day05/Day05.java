package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day05 {

    public static void main(String[] args) {
        String[] puzzle = readFile().split("\n");
        System.out.println("puzzle: " + Arrays.toString(puzzle));
        //System.out.println("Total XMAS occurrences: " + count );
    }

    static String readFile() {
        try {
            File file = new File("Daten/Werte5.txt");
            Scanner scanner = new Scanner(file);

            List<Integer> lList = new ArrayList<>();
            List<Integer> rList = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.trim().split("\\s+");//Leerzeichen trennt
                if (parts.length == 2) {
                    lList.add(Integer.parseInt(parts[0]));
                    rList.add(Integer.parseInt(parts[1]));
                }
            }
            scanner.close();

            return "";
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return "";
        }
    }
}
