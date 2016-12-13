import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

import java.util.Scanner;
import java.io.InputStream;
import java.io.IOException;

/**
 * Write a description of class Game here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Game extends World
{
    
    private final String LEVEL_PATH= "/levels/level%d.txt";
    
    private final int LEVEL;
    
    private final InputInterface USER_INPUT;
    private final LevelReader LR;
    private final BackgroundHelper BH;
    private int beginningCooldown = 100;
    
    // player variables
    private Player player;
    private int lives = 1;
    private final int DEATH_DURATION = 200;     // duration of death animation
    private int deathAnimation=DEATH_DURATION;
    
    /**
     * Constructor for objects of class Game.
     * This method is used for playing a level.
     * 
     */
    public Game(int levelNumber, String replayPath) throws IOException
    {
        // screen size is 512x512 pixels
        super(512, 512, 1, false);
        LEVEL = levelNumber;
        
        // add background
        BH = new BackgroundHelper(this, LEVEL);
        
        // initialize player-related variables
        USER_INPUT = new InputInterface(replayPath);
        player = new Player(this);
        this.addObject(player, 256, 384);
        
        // get the level file into a Scanner
        InputStream stream = getClass().getResourceAsStream(String.format(LEVEL_PATH, levelNumber));
        Scanner sc = new Scanner(stream);
        // parse the first line
        String temp = sc.nextLine();                    // first line from Scanner
        // send Scanner to another class for furtherprocessing
        LR = new LevelReader(this, sc);
        
    }
    
    
    // user input methods
    
    public boolean keyRight()
    {
        return USER_INPUT.right;
    }
    
    public boolean keyUp()
    {
        return USER_INPUT.up;
    }
    
    public boolean keyLeft()
    {
        return USER_INPUT.left;
    }
    
    public boolean keyDown()
    {
        return USER_INPUT.down;
    }
    
    public boolean keyS()
    {
        return USER_INPUT.s;
    }
    
    public boolean keyD()
    {
        return USER_INPUT.d;
    }
    
    
    // player methods
    
    public int getPlayerDist(int x, int y)
    {
        // simple distance formula
        return player.getDist(x, y);
    }
    
    public void playerLoseLife()
    {
        // make the player lose a life
        lives--;
        
        // check if game over
        if (lives==0) {
            player.setImage("Isaac/death1.png");
        }
        else {
            // create an explosion which destroys bullets
            this.addObject(new PlayerExplosion(), player.getX(), player.getY());
        }
    }
    
    public boolean playerDead()
    {
        return lives==0;
    }
    
    public void act()
    {
        if (lives == 0) {
            // player is dead
            // check if animation is done
            if (deathAnimation == 0) {
                Greenfoot.setWorld(new Title());
            }
            deathAnimation--;
            
            // do player image
            if (deathAnimation == (DEATH_DURATION*5)/6) {
                player.setImage("Isaac/death2.png");
            }
            if (deathAnimation == (DEATH_DURATION*4)/6) {
                player.setImage("Isaac/death3.png");
            }
            
            // do background fading
            BH.fadeScreen(((DEATH_DURATION-deathAnimation)*255) / DEATH_DURATION);
        }
        else {
            // player is not dead
            // check exit button
            if (BH.hitExit()) {
                // kill the player
                lives=0;
                player.setImage("Isaac/death1.png");
                return;
            }
            
            // update the private classes
            USER_INPUT.getNewStrokes();
            LR.tick();
            
            // the map should be stopped at the beginning
            if (beginningCooldown > 0) {
                beginningCooldown--;
            }
            else {
                BH.tickBackground();
            }
        }
    }
}
