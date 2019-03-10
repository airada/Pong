package com.example.pong;

import android.support.constraint.solver.widgets.Rectangle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class Paddle extends View{
    private int size;

    private int input; //Single or Multiplayer mode
    private int move_percent;

    private int pos_x; //Paddle positions
    private int pos_y;

    private Rect paddle;
    private Paint paint;

    public Paddle()
    {
        this.paddle = new Rect(50, 50, 100, 200);
    }

    public Paddle(int size)
    {
        this.paddle = new Rect(100,100, size, size);
    }

    void setInput(int input)
    {
        this.input = input;
    }

}
