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


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
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
    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);

        //Game Objects Initialized
        top_paddle = new Paddle(new RectF((getScreenWidth()/2)-175,200,
                (getScreenWidth()/2)+175,275), 1);
        bottom_paddle = new Paddle(new RectF((getScreenWidth()/2)-175,getScreenHeight()-200,
                (getScreenWidth()/2)+175,getScreenHeight()-125), 0);
        ball = new Ball(new RectF((getScreenWidth()/2)-25,(getScreenHeight()/2)-25,
                (getScreenWidth()/2)+25,(getScreenHeight()/2)+25));

        //Game Object Positions
        top_paddlePoint = new PointF(getScreenWidth()/2,200);
        bottom_paddlePoint = new PointF(getScreenWidth()/2, getScreenHeight()-200);
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
        switch (event.getAction()) {
            case MotionEvent.ACTION_BUTTON_PRESS:
            case MotionEvent.ACTION_DOWN:
                if (bottom_paddle.contains(event.getX(),event.getY())) {
                    bottom_paddlePoint.set((int) event.getX(), bottom_paddlePoint.y);
                }
            case MotionEvent.ACTION_MOVE:
                if(bottom_paddle.contains(event.getX(),event.getY())
                        || ((event.getY() <=  bottom_paddlePoint.y+200) &&
                        (event.getY() >=  bottom_paddlePoint.y-200))) {
                    bottom_paddlePoint.set((int) event.getX(), bottom_paddlePoint.y);
                }
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

    //
    //      COLLISIONS AND UPDATES
    //
    public void paddleCollision(){
        if (top_paddle.intersect(rwall) || top_paddle.intersect(lwall))
            top_paddle.update(top_paddlePoint, true);
        else
            top_paddle.update(top_paddlePoint, false);
    }

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
        paddleCollision();
        ballCollision();
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
