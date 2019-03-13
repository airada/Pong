/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noah
 */
public class Paddle {
    
    double paddlePositionX;
    double paddlePositionY;
    
    double size;
    double inputZone;
    
    public Paddle(int yPos)
    {
        paddlePositionX = 0.5;
        switch (yPos) {
            case 0:
                paddlePositionY = 0;
                break;
            case 1:
                paddlePositionY = 1;
                break;
        }
    }
    
    public void isTouched()
    {
        //How do you get info on where user is touching?
    }
    public void updatePosition(double newPosX)
    {
        //on tap and drag, relocate paddle to dragged region
        paddlePositionX = newPosX;
    }
    public void stopCollision()
    {
        //cap movement and wall_L and wall_R
        //handled by gui?
    }
}
