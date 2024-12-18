package day07;

import java.util.ArrayList;
import java.util.List;

public class Day07 {
    public static void main(String[] args) {
        final List<String> equations = DataExtractor.readFile();

        //System.out.println("Equations: " + String.join("\n", equations));

        long calibrationPart1 = 0;
        long calibrationPart2 = 0;

        for (String equation : equations) {
            String[] parts = equation.split(":");
            long targetValue = Long.parseLong(parts[0].trim());
            String[] numberStrings = parts[1].trim().split(" ");
            List<Integer> numbers = new ArrayList<>();

            for (String num : numberStrings) {
                numbers.add(Integer.parseInt(num));
            }
            //System.out.println("Target value: " + targetValue + " = "+ String.join(" ? ", numbers.toString()));

            if (canProduceTarget(numbers, targetValue)) {
                calibrationPart1 += targetValue;
            }

            if (canProduceTargetWithConcat(numbers, targetValue)) {
                calibrationPart2 += targetValue;
            }
        }
        System.out.println("Total Calibration Result: " + calibrationPart1);
        System.out.println("Total Calibration Result Part 2: " + calibrationPart2);
    }

    private static boolean canProduceTarget(List<Integer> numbers, long target) {
        int n = numbers.size();

        int numOperators = n - 1; //wie viele Operatoren
        int totalCombinations = 1 << numOperators; // 2^operators anstelle von Math.pow https://www.geeksforgeeks.org/shift-operator-in-java/

        for (int mask = 0; mask < totalCombinations; mask++) {
            long result = numbers.get(0);
            StringBuilder equation = new StringBuilder(String.valueOf(numbers.get(0)));

            for (int i = 0; i < numOperators; i++) {
                if ((mask & (1 << i)) == 0) {
                    result += numbers.get(i + 1);
                    equation.append(" + ").append(numbers.get(i + 1));
                } else {
                    result *= numbers.get(i + 1);
                    equation.append(" * ").append(numbers.get(i + 1));
                }
            }

            if (result == target) {
                System.out.println("Equation: " + equation + " = " + result + " should be " + target);
                //System.out.println("SUCCESS!");
                return true;
            }
        }
        return false;
    }

    private static boolean canProduceTargetWithConcat(List<Integer> numbers, long target) {
        return evaluate(numbers, 1, numbers.get(0), target, String.valueOf(numbers.get(0)));
    }

    private static boolean evaluate(List<Integer> numbers, int index, long currentValue, long target, String equation) {

        if (index == numbers.size()) {
            if (currentValue == target) {
                System.out.println("Equation: " + equation + " = " + currentValue + " should be " + target);
                return true;
            }
            return false;
        }

        int nextNumber = numbers.get(index);

        // Case 1: Addition '+'
        if (evaluate(numbers, index + 1, currentValue + nextNumber, target, equation + " + " + nextNumber)) {
            return true;
        }

        // Case 2: Multiplication '*'
        if (evaluate(numbers, index + 1, currentValue * nextNumber, target, equation + " * " + nextNumber)) {
            return true;
        }

        // Case 3: Concatenation '||'
        long concatenatedValue = Long.parseLong("" + currentValue + nextNumber);
        if (evaluate(numbers, index + 1, concatenatedValue, target, equation + " || " + nextNumber)) {
            return true;
        }

        return false;
    }
}
