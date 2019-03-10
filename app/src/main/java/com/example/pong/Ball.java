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
    
    public Ball(Game game)
    {
        size = 0.25;
        xSpeed = 0.1;
        ySpeed = 0.1;
        
        wall_L = game.wall_L;
        wall_R = game.wall_R;
        wall_T = game.wall_T;
        wall_B = game.wall_B;
        
        topPositionPaddleX = game.topPaddlePositionX;
        topPositionPaddleY = game.topPaddlePositionY;
        
        bottomPositionPaddleX = game.bottomPaddlePositionX;
        bottomPositionPaddleY = game.bottomPaddlePositionY;

        ballPositionX = 0.5;
        ballPositionY = 0.2;
    }
    
    public void bounce()
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
    }
    
    public boolean outOfBounds()
    {
        return ballPositionY < wall_B || ballPositionY > wall_T;
    }

}
