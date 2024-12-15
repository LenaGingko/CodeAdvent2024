package day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Guard {

    Guard(){}

    private int markedPos = 0;
    private int[] startPos = {-1, -1}; //row,col

    private List<int[]> distinctPos = new ArrayList<>();

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
                if (guardMap[row][col].equals("^")) {
                    startPos[0] = row;
                    startPos[1] = col;
                }
            }
        }
    }

    public void move(String[][] guardMap) {
        System.out.println("Move Guard");
        int[] currentPos = startPos;//int[] currentPos = Arrays.copyOf(startPos, startPos.length);
        int currentDir = 0;//up

        while (true) {

            int[] nextPos = {currentPos[0]+directions[currentDir][0],currentPos[1]+directions[currentDir][1]};

            //check border
            if (nextPos[0] < 0 || nextPos[0] >= guardMap.length ||
                    nextPos[1] < 0 || nextPos[1] >= guardMap[0].length) {
                System.out.println("Grenze!");
                break;
            }

            // if #
            if (guardMap[nextPos[0]][nextPos[1]].equals(".")) {
                currentPos = travel(currentPos, directions, currentDir);

                nextPos[0] = currentPos[0] + directions[currentDir][0];
                nextPos[1] = currentPos[1] + directions[currentDir][1];

            } else {
                currentDir = (currentDir + 1) % directions.length;//rechts
                System.out.println("TurnRight " + currentDir);
                System.out.println("Currently at " + Arrays.toString(currentPos));
            }
        }
    }

    private int[] travel(int[] currentPos, int[][] directions, int currentDir){
        //System.out.println("Current Position: " + Arrays.toString(currentPos));

        //check distinct
        int[] positionCopy = Arrays.copyOf(currentPos, currentPos.length); //damit es nicht mitupdatet
        if (!distinctPos.contains(positionCopy)) {
            markedPos++;
            //System.out.println("Positions -> " + markedPos);
            distinctPos.add(positionCopy);
        }

        currentPos[0] += directions[currentDir][0];
        currentPos[1] += directions[currentDir][1];
        return currentPos;
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
