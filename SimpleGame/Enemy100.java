import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Enemy100 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy100 extends Enemy
{
    
    protected int deadAnimation = 10;
    protected int enemySize = 20;
    
    public Enemy100(Game g, ArrayList<Integer> info) {
        // initialize
        game = g;
        assert info.size() == 1;
        
        // add this actor
        game.addObject(this, info.get(0), 0);
    }
    
    protected void dying() {
        //
    }
    
    protected void alive() {
        move(0, 1);
        
        if (game.getPlayerDist(getX(), getY()) <= enemySize) {
            // hit player
            game.playerLoseLife();
            kill();
        }
    }
}
