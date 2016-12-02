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
    
    private final int LEVEL;
    
    private final InputInterface USER_INPUT;
    private final LevelReader LR;
    
    /**
     * Constructor for objects of class Game.
     * This method is used for playing a level.
     * 
     */
    public Game(String levelPath, String replayPath) throws IOException
    {
        // screen size is 512x512 pixels
        super(512, 512, 1);
        
        // get the user's input interface
        USER_INPUT = new InputInterface(replayPath);
        
        // get the level file into a Scanner
        InputStream stream = getClass().getResourceAsStream(levelPath);
        Scanner sc = new Scanner(stream);
        
        // get the first line
        String temp = sc.nextLine();                    // first line from Scanner
        String[] args = temp.trim().split("\\s+");      // separate the String by whitespace
        
        // parse the first line from args
        LEVEL = Integer.parseInt(args[0]);
        
        // send Scanner to another class for processing
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
    
    // user variables
    private Player player;
    
    public int getPlayerDist(int x, int y)
    {
        // simple distance formula
        return (int) Math.sqrt(Math.pow(player.getX()-x, 2) + Math.pow(player.getY()-y, 2));
    }
    
}
