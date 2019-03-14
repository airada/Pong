
package com.example.pong;

import android.graphics.Point;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


/**
 *
 * @author Noah
 */
public class Paddle{

    private Rect paddle;
    int type;
    int max_speed;

    public Paddle(Rect paddle, int type)
    {
        this.paddle = paddle;
        this.type = type;
        this.max_speed = 50;
    }
    public Rect getPaddle() {return this.paddle; }

    public boolean intersect(Rect object){ return paddle.intersects(paddle, object);}

    public void movePaddle(boolean intersect)
    {
        if(intersect==true)
            max_speed*=-1;
        paddle.offset(max_speed,0);
    }

    public boolean contains(float x, float y){
        return paddle.contains((int)x,(int)y);
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(paddle, paint);
    }


    public void update(Point point, Boolean intersect) {
        if(type==0) {
            paddle.set(point.x - paddle.width() / 2, point.y - paddle.height() / 2,
                    point.x + paddle.width() / 2, point.y + paddle.height() / 2);
        } else if(type==1) {
            movePaddle(intersect);
        }

    }

}
