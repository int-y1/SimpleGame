import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

/**
 * Write a description of class Options here.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class Options extends World
{
    
    private final boolean DEBUG = false;
    private final String BG_IMAGE_PATH = "OptionScreen.png";
    
    private GameSettings gameSettings;
    
    private DisplayerTop backButton;
    private DisplayerTop yesButton;
    private DisplayerTop noButton;
    private DisplayerTop fly;
    
    private boolean musicOn;
    
    /**
     * Constructor for objects of class Options.
     * 
     */
    public Options(GameSettings gs)
    {
        // Create a new world with 512x512 cells with a cell size of 1x1 pixels.
        super(512, 512, 1);
        gameSettings = gs;
        
        // set paint order for the title screen
        // earlier class is drawn on a later class
        setPaintOrder(Displayer.class,
                      GifDisplayer.class);
        
        // make the background
        addObject(new GifDisplayer(BG_IMAGE_PATH), 256, 256);
        
        // make buttons
        GreenfootImage temp;
        
        // back button
        temp = new GreenfootImage(90, 120);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        backButton = new DisplayerTop(temp);
        addObject(backButton,45,452);
        
        // yes button
        temp = new GreenfootImage(22, 36);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        yesButton = new DisplayerTop(temp);
        addObject(yesButton,286,225);
        
        // no button
        temp = new GreenfootImage(22, 36);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        noButton = new DisplayerTop(temp);
        addObject(noButton,333,225);
        
        // fly
        musicOn = (gameSettings.getMusicVolume() > 0);
        fly = new DisplayerTop("100flies1.png");
        addObject(fly,342,250);
        moveFly();
    }
    
    private void moveFly()
    {
        // choose position of fly, based on musicSelection
        if (musicOn) {
            fly.setLocation(yesButton.getX(), yesButton.getY()-25);
        }
        else {
            fly.setLocation(noButton.getX(), noButton.getY()-25);
        }
    }
    
    /**
     * Every act, this World reads the user input and responds appropriately.
     */
    public void act()
    {
        // back button
        if (Greenfoot.isKeyDown("escape") || Greenfoot.mouseClicked(backButton)) {
            Greenfoot.setWorld(new Title(gameSettings));
        }
        
        // yes button
        if (Greenfoot.isKeyDown("left") || Greenfoot.mouseClicked(yesButton)) {
            musicOn = true;
            gameSettings.setMusicVolume(100);
            moveFly();
        }
        
        // no button
        if (Greenfoot.isKeyDown("right") || Greenfoot.mouseClicked(noButton)) {
            musicOn = false;
            gameSettings.setMusicVolume(0);
            moveFly();
        }
    }
}
