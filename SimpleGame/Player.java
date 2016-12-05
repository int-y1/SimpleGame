import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Player extends Actor
{
    
    Game GAME;
    
    public Player(Game game)
    {
        // get this game
        GAME = game;
    }
    
    // implement a lives system
    private int lives = 3;
    
    public int getLives()
    {
        return lives;
    }
    
    public void loseLife()
    {
        lives--;
    }
    
    public int getDist(int x, int y)
    {
        return (int) Math.sqrt(Math.pow(x-this.getX(), 2) + Math.pow(y-this.getY(), 2));
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
