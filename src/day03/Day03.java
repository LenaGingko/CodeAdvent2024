package day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Day03 {

    static List<Integer[]> readFile(){
        try {
            File file = new File("Daten/Werte3.txt");
            Scanner scanner = new Scanner(file);

            List<Integer[]> muls = new ArrayList<>();
            String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";//https://data.templateroller.com/pdf_docs_html/2638/26387/2638707/page_1_thumb_950.png
            Pattern pattern = Pattern.compile(regex); //https://www.geeksforgeeks.org/matcher-pattern-method-in-java-with-examples/

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    muls.add(new Integer[]{x, y});
                }
            }
            scanner.close();
            return muls;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public static void main(String[] args) {
        List<Integer[]> mulList = readFile();

        int sum = 0;
        for ( Integer[] mul : mulList ) {
            sum += mul[0] * mul[1];
        }
        System.out.println("Sum: " + sum);
    }
}
