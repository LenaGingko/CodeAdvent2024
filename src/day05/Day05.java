package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day05 {

    public static void main(String[] args) {
        ParsedData data = readFile("Daten/Werte5.txt");

        System.out.println("rules: " + data.rules);
        System.out.println("updates: " + data.updates);
    }

    static ParsedData readFile(String filePath) {
        List<String> rules = new ArrayList<>();
        List<String> updates = new ArrayList<>();
        boolean isRuleSection = true;

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Parse the file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Check for empty line to switch sections
                if (line.isEmpty()) {
                    isRuleSection = false; // Switch to updates section
                    continue;
                }

                // Add line to the appropriate section
                if (isRuleSection) {
                    rules.add(line);
                } else {
                    updates.add(line);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }
        return new ParsedData(rules, updates);
    }

}
