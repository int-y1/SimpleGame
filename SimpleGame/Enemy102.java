import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a description of class Enemy102 here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Enemy102 extends Enemy
{
    
    private int vx;
    private int vy;
    
    public Enemy102(Game g, ArrayList<String> info) {
        // initialize
        game = g;
        deadAnimation = 1;
        vx = Integer.parseInt(info.get(2));
        vy = Integer.parseInt(info.get(3));
        enemySize = 20;
        hittable = false;
        
        // add this actor
        game.addObject(this, Integer.parseInt(info.get(0)), Integer.parseInt(info.get(1)));
    }
    
    protected void dying() {
        // no changes needed
    }
    
    protected void alive() {
        // movement only
        move(vx, vy);
        
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
