package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReader {

    private DataReader() {}

    static String[][] readFile() {
        try {
            File file = new File("Daten/Werte10.txt");
            Scanner scanner = new Scanner(file);

            List<String[]> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] splitted = line.split("");
                lines.add(splitted);
            }
            scanner.close();

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