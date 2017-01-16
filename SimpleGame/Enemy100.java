import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Enemy100 here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Enemy100 extends Enemy
{
    
    protected int enemySize = 20;
    protected int enemySpeed;
    
    public Enemy100(Game g, ArrayList<String> info) {
        // initialize
        game = g;
        deadAnimation = 16;
        enemySpeed = Integer.parseInt(info.get(0));
        
        // add this actor
        game.addObject(this, Integer.parseInt(info.get(1)), -20);
    }
    
    protected void dying() {
        // change animations when necessary
        if (deadAnimation == 15) setImage("100fliesDeath1.png");
        if (deadAnimation == 11) setImage("100fliesDeath2.png");
        if (deadAnimation == 7)  setImage("100fliesDeath3.png");
        if (deadAnimation == 3)  setImage("100fliesDeath4.png");
    }
    
    protected void alive() {
        // animations
        if (timer%10 < 5) setImage("100flies1.png");
        else              setImage("100flies2.png");
        
        // movement
        move(0, enemySpeed);
        
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
