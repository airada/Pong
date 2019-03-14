
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
    float max_speed;

    double inputZone;

    public Paddle(Rect paddle, int type)
    {
        this.paddle = paddle;
        this.type = type;
    }

    public void movePaddle()
    {

    }

    public boolean contains(float x, float y){
        return paddle.contains((int)x,(int)y);
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(paddle, paint);
    }


    public void update(Point point) {
        if(type==0) {
            paddle.set(point.x - paddle.width() / 2, point.y - paddle.height() / 2,
                    point.x + paddle.width() / 2, point.y + paddle.height() / 2);
        } else if(type==1) {
            movePaddle();
        }

    }

}
