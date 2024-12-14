package day06;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Guard {

    Guard(){}

    private int markedPos = 0;
    private int[] startPos = {-1, -1};

    private List<int[]> obstructions = new ArrayList<>();

    public void move(String[][] guardMap) {
        for (int row = 0; row < guardMap.length; row++) {
            for (int col = 0; col < guardMap[row].length; col++) {
                if (guardMap[row][col].equals("#")) {
                    System.out.println("Obstacle found at position: (" + row + ", " + col + ")");
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
