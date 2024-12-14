package day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day06 {

    public static void main(String[] args) {
        String[][] guardMap = readFile();
        System.out.println("map: " + Arrays.deepToString(guardMap));

        Guard guard = new Guard();
        guard.move(guardMap);
        System.out.println("Start Position: " + guard.getMarkedPos());
        System.out.println("Obstruction Positions: " + Arrays.toString(guard.getStartPos()));
        System.out.println("Distinct Positions: " + guard.getObstructions());

    }


    static String[][] readFile() {
        try {
            File file = new File("Daten/Werte6.txt");
            Scanner scanner = new Scanner(file);

            List<String[]> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] splitted = line.split("\n");
                lines.add(splitted);
            }
            scanner.close();

            // List<String[]> -> String[][]
            String[][] result = new String[lines.size()][];
            for(int i = 0; i<result.length; i++) {
                result[i] = lines.get(i);
            }

            return result;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return null;
        }
    }
}
