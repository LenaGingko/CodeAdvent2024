package day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day03 {

    static List<Integer[]> readFile(){
        try {
            File file = new File("Daten/Werte3.txt");

            Scanner scanner = new Scanner(file);

            List<Integer[]> reports = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.trim().split("\\s+");

                //System.out.println("Length report: " + parts.length); //wie viel Level
                //Arrays.stream(parts).forEach(System.out::println); //Array []
                if (parts.length != 0) {
                    Integer[] integers = Arrays.stream(parts)
                            .map(Integer::parseInt) // Convert each string to an integer
                            .toArray(Integer[]::new); // Collect as an Integer array
                    reports.add(integers);
                }
            }
            scanner.close();

            return reports;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public static void main(String[] args) {
        List<Integer[]> reports = readFile();
    }
}
