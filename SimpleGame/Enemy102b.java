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
    
    protected int enemySize = 20;
    private double vx;
    private double vy;
    
    private double offx;
    private double offy;
    
    public Enemy102b(Game g, int px, int py, double vx, double vy) {
        // initialize
        game = g;
        deadAnimation = 1;
        this.vx = vx;
        this.vy = vy;
        
        // add this actor
        game.addObject(this, px, py);
    }
    
    protected void dying() {
        // no changes needed
    }
    
    protected void alive() {
        // movement only
        // do x movement
        offx += vx;
        while (offx>=1.0) {
            move(1, 0);
            offx-=1.0;
        }
        while (offx<=-1.0) {
            move(-1, 0);
            offx+=1.0;
        }
        // do y movement
        offy += vy;
        while (offy>=1.0) {
            move(0, 1);
            offy-=1.0;
        }
        while (offy<=-1.0) {
            move(0, -1);
            offy+=1.0;
        }
        
        // check hitboxes
        if (game.getPlayerDist(getX(), getY()) <= enemySize) {
            // hit player
            game.playerLoseLife();
            kill();
        }
        
        if (outOfBounds(100)) {
            // out of bounds
            killFast();
        }
    }
}