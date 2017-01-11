import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class DisplayerMove here.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class DisplayerMove extends DisplayerMiddle
{
    
    private final Game GAME;
    
    /**
     * Initialize the DisplayerMove's image.
     * The given String is the path to an image.
     */
    public DisplayerMove(Game game, ArrayList<String> al) {
        // initialize displayer
        super(al.get(0));
        GAME = game;
        
        // add to the game
        game.addObject(this, Integer.parseInt(al.get(1)), -getImage().getHeight()/2);
    }
    
    /**
     * Act - move the image down 1 pixel, and delete if necessary.
     */
    public void act()
    {
        // move image
        move(0, 1);
        
        // check if out of range
        if (getY() >= 512 + getImage().getHeight()/2) {
            // remove
            GAME.removeObject(this);
        }
    }
}
