package day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day04 {
    public static void main(String[] args) {

        String input = readFile();
        System.out.println(input);
    }

    static String readFile(){
        try {
            File file = new File("Daten/Werte4.txt");
            Scanner scanner = new Scanner(file);

            StringBuilder fullText = new StringBuilder();
            while (scanner.hasNextLine()) {
                fullText.append(scanner.nextLine());
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
