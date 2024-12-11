package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day05 {

    public static void main(String[] args) {
        Data data = readFile("Daten/Werte5.txt");

        List<Integer[]> validUpdates = filterData(data);
        System.out.println("all Updates: " + data.updates.size());
        System.out.println("rules: " + data.rules);

        System.out.println("Updates: " +
                data.updates.stream()
                        .map(Arrays::toString)
                        .collect(Collectors.joining(", "))
        );


        System.out.println("validUpdates length: " + validUpdates.size() );

        int sum = 0;
        for (Integer[] update : validUpdates) { //5891 too high
            int middlePage = findMiddle(update);
            sum += middlePage;
            System.out.println("Middle Page von Update: " + Arrays.toString(update) +" = "+ middlePage + " -> " + sum);
        }
        System.out.println("Summe: " + sum);
    }

    static int findMiddle(Integer[] update) {
        int middle = update.length / 2;
        return update[middle];
    }

    static List<Integer[]> filterData(Data data) {
        List<Integer[]> validUpdatesList = new ArrayList<>();

        for (Integer[] update : data.updates) {
            if (isUpdateValid(update, data.rules)) {
                validUpdatesList.add(update);
            }
            if (!isUpdateValid(update, data.rules)) {
                System.out.println("Invalid Update: " + Arrays.toString(update));
            }
        }
        return validUpdatesList;
    }

    static boolean isUpdateValid(Integer[] update, Map<Integer, List<Integer>> rules) {

        Set<Integer> ruleKeys = rules.keySet();

        //updates loop
        List<Integer> updateList = Arrays.asList(update);//für contains
        //wenn nummer in  rules.entrySet()
        for (Integer i : ruleKeys) {
            if (!updateList.contains(i)) continue;
            for (Integer j : rules.get(i)) {
                if (!updateList.contains(j)) continue;
                int positionKey = updateList.indexOf(i);
                int positionValue = updateList.indexOf(j);

                if (positionKey >= positionValue) {
                    return false;
                }
            }
        }
        return true;
    }

    static Data readFile(String filePath) {
        Map<Integer, List<Integer>> rules = new HashMap<>();
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
                        List<Integer> currentRule = rules.getOrDefault(links, new ArrayList<>());
                        currentRule.add(rechts);
                        rules.put(links, currentRule);
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
