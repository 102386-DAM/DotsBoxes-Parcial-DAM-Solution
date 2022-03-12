package cat.udl.tidic.amd.dotsboxes.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {

    static String TAG = "GameView";
    static int M=4;
    static int N=4;

    protected Paint paint;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int yDistance=getMeasuredHeight()/M;
        int xDistance=getMeasuredWidth()/N;
        int xMargin = xDistance/2;
        int yMargin = yDistance/2;

        Log.d(TAG, "yDistance = " + yDistance);
        Log.d(TAG, "xDistance = " + xDistance);
        int x=xMargin;
        for(int r=0; r < M; r++){
            int y=yMargin;
            for(int c=0; c < N; c++) {
                canvas.drawCircle(x,y,10, paint );
                y=y+yDistance;
            }
            x=x+xDistance;
        }

    }
}