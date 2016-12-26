import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Enemy extends Actor
{
    
    protected Game game;
    protected boolean dead = false;
    protected int deadAnimation;
    protected int timer=0;          // number of frames since this object is created
    
    /**
     * Move the image.
     */
    public void move(int dx, int dy) {
        setLocation(getX()+dx, getY()+dy);
    }
    
    public void kill() {
        dead = true;
    }
    
    protected void killFast() {
        game.removeObject(this);
    }
    
    public boolean outOfBounds(int tolerance)
    {
        // check the four edges
        if (getX() < -tolerance)    return true;
        if (getX() > 512+tolerance) return true;
        if (getY() < -tolerance)    return true;
        if (getY() > 496+tolerance) return true;
        
        // this object is within bounds
        return false;
    }
    
    protected void dying() {
        //
    }
    
    protected void alive() {
        //
    }
    
    /**
     * Act - do whatever the Enemy100 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (dead) {
            // check the death animation countdown
            if (deadAnimation == 0) {
                game.removeObject(this);
                return;
            }
            deadAnimation--;
            
            dying();
        }
        else {
            // enemy is still alive
            timer++;
            alive();
        }
    }
}
