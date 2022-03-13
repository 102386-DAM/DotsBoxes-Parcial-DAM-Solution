package cat.udl.tidic.amd.dotsboxes.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import cat.udl.tidic.amd.dotsboxes.models.Board;
import cat.udl.tidic.amd.dotsboxes.models.Game;
import cat.udl.tidic.amd.dotsboxes.models.Line;
import cat.udl.tidic.amd.dotsboxes.models.Player;
import cat.udl.tidic.amd.dotsboxes.models.Square;
import cat.udl.tidic.amd.dotsboxes.viewmodels.GameViewModel;

public class GameView extends View {

    static String TAG = "GameView";
    static int M=4;
    static int N=4;

    GameViewModel gameViewModel;

    Game game;
    Board board;

   private int xMargin, yMargin, yDistance, xDistance;
   private List<Point> points;

   // Ho haurem de guardar en una ronda d'un jugador
    Point pi = null;
    Point pf = null;

    Pair currentPair = null;

    private List<Pair<Point,Point>> pointsPlayer1;
    private List<Pair<Point,Point>> pointsPlayer2;

    private List<Pair> squares;

    private Player player = null;
    private boolean endTurn=false;

    protected Paint paint;


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        points = new ArrayList<>();

        //init board
        yDistance=getMeasuredHeight()/M;
        xDistance=getMeasuredWidth()/N;
        xMargin = xDistance/2;
        yMargin = yDistance/2;
        board = new Board(xMargin,yMargin,xDistance,yDistance,M,N);

        //init game
        game = new Game(board);

        // This goes to logic
        pointsPlayer1 = new ArrayList<>();
        pointsPlayer2 = new ArrayList<>();

        paint = new Paint();

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //yDistance=getMeasuredHeight()/M;
        //xDistance=getMeasuredWidth()/N;
        //xMargin = xDistance/2;
        //yMargin = yDistance/2;

        //Log.d(TAG, "yDistance = " + yDistance);
        //Log.d(TAG, "xDistance = " + xDistance);

        //Log.d(TAG,"Lines P1="+pointsPlayer1.toString());
        //Log.d(TAG,"Lines P2="+pointsPlayer2.toString());

        if (game.currentPlayer() == null){
            //init board
            yDistance=getMeasuredHeight()/M;
            xDistance=getMeasuredWidth()/N;
            xMargin = xDistance/2;
            yMargin = yDistance/2;
            board = new Board(xMargin,yMargin,xDistance,yDistance,M,N);

            //init game
            //this.gameViewModel.setCurrentGame(game);
            game = new Game(board);
            this.gameViewModel.setCurrentGame(game);
        }else{
            this.gameViewModel.setCurrentGame(game);
        }

        board.getSquares().forEach((Square square)->{
            square.lines.forEach((Line l) -> {

                Point p1 = (Point) l.getPA();
                Point p2 = (Point) l.getPB();

                if (l.getOwner() == game.playerRed){
                    paint.setColor(Color.RED);
                    paint.setStrokeWidth(30);
                    canvas.drawLine(p1.x,p1.y,p2.x,p2.y,paint);
                } else if (l.getOwner() == game.playerBlue){
                    paint.setColor(Color.BLUE);
                    paint.setStrokeWidth(30);
                    canvas.drawLine(p1.x,p1.y,p2.x,p2.y,paint);
                } else {
                    paint.setColor(Color.BLACK);
                }

                paint.setColor(Color.BLACK);
                canvas.drawCircle(p1.x,p1.y,30, paint );
                canvas.drawCircle(p2.x,p2.y,30, paint );
            });
            if (square.isCompleted().get()){

                Log.d(TAG,square.getOwner().alias);

                paint.setTextSize(300);
                paint.setColor(square.getOwner().getColor());

                canvas.drawText(square.getOwner().alias,
                        square.getP2().x + xDistance/7,
                        square.getP2().y - yDistance/5, paint);
            }
        });


        if (game.currentPlayer() == null){
            //@Random choiche
            game.playerBlue.setPlaying(true);
        }else{
            if (endTurn){
                game.nextPlayer();
            }
        }

        Log.d(TAG,"Player playing="+game.currentPlayer().getName());
    }

    @Override
    public boolean performClick() {
        // Calls the super implementation, which generates an AccessibilityEvent
        // and calls the onClick() listener on the view, if any
        super.performClick();
        return true;
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean onTouchEvent(MotionEvent event) {
        //Log.i(TAG,"f==>"+event);
        //Log.i(TAG,"points=>"+points);

        int x = (int) event.getX();
        int y = (int) event.getY();
        Point current = new Point();
        current.set(x, y);

        Point p = board.getPoint(current);
        Log.d(TAG,"Currnet point:"+p);

        // The point is valid p is different of null
        if (p != null) {
            if (game.currentPlayer().election == null) {
                // First click
                Log.d(TAG,"First Click");
                game.currentPlayer().election = new Pair<>(p, new Point());
            } else {
                //Second click
                Log.d(TAG,"Second Click");
                game.currentPlayer().election.second.set(p.x, p.y);
                Log.d(TAG, "isValidLine="+board.isValidElection(game.currentPlayer().election) );
                if (board.isValidElection(game.currentPlayer().election)) {
                    // if no square -> update->False and endTurn=True
                    // if square -> update -> True and endTurn=False
                    endTurn=!board.update(game.currentPlayer());

                    if (!endTurn){
                        game.currentPlayer().setSquares(game.currentPlayer().getSquares() + 1);
                        Log.d(TAG,game.currentPlayer().getName() +"->" +
                                game.currentPlayer().getSquares() + " squares");
                    }
                }else{
                    endTurn=false;
                }

                invalidate();
                // Reset election after second click
                game.currentPlayer().election = null;
            }
        }
        performClick();
        return false;
    }

    public void setGameViewModel(GameViewModel gameViewModel){
        this.gameViewModel = gameViewModel;
    }
}