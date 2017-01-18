import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

/**
 * Write a description of class Credits here.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class Credits extends World
{
    
    private final boolean DEBUG = false;
    
    private GameSettings gameSettings;
    
    private final String BG_IMAGE_PATH = "CreditsPage.png";
    private DisplayerTop backButton;
    
    /**
     * Constructor for objects of class Credits.
     * 
     */
    public Credits(GameSettings gs)
    {
        // Create a new world with 512x512 cells with a cell size of 1x1 pixels.
        super(512, 512, 1);
        gameSettings = gs;
        
        // make the background
        addObject(new GifDisplayer(BG_IMAGE_PATH), 256, 256);
        
        // make the back button
        GreenfootImage temp = new GreenfootImage(56, 106);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        backButton = new DisplayerTop(temp);
        addObject(backButton,28,459);
    }
    
    /**
     * Every act, this World reads whether the back button is pressed.
     */
    public void act()
    {
        // back button
        if (Greenfoot.isKeyDown("escape") || Greenfoot.mouseClicked(backButton)) {
            Greenfoot.setWorld(new Title(gameSettings));
        }
    }
}
