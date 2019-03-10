/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noah
 */
public class AI {
    
    public double maxSpeed;
    public double ballPositionX;
    
    public AI(Game game)
    {
        maxSpeed = 0.2; //temp value 
        ballPositionX = game.ball.ballPositionX;
    }
    
    public void setMaxSpeed(double speed)
    {
        maxSpeed = speed;
    }
    
    public double getBallPosition()
    {
        return ballPositionX;
    }
}
