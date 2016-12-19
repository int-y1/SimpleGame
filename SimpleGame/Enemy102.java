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
    
    protected int enemySize = 20;
    private int vx;
    private int vy;
    
    public Enemy102(Game g, ArrayList<Integer> info) {
        // initialize
        game = g;
        deadAnimation = 1;
        vx = info.get(2);
        vy = info.get(3);
        
        // add this actor
        game.addObject(this, info.get(0), info.get(1));
    }
    
    protected void dying() {
        // no changes needed
    }
    
    protected void alive() {
        // movement only
        move(vx, vy);
        
        if (game.getPlayerDist(getX(), getY()) <= enemySize) {
            // hit player
            game.playerLoseLife();
            kill();
        }
        
        if (outOfBounds(20)) {
            // out of bounds
            killFast();
        }
    }
}
