import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a description of class Enemy102c here.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class Enemy102c extends Enemy
{
    
    protected int enemySize = 20;
    private double ax;
    private double ay;
    private double vx = 0.0;
    private double vy = 0.0;
    private double dx = 0.0;
    private double dy = 0.0;
    private int restTime;
    
    public Enemy102c(Game g, int px, int py, double ax, double ay, int restTime) {
        // initialize
        game = g;
        deadAnimation = 1;
        this.ax = ax;
        this.ay = ay;
        this.restTime = restTime;
        if (restTime == 0) setImage("blooddrop.png");
        
        // add this actor
        game.addObject(this, px, py);
        
        // plus a sanity check
        if (outOfBounds(200)) {
            // out of bounds
            killFast();
        }
    }
    
    protected void dying() {
        // no changes needed
    }
    
    protected void alive() {
        // check if resting
        if (restTime>0) {
            restTime--;
            
            // check if done resting
            if (restTime == 0) setImage("blooddrop.png");
            return;
        }
        
        // movement only
        // do x movement
        vx += ax;
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
        vy+=ay;
        dy+=vy;
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
        
        if (outOfBounds(200)) {
            // out of bounds
            killFast();
        }
    }
}
