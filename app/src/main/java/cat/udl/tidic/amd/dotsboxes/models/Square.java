package cat.udl.tidic.amd.dotsboxes.models;

import android.graphics.Point;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Square {

    // P1 - P2
    // |    |
    // P4 - P3
    private Point P1;
    private Point P2;
    private Point P3;
    private Point P4;

    public List<Line> lines;

    // Player who belongs that square
    private Player owner;

    public Square(Point p1, Point p2, Point p3, Point p4) {
        P1 = p1;
        P2 = p2;
        P3 = p3;
        P4 = p4;

        lines = new ArrayList<>();
        lines.add(new Line(P1,P2));
        lines.add(new Line(P2,P4));
        lines.add(new Line(P4,P3));
        lines.add(new Line(P3,P1));
    }

    public Point getP2() {
        return P2;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public AtomicBoolean isCompleted(){

        AtomicBoolean isCompleted = new AtomicBoolean(true);

        lines.forEach((Line line) -> {
            if (line.owner == null){
                isCompleted.set(false);
            }
        });

        return isCompleted;

    }

}
