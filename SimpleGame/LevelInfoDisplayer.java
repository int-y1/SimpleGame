import java.util.ArrayList;
import greenfoot.*;
import java.awt.Color;
import java.io.InputStream;
import java.util.Scanner;

/**
 * The LevelInfoDisplayer class displays info about the player's progress.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class LevelInfoDisplayer  
{
    
    private Game game;
    
    // level display variables
    private final int HEART_COUNT;
    private Heart[] hearts;
    private DisplayerTop exitButton;
    
    /**
     * Constructor for objects of class LevelInfoDisplayer.
     */
    public LevelInfoDisplayer(Game g, int lives, int maxLives)
    {
        // initialize variables
        game = g;
        
        // initialize lives info
        HEART_COUNT = (maxLives+1)/2;
        hearts = new Heart[HEART_COUNT];
        for (int i=0; i<HEART_COUNT; i++) {
            hearts[i] = new Heart();
            game.addObject(hearts[i], 16 + i*32, 496);
        }
        
        // initialize first info
        setLives(lives);
        
        // initialize the level display
        // exit button
        exitButton = new DisplayerTop("Isaac/death3.png");
        game.addObject(exitButton, 492, 496);
    }
    
    /**
     * Use the lives variable to update the images of the hearts.
     */
    public void setLives(int lives) {
        // set heart images
        for (int i=0; i<HEART_COUNT; i++) {
            if (i*2+2 <= lives)      hearts[i].setHeartFullness(2);
            else if (i*2+1 == lives) hearts[i].setHeartFullness(1);
            else                     hearts[i].setHeartFullness(0);
        }
    }
    
    /**
     * Return true if the player pressed the exit button, otherwise false.
     */
    public boolean hitExit() {
        return Greenfoot.mouseClicked(exitButton);
    }
}
