import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Enemy here.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class Enemy extends GameObject
{
    
    protected boolean dead = false;
    protected int deadAnimation;
    protected int enemySize;
    protected int enemyHP;
    
    public void kill() {
        dead = true;
    }
    
    public boolean isInside(int x, int y)
    {
        // return true if this point is inside/touching the enemy
        double dist = Math.sqrt(Math.pow(getX()-x,2) + Math.pow(getY()-y,2));
        return dist <= enemySize;
    }
    
    public void getHit()
    {
        enemyHP -= 1000;
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
