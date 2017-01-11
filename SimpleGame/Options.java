import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.IOException;
import java.awt.Color;

/**
 * Write a description of class Options here.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class Options extends World
{
    
    private final boolean DEBUG;
    private final String BG_IMAGE_PATH = "TitleScreen.gif";
    
    private DisplayerTop backButton;
    private DisplayerTop leftButton;
    private DisplayerTop rightButton;
    private DisplayerTop playButton;
    private DisplayerTop levelDisplay;
    
    private final int MAX_LEVEL=8;
    private int levelSelect=1;
    
    /**
     * Constructor for objects of class Options.
     * 
     */
    public Options(boolean debug)
    {
        // Create a new world with 512x512 cells with a cell size of 1x1 pixels.
        super(512, 512, 1);
        DEBUG = debug;
        
        // set paint order for the title screen
        // earlier class is drawn on a later class
        setPaintOrder(Displayer.class,
                      GifDisplayer.class);
        
        // make the background
        addObject(new GifDisplayer(BG_IMAGE_PATH), 256, 256);
        
        // make buttons
        // make the back button
        backButton = new DisplayerTop("100flies1.png");
        addObject(backButton,64,64);
        // make the left/right buttons
        leftButton = new DisplayerTop("100flies1.png");
        addObject(leftButton,224,128);
        rightButton = new DisplayerTop("100flies1.png");
        addObject(rightButton,288,128);
        // make the play button
        playButton = new DisplayerTop("100flies1.png");
        addObject(playButton,256,128);
        
        // make the level display
        levelDisplay = new DisplayerTop("nothing.png");
        addObject(levelDisplay,256,256);
        updateLevelDisplay();
    }
    
    private void updateLevelDisplay()
    {
        // make the GreenfootImage
        GreenfootImage tempImage = new GreenfootImage(String.format("Room: %d",levelSelect), 32, Color.BLACK, new Color(0,0,0,0));
        levelDisplay.setImage(tempImage);
    }
    
    private void levelDecrease()
    {
        if (levelSelect > 1) {
            // possible to decrease level number
            levelSelect--;
            updateLevelDisplay();
        }
    }
    
    private void levelIncrease()
    {
        if (levelSelect < MAX_LEVEL || DEBUG) {
            // possible to increase level number
            levelSelect++;
            updateLevelDisplay();
        }
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // back button
        if (Greenfoot.mouseClicked(backButton)) {
            Greenfoot.setWorld(new Title());
        }
        
        // left button
        if (Greenfoot.mouseClicked(leftButton)) {
            levelDecrease();
        }
        
        // right button
        if (Greenfoot.mouseClicked(rightButton)) {
            levelIncrease();
        }
        
        // play button
        if (Greenfoot.mouseClicked(playButton)) {
            try {
                Greenfoot.setWorld(new Game(levelSelect, 2, null));
            }
            catch (IOException e) {
                //
            }
        }
    }
}
