package com.example.pong;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel2 extends GamePanel implements SurfaceHolder.Callback {
    private MainThread thread;

    //  Logistics
    private int mode;
    private int score;

    //  Game Objects
    private Paddle top_paddle;
    private Paddle bottom_paddle;
    private Ball ball;

    //  Game Object Positions
    private PointF top_paddlePoint;
    private PointF bottom_paddlePoint;
    private PointF ballPoint;

    //  Game Walls
    private RectF lwall;
    private RectF twall;
    private RectF rwall;
    private RectF bwall;



    //
    //      INITIALIZE OBJECTS
    //
    public GamePanel2(Context context) {
        super(context);

        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);

        //Game Objects Initialized
        top_paddle = new Paddle(new RectF((getScreenWidth()/2)-175,200,
                (getScreenWidth()/2)+175,235), 0);
        bottom_paddle = new Paddle(new RectF((getScreenWidth()/2)-175,getScreenHeight()-(getScreenHeight()/4),
                (getScreenWidth()/2)+175,getScreenHeight()+35), 0);
        ball = new Ball(new RectF((getScreenWidth()/2)-25,(getScreenHeight()/2)-25,
                (getScreenWidth()/2)+25,(getScreenHeight()/2)+25));

        //Game Object Positions
        top_paddlePoint = new PointF(getScreenWidth()/2,0+(getScreenHeight()/4));
        bottom_paddlePoint = new PointF(getScreenWidth()/2, getScreenHeight()+(getScreenHeight()/4));
        ballPoint = new PointF( getScreenWidth()/2, getScreenHeight()/2);

        //Game Walls
        lwall = new RectF(0, 0, 25, getScreenHeight());
        twall = new RectF(0, 0, getScreenWidth(), 25);
        rwall = new RectF(getScreenWidth()-25,0,getScreenWidth(),getScreenHeight());
        bwall = new RectF(0,getScreenHeight()-25,getScreenWidth(),getScreenHeight());

        setFocusable(true);
    }

    //
    //      SCREEN DIMENSIONS AND WALLS
    //
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    //
    //      GAME LOOP
    //
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(true){
            try {
                thread.setRunning(false);
                thread.join();
            } catch(Exception e){e.printStackTrace();}
            retry = false;
        }
    }

    //
    //      PADDLE INTERACTION
    //
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // get pointer index from the event object

        // get pointer ID
        int pointerIdA = event.getPointerId(0);

        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
                //Handle pointer A
                int pointerIndexA = event.findPointerIndex(pointerIdA);
                if (bottom_paddle.contains(event.getX(pointerIndexA),event.getY(pointerIndexA))) {
                    bottom_paddlePoint.set((int) event.getX(pointerIndexA), bottom_paddlePoint.y);
                }
                if (top_paddle.contains(event.getX(pointerIndexA),event.getY(pointerIndexA))) {
                    top_paddlePoint.set((int) event.getX(pointerIndexA), top_paddlePoint.y);
                }

            case MotionEvent.ACTION_POINTER_DOWN:
                //Handle pointer B
                int pointerIndexB = event.getActionIndex();
                if (bottom_paddle.contains(event.getX(pointerIndexB),event.getY(pointerIndexB))) {
                    bottom_paddlePoint.set((int) event.getX(pointerIndexB), bottom_paddlePoint.y);
                }
                if (top_paddle.contains(event.getX(pointerIndexB),event.getY(pointerIndexB))) {
                    top_paddlePoint.set((int) event.getX(pointerIndexB), top_paddlePoint.y);
                }

            case MotionEvent.ACTION_MOVE:
                //Must handle individual pointer moves manually.
                //https://stackoverflow.com/questions/9028357/android-multitouch-second-finger-action-move-ignored
                int pointerCount = event.getPointerCount();
                for (int i = 0; i < pointerCount; ++i) {
                    //Handle pointer A
                    int pointerIndex = i;
                    int pointerId = event.getPointerId(pointerIndex);

                    if (pointerId == 0 || pointerId == 1) {
                        if (bottom_paddle.contains(event.getX(pointerId), event.getY(pointerId))
                                || ((event.getY(pointerId) <= bottom_paddlePoint.y + 200) &&
                                (event.getY(pointerId) >= bottom_paddlePoint.y - 200))) {
                            bottom_paddlePoint.set((int) event.getX(pointerId), bottom_paddlePoint.y);
                        }
                        if (top_paddle.contains(event.getX(pointerId), event.getY(pointerId))
                                || ((event.getY(pointerId) <= top_paddlePoint.y + 200) &&
                                (event.getY(pointerId) >= top_paddlePoint.y - 200))) {
                            top_paddlePoint.set((int) event.getX(pointerId), top_paddlePoint.y);
                        }
                    }
                }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        return true;
    }

    //
    //      COLLISIONS AND UPDATES
    //
    public void ballCollision() {
        if (ball.intersect(lwall) || ball.intersect(rwall))
            ball.update(ballPoint, true, false);
        else if (ball.intersect(twall) || ball.intersect(bwall) ||
                ball.intersect(top_paddle.getPaddle()) || ball.intersect(bottom_paddle.getPaddle()))
            ball.update(ballPoint, false, true);
        else
            ball.update(ballPoint, false, false);
    }

    //
    //      UPDATE POSITIONS AND SCORES
    //
    public void update(){
        ballCollision();
        top_paddle.update(top_paddlePoint, false);
        bottom_paddle.update(bottom_paddlePoint, false);
    }


    //
    //      DRAW ALL GAME OBJECTS
    //
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLACK);

        //Game Objects
        top_paddle.draw(canvas);
        bottom_paddle.draw(canvas);
        ball.draw(ballPoint, canvas);

        //Game Walls
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(lwall, paint);
        canvas.drawRect(twall, paint);
        canvas.drawRect(rwall, paint);
        canvas.drawRect(bwall, paint);
    }
}
