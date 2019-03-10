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
    
    public Paddle(String topOrBottom)
    {
        paddlePositionX = 0.5;
        switch (topOrBottom) {
            case "top":
                paddlePositionY = 1;
                break;
            case "bottom":
                paddlePositionY = 0;
                break;
        }
    }
    
    public void getInputs()
    {
        //How do you get info on where user is touching?
    }
    public void movePaddle(double newPosX)
    {
        //on tap and drag, relocate paddle to dragged region
        paddlePositionX = newPosX;
    }
    public void stopCollision()
    {
        //ball already handles collision
    }
}
