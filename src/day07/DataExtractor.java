package day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataExtractor {

    private DataExtractor() {}

    static List<String> readFile() {
        try {
            File file = new File("Daten/Werte7.txt");
            Scanner scanner = new Scanner(file);

            List<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                lines.add(line);
            }
            scanner.close();

            return lines;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return null;
        }
    }
}
