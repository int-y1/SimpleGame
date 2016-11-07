import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.io.IOException;

/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends World
{
    
    /**
     * Constructor for objects of class Game.
     * This method is used for playing a.
     * 
     */
    public Game(String levelPath) throws IOException
    {    
        // Create a new world with 512x512 cells with a cell size of 1x1 pixels.
        super(512, 512, 1);
        userInput = new InputInterface();
    }
    
    /**
     * Constructor for objects of class Game.
     * This method is used for replaying a file.
     */
    public Game(String levelPath, String replayPath) throws IOException
    {    
        // Create a new world with 512x512 cells with a cell size of 1x1 pixels.
        super(512, 512, 1);
        userInput = new InputInterface(replayPath); 
    }
    
    // user input methods
    private final InputInterface userInput;
    
    public boolean keyRight()
    {
        return userInput.right;
    }
    
    public boolean keyUp()
    {
        return userInput.up;
    }
    
    public boolean keyLeft()
    {
        return userInput.left;
    }
    
    public boolean keyDown()
    {
        return userInput.down;
    }
    
    // user variables
    Player player;
    
    public int getPlayerDist(int x, int y)
    {
        // simple distance formula
        return (int) Math.sqrt(Math.pow(player.getX()-x, 2) + Math.pow(player.getY()-y, 2));
    }
    
}
