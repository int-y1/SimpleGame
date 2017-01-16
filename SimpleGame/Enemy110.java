import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a description of class Enemy110 here.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class Enemy110 extends Enemy
{
    
    protected int enemySpeed = -10;
    protected int shootSpeed = 2;
    private int jumpFrame = 0;
    private int jumps = 8;
    private double bulletAccel = 0.002;
    
    private double enemyScale = 1.0+jumps*0.1;
    
    public Enemy110(Game g, ArrayList<String> info) {
        // initialize
        game = g;
        deadAnimation = 1;
        setScaledImage("101santaPoop1.png");
        enemySize = 30+jumps*3;
        
        // add this actor
        game.addObject(this, 256, -100);
    }
    
    protected void dying() {
    }
    
    private void setScaledImage(String imageFile) {
        // set image first
        setImage(imageFile);
        // double the image size
        getImage().scale((int)(getImage().getWidth()*enemyScale), (int)(getImage().getHeight()*enemyScale));
        //setImage(getImage());
    }
    
    private void lowerScale() {
        // change scaling and hitbox size
        enemyScale-=0.1;
        enemySize-=3;
    }
    
    protected void alive() {
        // animations and movement
        if (jumpFrame == 0) {
            // get ready to jump
            if (getY() > 384 && jumps > 0) {
                setScaledImage("101santaPoop2.png");
                jumpFrame = 1;
                jumps--;
            }
            move(0, 1);
        }
        else if (jumpFrame < 10) {
            // do nothing right now
            jumpFrame++;
            move(0, 1);
        }
        else if (jumpFrame < 40) {
            // set image
            if (jumpFrame == 10) setScaledImage("101santaPoop3.png");
            // do the actual jump
            jumpFrame++;
            move(0, enemySpeed);
        }
        else if (jumpFrame < 60) {
            if (jumpFrame == 40) {
                // set image
                lowerScale();
                setScaledImage("101santaPoop4.png");
                
                // plus, spawn some tears
                for (int i=-20; i<20; i++) {
                    // get constants
                    int delayTime = Math.abs(i*8);
                    double phase = i*0.3 + jumps*5;
                    
                    // horizontal bullets
                    new Enemy102c(game, getX()+i*32+8, getY(), bulletAccel/2 * Math.sin(phase), bulletAccel * Math.cos(phase), delayTime);
                    new Enemy102c(game, getX()+i*32-8, getY(), -bulletAccel/2 * Math.sin(phase), -bulletAccel * Math.cos(phase), delayTime);
                    // vertical bullets
                    new Enemy102c(game, getX(), getY()+i*32, -bulletAccel/2 * Math.sin(phase), -bulletAccel * Math.cos(phase), delayTime);
                }
                
                // replace the boss if there are no jumps left
                if (jumps==0) {
                    // make a santa poop at this location
                    Enemy101 tempEnemy = new Enemy101(game, makeArrayList(new int[]{3,2,256}));
                    tempEnemy.setLocation(getX(), getY());
                    removeFast();
                    return;
                }
            }
            // do nothing
            jumpFrame++;
            move(0, 1);
        }
        else {
            // cycle is done
            jumpFrame = 0;
            setScaledImage("101santaPoop1.png");
        }
        
        if (game.getPlayerDist(getX(), getY()) <= enemySize) {
            // hit player
            game.playerLoseLife();
        }
        
        if (outOfBounds(150)) {
            // out of bounds
            // shouldn't happen
            removeFast();
        }
    }
}
