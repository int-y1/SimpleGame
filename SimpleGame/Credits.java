import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * This World displays the creators of the game.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class Credits extends World
{
    
    private GameSettings gameSettings;
    
    // debug
    private final boolean DEBUG = false;
    
    // constants
    private final String BG_IMAGE_PATH = "CreditsPage.png";
    
    // displayers
    // all of the buttons are transparent and set to the top
    private DisplayerTop backButton;
    
    /**
     * Constructor for the Credits world.
     * Create one button.
     */
    public Credits(GameSettings gs)
    {
        // set screen to 512x512 with 1x1 pixels
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
