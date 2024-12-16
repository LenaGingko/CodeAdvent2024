package day07;

import day06.Guard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day07 {
    public static void main(String[] args) {
        final List<String> equations = readFile();

        System.out.println("Equations: " +
                String.join("\n", equations)
        );
    }


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
