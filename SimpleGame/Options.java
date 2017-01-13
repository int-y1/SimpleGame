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
    
    private final boolean DEBUG;
    private final String BG_IMAGE_PATH = "OptionScreen.png";
    
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
        GreenfootImage temp;
        
        // back button
        temp = new GreenfootImage(90, 120);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        backButton = new DisplayerTop(temp);
        addObject(backButton,45,452);
        
        // left button
        temp = new GreenfootImage(36, 18);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        leftButton = new DisplayerTop(temp);
        addObject(leftButton,182,250);
        
        // right button
        temp = new GreenfootImage(36, 18);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        rightButton = new DisplayerTop(temp);
        addObject(rightButton,342,250);
        
        // play button
        temp = new GreenfootImage(110, 30);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        playButton = new DisplayerTop(temp);
        addObject(playButton,260,250);
        
        // level display
        levelDisplay = new DisplayerTop("nothing.png");
        addObject(levelDisplay,260,210);
        updateLevelDisplay();
    }
    
    private void updateLevelDisplay()
    {
        // set up the font
        Font tempFont = new Font("Comic Sans MS", Font.PLAIN, 40);
        // set up the GreenfootImage
        GreenfootImage tempImage = new GreenfootImage(200,32);
        tempImage.setColor(Color.BLACK);
        tempImage.setFont(tempFont);
        
        // draw centered text
        // the main challenge is getting a FontMetrics object
        String str = Integer.toString(levelSelect);
        int textWidth = tempImage.getAwtImage().getGraphics().getFontMetrics(tempFont).stringWidth(str);
        tempImage.drawString(str, 100-textWidth/2, 30);
        
        // update the image
        levelDisplay.setImage(tempImage);
    }
    
    private void levelChange(int amount)
    {
        // change the level
        levelSelect += amount;
        
        if (levelSelect < 1) {
            // level number too low
            levelSelect = 1;
        }
        else if (!(levelSelect <= MAX_LEVEL || DEBUG)) {
            // level number too high
            levelSelect = MAX_LEVEL;
        }
        
        // update number to screen
        updateLevelDisplay();
    }
    
    int SCROLL_MAX_COOLDOWN = 15;
    int scrollCooldown = 0;
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // back button
        if (Greenfoot.isKeyDown("escape") || Greenfoot.mouseClicked(backButton)) {
            Greenfoot.setWorld(new Title());
        }
        
        // do scroll countdown
        if (scrollCooldown > 0) {
            if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right")) {
                // something is pressed
                // lower cooldown by one
                scrollCooldown--;
            }
            else {
                // nothing is pressed
                // set to zero automatically
                scrollCooldown = 0;
            }
        }
        
        // left button
        if ((Greenfoot.isKeyDown("left") && scrollCooldown==0) || Greenfoot.mouseClicked(leftButton)) {
            // do left press
            scrollCooldown = SCROLL_MAX_COOLDOWN;
            levelChange(-1);
        }
        
        // right button
        if ((Greenfoot.isKeyDown("right") && scrollCooldown==0) || Greenfoot.mouseClicked(rightButton)) {
            // do right press
            scrollCooldown = SCROLL_MAX_COOLDOWN;
            levelChange(1);
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
