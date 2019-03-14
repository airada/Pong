/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pong;

import android.graphics.Rect;

/**
 *
 * @author Noah
 */

public class PaddleAI extends Paddle
{
    public AI gameAI;

    public PaddleAI(Rect paddle, int type)
    {
        super(paddle, type);
    }

    /*
    public PaddleAI(int topOrBottom, GameState gameState)
    {
        super(topOrBottom);
        gameAI = new AI(gameState);
    }
    public void updatePosition()
    {
        //Follow ball.ballPositionX
        
        //Note that paddle can only move distance gameAI.maxSpeed per update
        if (Math.abs(paddlePositionX - gameAI.getBallPositionX()) < gameAI.getMaxSpeed())
        {
            paddlePositionX = gameAI.getBallPositionX();
        }
        //ball is too far and paddle cannot reach ball, move towards ball
        else
        {
            if (paddlePositionX > gameAI.getBallPositionX())
                paddlePositionX -= gameAI.getMaxSpeed(); //move left
            else
                paddlePositionX += gameAI.getMaxSpeed(); //move right
        }
    }
    */
}
