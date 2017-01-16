import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a description of class Enemy101 here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Enemy101 extends Enemy
{
    
    protected int enemySize = 30;
    protected int enemySpeed;
    protected int shootSpeed;
    
    public Enemy101(Game g, ArrayList<String> info) {
        // initialize
        game = g;
        deadAnimation = 25;
        enemySpeed = Integer.parseInt(info.get(0));
        shootSpeed = Integer.parseInt(info.get(1));
        
        // add this actor
        game.addObject(this, Integer.parseInt(info.get(2)), -20);
    }
    
    protected void dying() {
        // change animations when necessary
        if (deadAnimation == 24) setImage("101santaPoopDeath1.png");
        if (deadAnimation == 19) setImage("101santaPoopDeath2.png");
        if (deadAnimation == 14) setImage("101santaPoopDeath3.png");
        if (deadAnimation == 9)  setImage("101santaPoopDeath4.png");
        if (deadAnimation == 4)  setImage("101santaPoopDeath5.png");
    }
    
    protected void alive() {
        // animations and movement
        if (timer%70 < 30) {
            setImage("101santaPoop1.png");
            move(0, 1);
        }
        else if (timer%70 < 40)  {
            setImage("101santaPoop2.png");
            move(0, 1);
        }
        else if (timer%70 < 50)  {
            setImage("101santaPoop3.png");
            move(0, enemySpeed);
        }
        else {
            setImage("101santaPoop4.png");
            move(0, 1);
            
            if (timer%70 == 50) {
                // spawn tears
                new Enemy102(game, makeArrayList(new int[]{getX(), getY(), 0, shootSpeed}));
                new Enemy102(game, makeArrayList(new int[]{getX(), getY(), 0, -shootSpeed}));
                new Enemy102(game, makeArrayList(new int[]{getX(), getY(), shootSpeed, 0}));
                new Enemy102(game, makeArrayList(new int[]{getX(), getY(), -shootSpeed, 0}));
            }
        }
        
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
