package day06;

import java.util.*;

public class Guard {

    Guard(){}

    private int markedPos = 0;
    private int[] startPos = {-1, -1}; //row,col
    private Set<String> distinctPos = new HashSet<>();

    private static final int[][] DIRECTIONS = {
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

        System.out.println("Border height: " + guardMap.length + " length: " + guardMap[0].length);
    }

    public void move(String[][] guardMap) {
        System.out.println("Move Guard");
        int[] currentPos = Arrays.copyOf(startPos, startPos.length);
        distinctPos.add(currentPos[0] + "," + currentPos[1]);
        int currentDir = 0; // Up

        while (true) {
            int[] nextPos = {currentPos[0] + DIRECTIONS[currentDir][0],
                    currentPos[1] + DIRECTIONS[currentDir][1]};

            if (nextPos[0] < 0 || nextPos[0] >= guardMap.length ||
                    nextPos[1] < 0 || nextPos[1] >= guardMap[0].length) {
                System.out.println("Grenze!");
                break;
            }

            if (guardMap[nextPos[0]][nextPos[1]].equals(".")) {

                currentPos = travel(currentPos, currentDir);
            } else {

                currentDir = (currentDir + 1) % DIRECTIONS.length;
                System.out.println("TurnRight " + currentDir);
                System.out.println("Currently at " + Arrays.toString(currentPos));
            }
        }
    }

    private int[] travel(int[] currentPos, int currentDir) {

        int[] newPos = {currentPos[0], currentPos[1]};
        String positionKey = newPos[0] + "," + newPos[1];

        if (!distinctPos.contains(positionKey)) {
            markedPos++;
            System.out.println("Positions -> " + markedPos + ", Current Position: [" + newPos[0] + ", " + newPos[1] + "]");
            distinctPos.add(positionKey);
        } else {
            System.out.println("Duplicate Position: [" + newPos[0] + ", " + newPos[1] + "]");
        }

        // Move the guard to the next position
        currentPos[0] += DIRECTIONS[currentDir][0];
        currentPos[1] += DIRECTIONS[currentDir][1];

        return currentPos;
    }

    private boolean containsPosition(List<int[]> positions, int[] pos) {
        for (int[] p : positions) {
            if (Arrays.equals(p, pos)) {
                return true;
            }
        }
        return false;
    }

    public int getMarkedPos(){
        return markedPos;
    }

    public int[] getStartPos(){
        return startPos;
    }
}
