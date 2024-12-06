package day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day02 {

    static List<Integer[]> readFile(){
        try {
            File file = new File("Daten/Werte2.txt");

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

        //reports.forEach(report -> System.out.println(Arrays.toString(report)));
        // ArrayList of Arrays[]
        int areSafe = 0;

        for(Integer[] report : reports){
            System.out.println("report: " + Arrays.toString(report));

            if (isReportSafe(report)) {
                areSafe++;
                continue;
            }

            boolean safeAfterRemoval = false; //1 Ausnahme pro report
            for (int i = 0; i < report.length; i++) {
                Integer[] modifiedReport = removeLevel(report, i);
                if (isReportSafe(modifiedReport)) {
                    safeAfterRemoval = true;
                    break;
                }
            }

            // If the report is safe after removal, count it as safe
            if (safeAfterRemoval) {
                areSafe++;
            }
        }

        System.out.println("areSafe Gesamt: " + areSafe);

    }

    public static boolean isReportSafe(Integer[] report) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;
        System.out.println(Arrays.toString(report));

        for (int i = 0; i < report.length-1; i++) { // minus da i+1 sonst Bounds
            if(Math.abs(report[i]-report[i + 1]) < 4) { //differenz über 3 scheidet aus

                if (report[i] <= report[i + 1]) { //differenz 0 scheidet aus
                    isDecreasing = false;
                }
                if (report[i] >= report[i + 1]) {
                    isIncreasing = false;
                }

            } else {
                isDecreasing = false;
                isIncreasing = false;
            }
            //System.out.println("Difference smaller than 4 " + (Math.abs(report[i]-report[i + 1]) < 4) );
            //System.out.println("Increase? " + isIncreasing + " - Decrease? " + isDecreasing);
        }
        return isIncreasing || isDecreasing; // The report is safe if it's either entirely increasing or decreasing
    }

    // Helper method to remove the level at index i
    public static Integer[] removeLevel(Integer[] report, int index) {
        List<Integer> modifiedReportList = new ArrayList<>(Arrays.asList(report));
        modifiedReportList.remove(index);
        return modifiedReportList.toArray(new Integer[0]);
    }
}
