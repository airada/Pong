/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noah
 */
public class PaddleAI extends Paddle
{
    AI gameAI;
    
    public PaddleAI(String topOrBottom, Game game)
    {
        super(topOrBottom);
        gameAI = new AI(game);
    }
    
    public void updatePosition()
    {
        
    }
}
