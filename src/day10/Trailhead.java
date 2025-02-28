package day10;

public class Trailhead {
    private final int x, y;
    private int score;

    public Trailhead(int x, int y) {
        this.x = x;
        this.y = y;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Trailhead at (" + x + ", " + y + ") with score: " + score;
    }
}