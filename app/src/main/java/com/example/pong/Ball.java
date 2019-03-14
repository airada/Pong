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
import android.graphics.RectF;

/**
 *
 * @author Noah
 */

public class Ball {

    private RectF ball;
    float size;
    double xSpeed;
    double ySpeed;

    double wall_L;
    double wall_R;
    double wall_T;
    double wall_B;

    double topPositionPaddleX;
    double topPositionPaddleY;

    double bottomPositionPaddleX;
    double bottomPositionPaddleY;


    public Ball(RectF ball) {
        this.size = 50;
        //xSpeed = 0.1;
        //ySpeed = 0.1;
        this.ball = ball;

        //wall_L = 0.0;
        //wall_R = gamePanel.getScreenWidth();
        //wall_T = 0.0;
        //wall_B = gamePanel.getScreenHeight();

        //topPositionPaddleX = gamePanel.topPaddlePositionX;
        //topPositionPaddleY = gamePanel.topPaddlePositionY;

        //bottomPositionPaddleX = gamePanel.bottomPaddlePositionX;
        //bottomPositionPaddleY = gamePanel.bottomPaddlePositionY;

        //ballPositionX = 0.5;
        //ballPositionY = 0.2;
    }

    public void bounce() {
        /*
        //WALLS: Hit left or right, flip xSpeed
        if (ballPositionX == wall_L || ballPositionX == wall_R)
        {
            xSpeed = xSpeed * -1;
        }
        //PADDLES: Hit top or bottom, flip ySpeed
        if (ballPositionY >= bottomPositionPaddleY - size && ballPositionY <= bottomPositionPaddleY + size ||
                ballPositionY >= topPositionPaddleY - size && ballPositionY <= topPositionPaddleY + size)
        {
            ySpeed = ySpeed * -1;
        }
        */
    }

    public boolean outOfBounds() {
        return false; //ballPositionY < wall_B || ballPositionY > wall_T;
    }

    public void draw(Point point, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(point.x, point.y, size, paint);
    }


    public void update(Point point) {
        ball.set(point.x - ball.width() / 2, point.y - ball.height() / 2,
                point.x + ball.width() / 2, point.y + ball.height() / 2);
    }

}