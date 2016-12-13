import java.util.ArrayList;
import greenfoot.*;
import java.awt.Color;

/**
 * Maintains the background and updates info about the player's progress.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class BackgroundHelper  
{
    
    private Game game;
    private final int LEVEL;
    
    // background variables
    private GreenfootImage bgImage;
    private int bgY;
    private Displayer[] bgArray;
    private int movements=0;
    private BackgroundFade bTint;
    
    // level display variables
    private int score=0;
    private int lives=3;
    private Displayer exitButton;
    
    /**
     * Constructor for objects of class BackgroundHelper
     */
    public BackgroundHelper(Game g, int level)
    {
        // initialize
        game=g;
        LEVEL=level;
        
        // initialize background displayers
        bgImage = new GreenfootImage(String.format("map%d.png",level));
        bgY=bgImage.getHeight();
        bgArray = new Displayer[(512/bgY)+2];
        for (int i=0; i<bgArray.length; i++) {
            bgArray[i] = new Displayer(bgImage);
            game.addObject(bgArray[i], 256, bgY*(i-1));
        }
        
        // initialize the background tint
        bTint = new BackgroundFade();
        game.addObject(bTint, 256, 256);
        
        // initialize the level display
        exitButton = new Displayer("Isaac/death3.png");
        game.addObject(exitButton, 18, 14);
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
    
    /**
     * Tint the screen by this transparency.
     */
    public void fadeScreen(int alpha)
    {
        bTint.changeFade(alpha);
    }
    
    public boolean hitExit() {
        return Greenfoot.mouseClicked(exitButton);
    }
}
