
package com.example.pong;

import android.graphics.Point;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;


/**
 *
 * @author Noah
 */
public class Paddle{

    private RectF paddle;
    int type;
    int max_speed;

    public Paddle(RectF paddle, int type)
    {
        this.paddle = paddle;
        this.type = type;
        this.max_speed = 50;
    }
    public RectF getPaddle() {return this.paddle; }

    public boolean intersect(RectF object){ return paddle.intersects(paddle, object);}

    public void movePaddle(boolean intersect)
    {
        if(intersect==true)
            max_speed*=-1;
    }

    public void AI(RectF ball, PointF point, Boolean intersect)
    {
        paddle.set(ball.centerX() - paddle.width()/2, point.y - paddle.height() / 2,
                ball.centerX() + paddle.width() / 2, point.y + paddle.height() / 2);
    }

    public boolean contains(float x, float y){
        return paddle.contains((int)x,(int)y);
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(paddle, paint);
    }

    public void update(PointF point, Boolean intersect) {
        if (type == 0) {
            paddle.set(point.x - paddle.width() / 2, point.y - paddle.height() / 2,
                    point.x + paddle.width() / 2, point.y + paddle.height() / 2);
        } else if (type == 1) {
            movePaddle(intersect);
            paddle.offset(max_speed, 0);
        }
    }

    public void update(RectF ball, PointF point, Boolean intersect) {
        movePaddle(intersect);
        AI(ball, point, intersect);

    }

}
