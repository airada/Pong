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
import android.graphics.Point;
import android.graphics.Rect;

/**
 *
 * @author Noah
 */

public class Ball {

    private Rect ball;
    float size;
    int xSpeed;
    int ySpeed;

    public Ball(Rect ball)
    {
        this.size = 50;
        this.xSpeed = 50;
        this.ySpeed = 50;
        this.ball = ball;
    }

    public boolean intersect( Rect object ) {return ball.intersects(ball, object);}


    public void bounce(Boolean intersectX, Boolean intersectY)
    {
        //WALLS: Hit left or right, flip xSpeed
        if (intersectX == true) { xSpeed *= -1; }
        //PADDLES: Hit top or bottom, flip ySpeed
        else if (intersectY == true) { ySpeed *= -1; }
    }

    public void draw(Point point, Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(point.x,point.y, size, paint);
    }


    public void update(Point point, Boolean intersectX, Boolean intersectY) {
        if (intersectX == true || intersectY == true) { bounce(intersectX,intersectY); }
        ball.offset(xSpeed,ySpeed);
        point.set(ball.centerX(),ball.centerY());
    }

}