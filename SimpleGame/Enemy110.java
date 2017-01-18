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
    private int jumpNum = 0;
    private double bulletAccel = 0.002;
    
    private double enemyScale = 1.8;
    
    public Enemy110(Game g, ArrayList<String> info) {
        // initialize
        game = g;
        deadAnimation = 1;
        setScaledImage("101santaPoop1.png");
        enemySize = (int)(30*enemyScale);
        enemyHP = 400000;
        
        // add this actor
        game.addObject(this, 256, -100);
    }
    
    protected void dying() {
    }
    
    private void setScaledImage(String imageFile) {
        // set image first
        setImage(imageFile);
        // change the image size
        getImage().scale((int)(getImage().getWidth()*enemyScale), (int)(getImage().getHeight()*enemyScale));
    }
    
    private void updateScale() {
        // change scaling and hitbox size
        enemyScale = 1+enemyHP/500000.0;
        enemySize = (int)(30*enemyScale);
    }
    
    public void getHit()
    {
        enemyHP -= 1000;
        if (enemyHP < 0) enemyHP = 0;
        updateScale();
    }
    
    protected void alive() {
        // animations and movement
        if (jumpFrame == 0) {
            // get ready to jump
            if (getY() > 384) {
                // do a jump
                jumpFrame = 1;
                enemyHP -= 50000;
                updateScale();
                setScaledImage("101santaPoop2.png");
            }
            else {
                // keep waiting
                setScaledImage("101santaPoop1.png");
            }
            move(0, 1);
        }
        else if (jumpFrame < 10) {
            if (jumpFrame == 1) Greenfoot.playSound("roar.mp3");
                
            // do nothing right now
            setScaledImage("101santaPoop2.png");
            jumpFrame++;
            move(0, 1);
        }
        else if (jumpFrame < 40) {
            // do the actual jump
            setScaledImage("101santaPoop3.png");
            jumpFrame++;
            move(0, enemySpeed);
            
            // play shoot sound
            if (jumpFrame == 30) Greenfoot.playSound("heavyLand.mp3");
        }
        else if (jumpFrame < 60) {
            // set image
            setScaledImage("101santaPoop4.png");
            
            if (jumpFrame == 40) {
                
                // plus, spawn some tears
                for (int i=-20; i<20; i++) {
                    // get constants
                    int delayTime = Math.abs(i*8);
                    jumpNum++;
                    double phase = i*0.3 + jumpNum*5;
                    
                    // horizontal bullets
                    new Enemy102c(game, getX()+i*32+8, getY(), bulletAccel/2 * Math.sin(phase), bulletAccel * Math.cos(phase), delayTime);
                    new Enemy102c(game, getX()+i*32-8, getY(), -bulletAccel/2 * Math.sin(phase), -bulletAccel * Math.cos(phase), delayTime);
                    // vertical bullets
                    new Enemy102c(game, getX(), getY()+i*32, -bulletAccel/2 * Math.sin(phase), -bulletAccel * Math.cos(phase), delayTime);
                }
                
                // replace the boss if there are no jumps left
                if (enemyHP <= 0) {
                    // make a santa poop at this location
                    Enemy101 tempEnemy = new Enemy101(game, makeArrayList(new int[]{-2,2,256}));
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
