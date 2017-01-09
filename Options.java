import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.IOException;

/**
 * Write a description of class Options here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Options extends World
{
    
    private final String BG_IMAGE_PATH = "TitleScreen.gif";
    
    private DisplayerTop backButton;
    private DisplayerTop leftButton;
    private DisplayerTop rightButton;
    private DisplayerTop playButton;
    
    private final int MAX_LEVEL=6;
    private int levelSelect=1;
    
    /**
     * Constructor for objects of class Options.
     * 
     */
    public Options()
    {
        // Create a new world with 512x512 cells with a cell size of 1x1 pixels.
        super(512, 512, 1);
        
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
    }
    
    private void levelDecrease()
    {
        if (levelSelect > 1) {
            // possible to decrease level number
            levelSelect--;
        }
    }
    
    private void levelIncrease()
    {
        if (levelSelect < MAX_LEVEL) {
            // possible to increase level number
            levelSelect++;
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
