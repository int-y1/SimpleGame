import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a description of class PlayerBullet here.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class PlayerBullet extends GameObject
{
    
    protected int bulletSize = 20;
    private int vx;
    private int vy;
    
    public PlayerBullet(Game g, ArrayList<String> info) {
        // initialize
        game = g;
        vx = Integer.parseInt(info.get(2));
        vy = Integer.parseInt(info.get(3));
        
        // add this actor
        game.addObject(this, Integer.parseInt(info.get(0)), Integer.parseInt(info.get(1)));
    }
    
    public void act() {
        // movement only
        move(vx, vy);
        
        // check hitboxes
        for (Enemy e:game.getObjects(Enemy.class)) {
            if (e.isInside(getX(), getY())) {
                // hit enemy
                e.getHit();
                // remove this object
                removeFast();
                return;
            }
        }
        
        if (outOfBounds(100)) {
            // out of bounds
            removeFast();
        }
    }
}
