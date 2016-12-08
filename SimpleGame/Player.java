import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Player extends Actor
{
    
    private final int SPEED_NORMAL = 6;
    private final int SPEED_SLOW = 2;
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
    
    
    // methods for coordinate geometry
    
    public int getDist(int x, int y)
    {
        return (int) Math.sqrt(Math.pow(x-this.getX(), 2) + Math.pow(y-this.getY(), 2));
    }
    
    public void displacePlayer(int dx, int dy)
    {
        int newX = this.getX()+dx;
        int newY = this.getY()+dy;
        
        if (newX<32)  newX=32;
        if (newX>480) newX=480;
        if (newY<32)  newY=32;
        if (newY>480) newY=480;
        
        this.setLocation(newX, newY);
    }
    
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // read movements
        int moveSpeed;
        if (GAME.keyS()) moveSpeed = SPEED_SLOW;
        else moveSpeed = SPEED_NORMAL;
        
        // read the left/right keys
        if (GAME.keyLeft()) {
            if (!GAME.keyRight()) {
                displacePlayer(-moveSpeed, 0);
            }
        }
        else if (GAME.keyRight()) {
            displacePlayer(moveSpeed, 0);
        }
        
        // read the up/down keys
        if (GAME.keyUp()) {
            if (!GAME.keyDown()) {
                displacePlayer(0, -moveSpeed);
            }
        }
        else if (GAME.keyDown()) {
            displacePlayer(0, moveSpeed);
        }
    }
}
