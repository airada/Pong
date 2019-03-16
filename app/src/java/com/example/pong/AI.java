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
public class AI {
    
    private double maxSpeed;
    private double ballPositionX;
    
    public AI(GameState gameState)
    {
        maxSpeed = 0.0000001; //temp value
        //ballPositionX = gameState.ball.ballPositionX;
    }
    
    public void setMaxSpeed(double newSpeed)
    {
        maxSpeed = newSpeed;
    }
    
    public void setBallPositionX(double newBallPositionX)
    {
        ballPositionX = newBallPositionX;
    }
    
    public double getMaxSpeed()
    {
        return maxSpeed;
    }
    public double getBallPositionX()
    {
        return ballPositionX;
    }
}
