import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class PlayerBullet here.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class PlayerBullet extends GameObject
{
    
    //protected int bulletSize = 20;
    private double vx;
    private double vy;
    private double dx = 0;
    private double dy = 0;
    
    public PlayerBullet(Game g, double vx, double vy) {
        // initialize
        game = g;
        this.vx = vx;
        this.vy = vy;
    }
    
    public void act() {
        // do movement
        dx += vx;
        dy += vy;
        move((int) dx, (int) dy);
        dx -= (int) dx;
        dy -= (int) dy;
        
        // check hitboxes
        for (Enemy e : (List<Enemy>) game.getObjects(Enemy.class)) {
            if (e.isHitting(getX(), getY())) {
                // hit enemy
                e.getHit();
                // remove this object
                Greenfoot.playSound("tear.mp3");
                removeFast();
                return;
            }
        }
        
        if (outOfBounds(100)) {
            // out of bounds
            Greenfoot.playSound("tear.mp3");
            removeFast();
        }
    }
}
