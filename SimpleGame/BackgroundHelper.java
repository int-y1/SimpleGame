import java.util.ArrayList;
import greenfoot.*;

/**
 * Maintains the background and updates info about the player's progress.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class BackgroundHelper  
{
    
    private final Game GAME;
    private final int LEVEL;
    
    // background variables
    private GreenfootImage bgImage;
    private int bgY;
    private Displayer[] bgArray;
    private int movements=0;
    
    /**
     * Constructor for objects of class BackgroundHelper
     */
    public BackgroundHelper(Game game, int level)
    {
        // initialize
        GAME=game;
        LEVEL=level;
        bgImage = new GreenfootImage(String.format("map%d.png",level));
        bgY=bgImage.getHeight();
        
        // initialize background displayers
        bgArray = new Displayer[(512/bgY)+2];
        for (int i=0; i<bgArray.length; i++) {
            bgArray[i] = new Displayer(bgImage);
            GAME.addObject(bgArray[i], 256, bgY*(i-1));
        }
    }
    
    /**
     * Move the background by a little.
     */
    public void tickBackground()
    {
        // move the background a little down
        for (int i=0; i<bgArray.length; i++) {
            bgArray[i].move(0,1);
        }
        
        // check if enough movements have occured
        movements++;
        if (movements == bgY) {
            // move the background to its original place
            for (int i=0; i<bgArray.length; i++) {
                bgArray[i].move(0,-bgY);
            }
            movements=0;
        }
    }
}
