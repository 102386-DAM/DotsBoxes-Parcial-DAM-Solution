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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import cat.udl.tidic.amd.dotsboxes.models.Board;
import cat.udl.tidic.amd.dotsboxes.models.Game;
import cat.udl.tidic.amd.dotsboxes.models.Line;
import cat.udl.tidic.amd.dotsboxes.models.MoveState;
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

    private int yDistance;
    private int xDistance;
   private List<Point> points;
   private boolean endTurn=false;
   protected Paint paint;


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        points = new ArrayList<>();

        //init board
        board = new Board(M,N);

        //init game
        game = new Game(board);

        //init the graphics
        paint = new Paint();

        game = new Game(board);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (game.currentPlayer() == null){
            yDistance=getMeasuredHeight()/M;
            xDistance=getMeasuredWidth()/N;
            int xMargin = xDistance / 2;
            int yMargin = yDistance / 2;
            this.game.board.setBoardDimensions(xMargin, yMargin, xDistance,yDistance);
            this.game.board.build();
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
        int x = (int) event.getX();
        int y = (int) event.getY();
        Point current = new Point();
        current.set(x, y);

        Point p = board.getPoint(current);

        // The point is valid p is different of null
        if (p != null) {
            if (game.currentPlayer().election == null) {
                // First click
                game.currentPlayer().election = new Pair<>(p, new Point());
            } else {
                //Second click
                game.currentPlayer().election.second.set(p.x, p.y);
                MoveState moveState = board.isValidElection(game.currentPlayer().election);
                if (moveState.isValid) {
                    // if no square -> update->False and endTurn=True
                    // if square -> update -> True and endTurn=False
                    endTurn=!board.update(game.currentPlayer());

                    if (!endTurn){
                        game.currentPlayer().setSquares(game.currentPlayer().getSquares() + 1);
                    }
                }else{
                    Toast.makeText(this.getContext(),moveState.message, Toast.LENGTH_LONG).show();

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