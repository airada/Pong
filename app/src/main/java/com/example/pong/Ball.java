/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noah
 */
public class Ball {

    double size;
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
    
    double ballPositionX;
    double ballPositionY;
    
    boolean isEndless;
    
    public Ball(GameState gameState)
    {
        size = 0.25;
        xSpeed = 0.1;
        ySpeed = 0.1;
        
        wall_L = gameState.wall_L;
        wall_R = gameState.wall_R;
        wall_T = gameState.wall_T;
        wall_B = gameState.wall_B;
        
        topPositionPaddleX = gameState.topPaddlePositionX;
        topPositionPaddleY = gameState.topPaddlePositionY;
        
        bottomPositionPaddleX = gameState.bottomPaddlePositionX;
        bottomPositionPaddleY = gameState.bottomPaddlePositionY;

        ballPositionX = 0.5;
        ballPositionY = 0.2;
        
        if (gameState.mode == 2)
        {
            isEndless = true;
        }
        else
        {
            isEndless = false;
        }
    }
    
    public void bounce(GameState gameState)
    {
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
        
        if (isEndless)
        {
            if (ballPositionY >= bottomPositionPaddleY - size && ballPositionY <= bottomPositionPaddleY + size) // bounce on bottom
                gameState.score += 1;
        }
    }
    
    public void updatePosition()
    {
        ballPositionX += xSpeed;
        ballPositionY += ySpeed;
    }
    
    public boolean outOfBounds()
    {
        return ballPositionY < wall_B || ballPositionY > wall_T;
    }

}
