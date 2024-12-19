package day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataReader {

    private DataReader() {}

    static String readFile() {
        try {
            File file = new File("Daten/Werte9.txt");
            Scanner scanner = new Scanner(file);

            String line = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().trim();
            }
            scanner.close();

            return line;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return null;
        }
    }
}
