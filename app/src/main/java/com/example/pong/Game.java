/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noah
 */
public class Game {
 
    int score_T;
    int score_B;
    
    double wall_L;
    double wall_R;
    double wall_T;
    double wall_B;
    
    Ball ball;
    double ballPositionX;
    double ballPositionY;
    
    Paddle bottomPaddle;
    double bottomPaddlePositionX;
    double bottomPaddlePositionY;
    
    Paddle topPaddle;
    double topPaddlePositionX;
    double topPaddlePositionY;
    
    int ticks;
    boolean hasWinner;
        
    public Game()
    {
        score_T = 0;
        score_B = 0;
        
        //TODO: SET BORDERS BY SCREEN POSITION (values temporary)
        wall_L = 0.0;
        wall_R = 1.0;
        wall_B = 0.0;
        wall_T = 1.0;
        
        ball = new Ball(this);
        topPaddle = new Paddle("top");
        bottomPaddle = new Paddle("bottom");
        
        ticks = 0;
        hasWinner = false;
    }
    
    public void update()
    {
        ticks += 1;
        ball.ballPositionX += ball.xSpeed;
        ball.ballPositionY += ball.ySpeed;
    }
    
    public void updateScore()
    {
        if (ball.outOfBounds())
        {
            if (ball.ballPositionY > wall_T)
                score_B += 1;
            else if (ball.ballPositionY < wall_B)
                score_T += 1;
            
            ticks = 0;
        }
    }
}
