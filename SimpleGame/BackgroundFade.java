import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class BackgroundFade here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class BackgroundFade extends Actor
{
    
    private GreenfootImage gfImage;
    
    /**
     * Initialize the tint at fully transparent.
     */
    public BackgroundFade() {
        gfImage = new GreenfootImage(512, 512);
        gfImage.setColor(new Color(0, 0, 0));
        gfImage.fill();
        changeFade(0);
    }
    
    public void changeFade(int alpha) {
        if (alpha < 0) alpha = 0;
        if (alpha > 255) alpha = 255;
        gfImage.setTransparency(alpha);
        setImage(gfImage);
    }
}
