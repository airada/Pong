package com.example.pong;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;


public class GamePanel3 extends SurfaceView implements SurfaceHolder.Callback {
    private Thread3 thread;
    private Context context;
    //  Logistics
    public static int score;
    private boolean gameover;
    private long gameOverTime;
    private Rect r = new Rect();

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
    public GamePanel3(Context context) {
        super(context);

        getHolder().addCallback(this);
        thread = new Thread3(getHolder(), this);
        this.context = context;

        score = 0;
        gameover = false;

        //Game Objects Initialized
        top_paddle = new Paddle(new RectF((getScreenWidth()/2)-175,200,
                (getScreenWidth()/2)+175,275), 3);
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

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("sub1","chemistry");
        context.startActivity(intent);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new Thread3(getHolder(), this);

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
            return;
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
                if(gameover && System.currentTimeMillis() - gameOverTime >= 2000)
                    gameover = false;

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
    public void ballCollision() {

        if (ball.intersect(lwall) || ball.intersect(rwall))
            ball.update(ballPoint, true, false);
        else if (ball.intersect(twall) || ball.intersect(bwall)) {
            System.out.println("Rally score is: " + Integer.toString(score));
            destroy();
            ball.update(ballPoint, false, false);
            gameover = true;
            gameOverTime = System.currentTimeMillis();
            startActivity(context);
        }
        else if (ball.intersect(top_paddle.getPaddle()) || ball.intersect(bottom_paddle.getPaddle())){
            if (ball.intersect(bottom_paddle.getPaddle())){score++;}
            if (ball.intersect(top_paddle.getPaddle())){ball.increaseSpeed();}
            ball.update(ballPoint, false, true);
        }
        else
            ball.update(ballPoint, false, false);
    }


    //
    //      UPDATE POSITIONS AND SCORES
    //
    public void update(){
        ballCollision();
        top_paddle.update(ball.getBall(), top_paddlePoint, false);
        bottom_paddle.update(bottom_paddlePoint, false);
    }


    //
    //      DRAW ALL GAME OBJECTS
    //
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
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

        if (gameover) {
            Paint paint1 = new Paint();
            paint1.setTextSize(100);
            draw1(canvas, paint1, "GAME OVER");

        }
    }
    private void draw1(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.rgb(255, 255, 255));
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }
    public void destroy() {
        ball.setEmpty();
        top_paddle.setEmpty();
        bottom_paddle.setEmpty();
        lwall.setEmpty();
        twall.setEmpty();
        rwall.setEmpty();
        bwall.setEmpty();
    }

}



