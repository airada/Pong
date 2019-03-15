/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pong;
/**
 *
 * @author Noah
 */
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

public class Ball {

    private RectF ball;
    float size;
    float xSpeed;
    float ySpeed;


    public Ball(RectF ball)
    {
        this.size = 50;
        this.xSpeed = 50;
        this.ySpeed = 50;
        this.ball = ball;
    }

    public RectF getBall() {return this.ball;}

    public boolean intersect( RectF object) {return ball.intersects(ball, object);}

    public void increaseSpeed(RectF twall, RectF lwall) {
        if (Math.abs(xSpeed) < 83) xSpeed*=1.005;
        ySpeed*=1.005;
        System.out.println("xSpeed is "+Float.toString(xSpeed));
        System.out.println("ySpeed is "+Float.toString(ySpeed));
    }

    public void bounce(Boolean intersectX, Boolean intersectY)
    {
        //WALLS: Hit left or right, flip xSpeed
        if (intersectX == true) { xSpeed *= -1; }
        //PADDLES: Hit top or bottom, flip ySpeed
        if (intersectY == true) { ySpeed *= -1; }
    }

    public void draw(PointF point, Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(point.x,point.y, size, paint);
    }

    public void update(PointF point, Boolean intersectX, Boolean intersectY) {
        if (intersectX == true || intersectY == true) { bounce(intersectX,intersectY); }
        ball.offset(xSpeed,ySpeed);
        point.set(ball.centerX(), ball.centerY());
    }

}