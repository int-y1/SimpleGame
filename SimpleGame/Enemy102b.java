import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a description of class Enemy102b here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Enemy102b extends Enemy
{
    
    private double vx;
    private double vy;
    private double dx = 0.0;
    private double dy = 0.0;
    
    public Enemy102b(Game g, int px, int py, double vx, double vy) {
        // initialize
        game = g;
        deadAnimation = 1;
        this.vx = vx;
        this.vy = vy;
        enemySize = 20;
        hittable = false;
        
        // add this actor
        game.addObject(this, px, py);
    }
    
    protected void dying() {
        // no changes needed
    }
    
    protected void alive() {
        // movement only
        // do x movement
        dx += vx;
        while (dx>=1.0) {
            move(1, 0);
            dx-=1.0;
        }
        while (dx<=-1.0) {
            move(-1, 0);
            dx+=1.0;
        }
        // do y movement
        dy += vy;
        while (dy>=1.0) {
            move(0, 1);
            dy-=1.0;
        }
        while (dy<=-1.0) {
            move(0, -1);
            dy+=1.0;
        }
        
        // check hitboxes
        if (game.getPlayerDist(getX(), getY()) <= enemySize) {
            // hit player
            game.playerLoseLife();
            kill();
        }
        
        if (outOfBounds(100)) {
            // out of bounds
            removeFast();
        }
    }
}