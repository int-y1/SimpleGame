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
    private Player player;
    
    /**
     * Constructor for objects of class Game.
     * This method is used for playing a level.
     * 
     */
    public Game(int levelNumber, String replayPath) throws IOException
    {
        // screen size is 512x512 pixels
        super(512, 512, 1, false);
        
        // put level number
        LEVEL = levelNumber;
        
        // add background
        BH = new BackgroundHelper(this, LEVEL);
        
        // get the user's input interface
        USER_INPUT = new InputInterface(replayPath);
        
        // put player into the game
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
        player.loseLife();
        
        // create an explosion which destroys bullets (later)
        this.addObject(new PlayerExplosion(), player.getX(), player.getY());
        
        // check if game over
        if (player.getLives()==0) {
            System.out.println("You fail!");
            Greenfoot.stop();
        }
    }
    
    
    private int cooldown = 100;
    public void act()
    {
        // update the private classes
        USER_INPUT.getNewStrokes();
        LR.tick();
        
        // the map should be stopped at the beginning
        if (cooldown > 0) {
            cooldown--;
        }
        else {
            BH.tickBackground();
        }
    }
}
