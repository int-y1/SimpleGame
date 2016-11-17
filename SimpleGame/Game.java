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
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends World
{
    
    private final int LEVEL;
    private Queue<Integer> instTime = new LinkedList<Integer>();
    private Queue<Integer> instID = new LinkedList<Integer>();
    private Queue<ArrayList<Integer> > instInfo = new LinkedList<ArrayList<Integer> >();
    
    
    /**
     * Constructor for objects of class Game.
     * This method is used for playing a level.
     * 
     */
    public Game(String levelPath, String replayPath) throws IOException
    {
        // screen size is 512x512 pixels
        super(512, 512, 1);
        USER_INPUT = new InputInterface(replayPath);
        // get the level info
        InputStream stream = getClass().getResourceAsStream(levelPath);
        Scanner sc = new Scanner(stream);
        LEVEL = sc.nextInt();
        int instructions = sc.nextInt();
    }
    
    // user input methods
    private final InputInterface USER_INPUT;
    
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
