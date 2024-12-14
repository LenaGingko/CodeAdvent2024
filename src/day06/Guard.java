package day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Guard {

    Guard(){}

    private int markedPos = 0;
    private int[] startPos = {-1, -1}; //row,col

    //private List<int[]> obstructions = new ArrayList<>();//nötig?

    private int[][] directions = {
            {-1, 0}, // Up
            {0, 1},  // Right
            {1, 0},  // Down
            {0, -1}, // Left
    };

    public void computeMap(String[][] guardMap) {
        findGuard(guardMap);
    }

    private void findGuard(String[][] guardMap) {
        if (guardMap == null || guardMap.length == 0) {
            System.out.println("guardMap is empty or null.");
        }

        for (int row = 0; row < guardMap.length; row++) {
            for (int col = 0; col < guardMap[row].length; col++) {
                /*
                if (guardMap[row][col].equals("#")) {
                    //System.out.println("Obstacle found at position: (" + row + ", " + col + ")");
                    int[] obsPos = {row,col};
                    obstructions.add(obsPos);
                }
                */

                if (guardMap[row][col].equals("^")) {
                    startPos[0] = row;
                    startPos[1] = col;
                }
            }
        }
    }

    public void move(String[][] guardMap) {
        System.out.println("Move Guard");
        int[] currentPos = startPos;

        int currentDir = 0;

        while(guardMap[currentPos[0]+directions[currentDir][0]]  [currentPos[1]+directions[currentDir][1]].equals(".")){//not #
            travel(currentPos, directions, currentDir);
        }
        currentDir = (currentDir + 1) % directions.length;
        System.out.println("TurnRight " + currentDir);
        System.out.println("Currently at " + Arrays.toString(currentPos));
        //go back to while loop with new direction
    }

    private void travel(int[] currentPos, int[][] directions, int currentDir){
        System.out.println("Current Position: " + Arrays.toString(startPos));
        markedPos++;

        int[] nextPos = { currentPos[0]+directions[currentDir][0], currentPos[1]+directions[currentDir][1] };
        currentPos[0] = nextPos[0];
        currentPos[1] = nextPos[1];
    }

    public int getMarkedPos(){
        return markedPos;
    }

    public int[] getStartPos(){
        return startPos;
    }

    /*public List<int[]> getObstructions(){
        return obstructions;
    }*/
}
