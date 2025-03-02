package day10;


import java.util.HashSet;
import java.util.Set;

public class Map {

    private final int[][] map;
    private String [][] trailhead;
    //A trailhead is any position that starts one or more hiking trails - here, these positions will always have height 0.
    // Assembling more fragments of pages, you establish that a trailhead's score is the number of 9-height positions reachable from that trailhead via a hiking trail.
    // In the above example, the single trailhead in the top left corner has a score of 1 because it can reach a single 9 (the one in the bottom left).
    private int sumOfScores = 0;

    public Map(int[][] map) {
        this.map = map;

    }

    public Set<Trailhead> findTrailheads() {
        Set<Trailhead> trailheads = new HashSet<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    Trailhead trailhead = new Trailhead(i, j);
                    int score = countHikingTrails(i, j);
                    trailhead.setScore(score);
                    trailheads.add(trailhead);
                }
            }
        }
        return trailheads;
    }

    private int countHikingTrails(int startX, int startY) {
        Set<String> visitedNines = new HashSet<>();
        dfs(startX, startY, 0, visitedNines);
        return visitedNines.size();
    }

    //Hiking trails never include diagonal steps - only up, down, left, or right
    private void dfs(int x, int y, int currentHeight, Set<String> visitedNines) {
        // Out of bounds check
        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) return;

        int height = map[x][y];

        //+1 on Map
        if (height != currentHeight) return;

        if (height == 9) {
            visitedNines.add(x + "," + y);
            return;
        }

        //(up, down, left, right)
        dfs(x + 1, y, height + 1, visitedNines); // Down
        dfs(x - 1, y, height + 1, visitedNines); // Up
        dfs(x, y + 1, height + 1, visitedNines); // Right
        dfs(x, y - 1, height + 1, visitedNines); // Left
    }

    public String[][] getTrailhead() {
        return trailhead;
    }

    public void setTrailhead(String[][] trailhead) {
        this.trailhead = trailhead;
    }

    public int getSumOfScores() {
        return sumOfScores;
    }
}
