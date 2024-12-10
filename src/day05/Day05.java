package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day05 {

    public static void main(String[] args) {
        Data data = readFile("Daten/Werte5.txt");

        List<Integer[]> validUpdates = filterData(data);

        System.out.println("rules: " + data.rules);
        System.out.println("Updates: " +
                data.updates.stream()
                        .map(Arrays::toString) // Convert each Integer[] to a string
                        .collect(Collectors.joining(", ")) // Join them with a comma and space
        );


        System.out.println("validUpdates length: " + validUpdates.size() );

        int sum = 0;
        for (Integer[] update : validUpdates) {
            int middlePage = findMiddlePage(update);
            sum += middlePage;
        }

        // Output the result
        System.out.println("Summe: " + sum);
    }

    static int findMiddlePage(Integer[] update) {
        int middleIndex = update.length / 2;
        return update[middleIndex];
    }

    static List<Integer[]> filterData(Data data) {
        List<Integer[]> validUpdatesList = new ArrayList<>();

        for (Integer[] update : data.updates) {
            if (isUpdateValid(update, data.rules)) {
                validUpdatesList.add(update);
            }
        }
        return validUpdatesList;
    }

    static boolean isUpdateValid(Integer[] update, Map<Integer, Integer> rules) {

        Set<Integer> ruleKeys = rules.keySet();

        //updates loop
        List<Integer> updateList = Arrays.asList(update);//für contains
        //wenn nummer in  rules.entrySet()
        for (Integer i : ruleKeys) {
            //check key
            if (updateList.contains(i)) {
                Integer j = rules.get(i);

                //check value
                if (updateList.contains(j)) {
                    int positionKey = updateList.indexOf(i);//stelle in List
                    int positionValue = updateList.indexOf(j);

                    if (positionKey >= positionValue) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    static Data readFile(String filePath) {
        Map<Integer, Integer> rules = new HashMap<>();
        List<Integer[]> updates = new ArrayList<>();
        boolean linebreak = true;

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    linebreak = false; //false rules, true updates
                    continue;
                }

                if (linebreak) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 2) { //2 werte
                        int links = Integer.parseInt(parts[0].trim());
                        int rechts = Integer.parseInt(parts[1].trim());
                        rules.put(links, rechts);
                    }
                } else {
                    String[] updateParts = line.split(",");
                    Integer[] updateArray = Arrays.stream(updateParts)
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .toArray(Integer[]::new);
                    updates.add(updateArray);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }

        return new Data(rules, updates);
    }
}
