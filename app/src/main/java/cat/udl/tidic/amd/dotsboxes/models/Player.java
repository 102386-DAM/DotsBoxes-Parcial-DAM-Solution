package cat.udl.tidic.amd.dotsboxes.models;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Pair;

public class Player {

    private String name;
    public String alias;
    private int color;
    private int squares;
    private boolean isPlaying;

    public Pair<Point,Point> election;


    public Player(String name, int color) {
        this.name = name;
        this.color = color;
        this.squares = 0;
        this.alias = name.toUpperCase().substring(0,1);
    }

    public int getSquares() {
        return squares;
    }

    public void setSquares(int squares) {
        this.squares = squares;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

}
