import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Player extends Actor
{
    
    private final int SPEED_NORMAL = 5;
    private final int SPEED_SLOW = 2;
    protected Game game;
    
    public Player(Game g)
    {
        // get this game
        game = g;
        
        setImage("Isaac/backwards1.png");
    }
    
    // methods for coordinate geometry
    
    public int getDist(int x, int y)
    {
        return (int) Math.sqrt(Math.pow(x-getX(), 2) + Math.pow(y-getY(), 2));
    }
    
    public void displacePlayer(int dx, int dy)
    {
        int newX = getX()+dx;
        int newY = getY()+dy;
        
        if (newX<32)  newX=32;
        if (newX>480) newX=480;
        if (newY<32)  newY=32;
        if (newY>480) newY=480;
        
        setLocation(newX, newY);
    }
    public void wat(){game.playerLoseLife();}
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // read movements
        int moveSpeed;
        if (game.keyS()) moveSpeed = SPEED_SLOW;
        else moveSpeed = SPEED_NORMAL;
        
        // read the left/right keys
        if (game.keyLeft()) {
            if (!game.keyRight()) {
                displacePlayer(-moveSpeed, 0);
            }
        }
        else if (game.keyRight()) {
            displacePlayer(moveSpeed, 0);
        }
        
        // read the up/down keys
        if (game.keyUp()) {
            if (!game.keyDown()) {
                displacePlayer(0, -moveSpeed);
            }
        }
        else if (game.keyDown()) {
            displacePlayer(0, moveSpeed);
        }
    }
}
