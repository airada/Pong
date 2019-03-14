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
public class GameState {
    
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
    
    PaddleAI topPaddle;
    double topPaddlePositionX;
    double topPaddlePositionY;
    
    boolean hasWinner;
    int mode;
    int score; //only used in mode 2 (Endless)
        
    public GameState(int mode)
    {    
        this.mode = mode;
        
        //ball = new Ball(this);
        //bottomPaddle = new Paddle(0); //Player is always bottom paddle
        score = 0; //only displayed and tracked when mode = 2
        
        // 0 : PvP | 1 : PvAI | 2: PvAI (Endless)
       /* switch(mode)
        {
            case 0:     topPaddle = new PaddleAI(1);  //calls constructor of Paddle for Player
                        break;
                        
            case 1:     topPaddle = new PaddleAI(1, this); //calls Paddle with "AI", loses after n number of rallies
                        break;
                        
            case 2:     topPaddle = new PaddleAI(1, this); //calls Paddle with "AI",;because mode is endless, opponent cannot lose.
                        topPaddle.gameAI.setMaxSpeed(1.0);
                        break;
        }
        */
        //TODO: SET BORDERS BY SCREEN POSITION (values temporary)
        wall_L = 0.0;
        wall_R = 1.0;
        wall_B = 0.0;
        wall_T = 1.0;
                
        hasWinner = false;
    }
    
    private boolean rallyComplete()
    {
        return false; //ball.ballPositionY < wall_B || ball.ballPositionY > wall_T;
    }
    
    public int update()
    {
        if (rallyComplete())
        {
            hasWinner = true;
            return 1;
        }
        //ball.updatePosition();
        //bottomPaddle.updatePosition(0.5); //feed in touch input
        //topPaddle.updatePosition();
        return 0;
    }

}
