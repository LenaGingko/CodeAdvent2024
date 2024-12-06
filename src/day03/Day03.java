package day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Day03 {

    static String readFile(){
        try {
            File file = new File("Daten/Werte3.txt");
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

    static List<Integer[]> computeInput(String input){

        Pattern regex = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Pattern doPattern = Pattern.compile("do\\(\\)");
        Pattern dontPattern = Pattern.compile("don't\\(\\)");

        int currentIndex = 0;
        List<Integer[]> muls = new ArrayList<>();
        boolean isEnabled = true;

        Matcher doMatcher = doPattern.matcher(input);
        Matcher dontMatcher = dontPattern.matcher(input);
        Matcher regexMatcher = regex.matcher(input);

        while (currentIndex < input.length()) {
            if (doMatcher.find(currentIndex) && doMatcher.start() == currentIndex) {
                isEnabled = true;
                currentIndex = doMatcher.end();
            } else if (dontMatcher.find(currentIndex) && dontMatcher.start() == currentIndex) {
                isEnabled = false;
                currentIndex = dontMatcher.end();

            } else if (regexMatcher.find(currentIndex) && regexMatcher.start() == currentIndex) {

                if (isEnabled) {
                    int mul1 = Integer.parseInt(regexMatcher.group(1));
                    int mul2 = Integer.parseInt(regexMatcher.group(2));
                    muls.add(new Integer[]{mul1, mul2});
                }
                currentIndex = regexMatcher.end();

            } else {
                currentIndex++;
            }
        }
        return muls;
    }
    public static void main(String[] args) {

        String input = readFile();

        List<Integer[]> mulList = computeInput(input);

        //mulList.forEach(mul -> System.out.println(Arrays.toString(mul)));

        int sum = mulList.stream()
                .mapToInt(mul -> mul[0] * mul[1])
                .sum();

        System.out.println("Sum: " + sum);
    }
}
