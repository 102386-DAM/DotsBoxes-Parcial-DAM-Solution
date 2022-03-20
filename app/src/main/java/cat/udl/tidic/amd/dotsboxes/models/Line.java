package cat.udl.tidic.amd.dotsboxes.models;

import android.graphics.Point;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.concurrent.atomic.AtomicBoolean;

public class Line {

    Player owner;
    Point PA;
    Point PB;

    private static String TAG = "Line";

    public Line(Point PA, Point PB) {
        this.PA = PA;
        this.PB = PB;
    }

    public Player getOwner() {
        return owner;
    }

    public Point getPA() {
        return PA;
    }

    public Point getPB() {
        return PB;
    }

    @Override
    @NonNull
    public String toString(){
        return "[" + PA + "," + PB + "->" +owner + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Line)) {
            return false;
        }

        Line c = (Line) o;


        return (c.getPA().equals( this.PA) || c.getPA().equals( this.PB)) &&
                (c.getPB().equals( this.PA) || c.getPB().equals( this.PB));
    }


}
