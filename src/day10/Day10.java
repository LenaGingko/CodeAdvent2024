package day10;

import java.util.Arrays;

public class Day10 {
    public static void main(String[] args) {
        final String[][] rawMap = DataReader.readFile();

        Map topoMap = new Map(rawMap);

        printMap(rawMap);
    }

    public static void printMap(String[][] map){
        for (String[] row : map) {
            System.out.println(Arrays.toString(row));
        }
    }
}
