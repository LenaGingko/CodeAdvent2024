package day07;

import java.util.ArrayList;
import java.util.List;

public class Day07 {
    public static void main(String[] args) {
        final List<String> equations = DataExtractor.readFile();

        System.out.println("Equations: " +
                String.join("\n", equations)
        );

        int totalCalibrationResult = 0;

        for (String equation : equations) {
            // Split target and numbers
            String[] parts = equation.split(":");
            long targetValue = Long.parseLong(parts[0].trim());
            String[] numberStrings = parts[1].trim().split(" ");
            List<Integer> numbers = new ArrayList<>();
            for (String num : numberStrings) {
                numbers.add(Integer.parseInt(num));
            }

            if (canProduceTarget(numbers, targetValue)) {
                totalCalibrationResult += targetValue;
            }
        }
        System.out.println("Total Calibration Result: " + totalCalibrationResult);//1130288313 too low
    }

    private static boolean canProduceTarget(List<Integer> numbers, long target) {
        int n = numbers.size();

        int numOperators = n - 1; // Slots between numbers
        int totalCombinations = 1 << numOperators; // 2^(numOperators)

        for (int mask = 0; mask < totalCombinations; mask++) {
            int result = numbers.get(0);

            for (int i = 0; i < numOperators; i++) {
                if ((mask & (1 << i)) == 0) {
                    result += numbers.get(i + 1);
                } else {
                    result *= numbers.get(i + 1);
                }
            }

            if (result == target) {
                return true;
            }
        }

        return false;
    }
}
