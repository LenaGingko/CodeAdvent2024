package day10;

import java.util.Arrays;

public class Day10 {
    public static void main(String[] args) {
        final String[][] topoMap = DataReader.readFile();

        System.out.println("map: " + Arrays.deepToString(topoMap));
    }
}
