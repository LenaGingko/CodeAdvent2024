package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataReader {

    private DataReader() {}

    static int[][] readFile() {
        try {
            File file = new File("Daten/Werte10.txt");
            Scanner scanner = new Scanner(file);

            List<int[]> lines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                int[] row = Arrays.stream(line.split("")) // Split string
                        .mapToInt(Integer::parseInt)
                        .toArray();
                lines.add(row);
            }
            scanner.close();
            return lines.toArray(new int[0][]);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return null;
        }
    }
}