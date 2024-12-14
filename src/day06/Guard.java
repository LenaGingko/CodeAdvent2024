package day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Guard {

    Guard(){}

    private int markedPos = 0;
    private int[] startPos = {-1, -1}; //row,col

    private List<int[]> obstructions = new ArrayList<>();

    public void computeMap(String[][] guardMap) {

        if (guardMap == null || guardMap.length == 0) {
            System.out.println("guardMap is empty or null.");
            return;
        }

        for (int row = 0; row < guardMap.length; row++) {
            for (int col = 0; col < guardMap[row].length; col++) {
                if (guardMap[row][col].equals("#")) {
                    //System.out.println("Obstacle found at position: (" + row + ", " + col + ")");
                    int[] obsPos = {row,col};
                    obstructions.add(obsPos);
                }

                if (guardMap[row][col].equals("^")) {
                    System.out.println("Guard found at position: (" + row + ", " + col + ")");
                    startPos[0] = row;
                    startPos[1] = col;
                }
            }
        }
    }

    public void move(String[][] guardMap) {
        System.out.println("Move Guard");
        int[] currentPos = startPos;

        //directions
        int[][] directions = {
                {0, 1},  // Right
                {1, 0},  // Down
                {0, -1}, // Left
                {-1, 0}, // Up
        };

        while(guardMap[currentPos[0]-1][currentPos[1]].equals(".")){
            currentPos[0]--;//nur nach oben
        }
        System.out.println("Currently at " + Arrays.toString(currentPos));
    }

    public int getMarkedPos(){
        return markedPos;
    }

    public int[] getStartPos(){
        return startPos;
    }

    public List<int[]> getObstructions(){
        return obstructions;
    }
}
