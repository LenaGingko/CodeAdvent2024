package day10;

import java.util.Arrays;

public class Day10 {
    public static void main(String[] args) {
        final int[][] rawMap = DataReader.readFile();

        Map topoMap = new Map(rawMap);

        printMap(rawMap);
    }

    public static void printMap(int[][] map){
        for (int[] row : map) {
            System.out.println(Arrays.toString(row));
        }
    }
}
