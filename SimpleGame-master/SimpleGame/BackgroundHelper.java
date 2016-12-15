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
    private int beginningCountdown = 100;   // length of rest at the level's start
    private int scrollTime;
    private int bgY;
    private int movements=0;
    
    private GreenfootImage bgImage;         // temporary image
    private DisplayerBottom[] bgArray;
    private DisplayerMiddle wallBegin;
    private DisplayerMiddle wallEnd;
    private BackgroundFade bTint;
    
    // level display variables
    private int score=0;
    private int lives=3;
    private DisplayerTop exitButton;
    
    /**
     * Constructor for objects of class BackgroundHelper
     */
    public BackgroundHelper(Game g, int level, int mapLength)
    {
        // initialize
        game=g;
        LEVEL=level;
        scrollTime=mapLength;
        
        // initialize starting wall
        bgImage = new GreenfootImage(String.format("map%da.png", LEVEL));
        wallBegin = new DisplayerMiddle(bgImage);
        game.addObject(wallBegin, 256, 512 - wallBegin.getImage().getHeight()/2);
        
        // initialize ending wall
        bgImage = new GreenfootImage(String.format("map%db.png", LEVEL));
        wallEnd = new DisplayerMiddle(bgImage);
        game.addObject(wallEnd, 256, wallEnd.getImage().getHeight()/2 - mapLength);
        
        // initialize background displayers
        bgImage = new GreenfootImage(String.format("map%d.png", LEVEL));
        bgY=bgImage.getHeight();
        bgArray = new DisplayerBottom[(512/bgY)+2];
        for (int i=0; i<bgArray.length; i++) {
            bgArray[i] = new DisplayerBottom(bgImage);
            game.addObject(bgArray[i], 256, bgY*(i-1));
        }
        
        // initialize the background tint
        bTint = new BackgroundFade();
        game.addObject(bTint, 256, 256);
        
        // initialize the level display
        exitButton = new DisplayerTop("Isaac/death3.png");
        game.addObject(exitButton, 18, 14);
    }
    
    /**
     * Move the background by a little.
     */
    public void tickBackground()
    {
        // do countdowns
        if (beginningCountdown > 0) {
            // still at the beginning
            beginningCountdown--;
            return;
        }
        
        if (scrollTime == 0) {
            // level is done scrolling
            return;
        }
        scrollTime--;
        
        
        // have the shift background a little
        // move walls
        wallBegin.move(0,1);
        wallEnd.move(0,1);
        
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
