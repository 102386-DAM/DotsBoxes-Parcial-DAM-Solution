package cat.udl.tidic.amd.dotsboxes.models;

import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Board {

    private  static String TAG = "Board";
    private final int xMargin, yMargin,  xDistance,yDistance;
    private final List<Point> points;
    private final List<Square> squares;
    private final int M;
    private final int N;

    public Board(int xMargin, int yMargin, int xDistance, int yDistance, int m, int n) {
        this.xMargin = xMargin;
        this.yMargin = yMargin;
        this.xDistance = xDistance;
        this.yDistance = yDistance;
        this.points = new ArrayList<>();
        this.squares = new ArrayList<>();
        M = m;
        N = n;
        build();
    }

    private void build() {
        // Build points
        int x=xMargin;
        for(int r=0; r < M; r++) {
            int y = yMargin;
            for (int c = 0; c < N; c++) {
                points.add(new Point(x, y));
                y = y + yDistance;
            }
            x = x + xDistance;
        }

        //Log.d(TAG, this.points.toString());

        // Use the points to build squares

        int initColIndex = 0;
        int initRowIndex = 0;
        int initSquareIndex = 0;

        for (int i = 0; i < (M - 1) * (N - 1); i++) {
            Point P1 = points.get(initSquareIndex);
            Point P2 = points.get(initSquareIndex + 1);
            Point P3 = points.get(initSquareIndex + (N));
            Point P4 = points.get(initSquareIndex + (N + 1));
            this.squares.add(new Square(P1, P2, P3, P4));
            initSquareIndex = initSquareIndex + 1;
            initRowIndex = initRowIndex +1;

            if (initRowIndex == (N - 1)) {
                initRowIndex = 0;
                initColIndex = initColIndex + 1;
                initSquareIndex = initColIndex * (M);
            }
            //Log.d(TAG, "Square " + i +":\n"+ P1  + "-" + P2 +"\n"+ P4  + "-" + P3);
        }



    }

    public List<Point> getPoints() {
        return points;
    }

    public List<Square> getSquares() {
        return squares;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean isValidElection(Pair<Point,Point> line){
        AtomicBoolean isValid = new AtomicBoolean(true);

        Log.d(TAG,"Line=" + line.toString());

        // Check if it is a valid line
        if ( line.first.equals(line.second)){
            isValid.set(false);
            Log.d(TAG,"Not valid line -> PA == PB");
        }

        // Check if the line is between adjacent points
        else if ( !((line.first.x == line.second.x + xDistance
                || line.first.x == line.second.x-xDistance
                || line.first.y == line.second.y+yDistance
                || line.first.y == line.second.y-yDistance)
                && ( line.first.x == line.second.x
                || line.first.y == line.second.y))) {
            isValid.set(false);
            Log.d(TAG,"Not a valid line (distance > 1 point or diagonal) ");
        } else {

            // Check that line is available
            Line cl = new Line(line.first, line.second);
            squares.forEach((Square square) -> {
                square.lines.forEach((Line l) -> {
                    // Lina ocupada
                    if (l.equals(cl) && l.owner != null) {
                        isValid.set(false);
                        Log.d(TAG, "Not a valid line (ocupada) ");
                    }
                });
            });
        }

        return isValid.get();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean update(Player player){
        Line cl = new Line(player.election.first,player.election.second);
        AtomicBoolean squareIsCompleted = new AtomicBoolean();
        squareIsCompleted.set(false);
        squares.forEach( (Square square) -> {
            square.lines.forEach((Line l) -> {
                if (l.equals(cl)) {
                    l.owner = player;
                    if (square.isCompleted().get()) {
                        square.setOwner(player);
                        squareIsCompleted.set(true);
                    }
                }
                //Log.d(TAG,l.toString());
            });
        });
        return squareIsCompleted.get();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Point getPoint(Point current){
        AtomicReference<Point> point = new AtomicReference<>();
        point.set(null);
        points.forEach((Point p) -> {
            // Rule ->  needs to be a point of the board
            //Log.d("TAG", current + "=>" + p);
            if ( ((current.x <= p.x + 30 && current.x >= p.x - 30)
                    && (current.y <= p.y + 30 && current.y >= p.y - 30))
            ) {
                //Log.d("TAG", "yes");
                point.set(p);
            }
        });
        return point.get();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean isBoardCompleted(){
        AtomicBoolean isCompleted = new AtomicBoolean();
        isCompleted.set(true);
        squares.forEach( (Square square) -> {
            if (!square.isCompleted().get()) {
                isCompleted.set(false);
            }
        });
        return isCompleted.get();
    }




}
