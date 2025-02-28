package day10;

import java.util.Arrays;

public class Map {

    private final String[][] map;
    private String [][] trailhead;
    //A trailhead is any position that starts one or more hiking trails - here, these positions will always have height 0.
    // Assembling more fragments of pages, you establish that a trailhead's score is the number of 9-height positions reachable from that trailhead via a hiking trail.
    // In the above example, the single trailhead in the top left corner has a score of 1 because it can reach a single 9 (the one in the bottom left).

    public Map(String[][] map) {
        this.map = map;
        this.trailhead = this.findStart(map);

    }

    public String[][] findStart(String[][] map){
        for (String[] row : map) {
            //suche 0
            //suche 1 daneben
        }
        return map;
    }

    public String[][] getTrailhead() {
        return trailhead;
    }

    public void setTrailhead(String[][] trailhead) {
        this.trailhead = trailhead;
    }

    //Hiking trails never include diagonal steps - only up, down, left, or right
}
