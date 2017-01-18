import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

/**
 * Write a description of class LevelSelection here.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class LevelSelection extends World
{
    
    private final boolean DEBUG = true;
    
    private GameSettings gameSettings;
    
    private final String BG_IMAGE_PATH = "LevelScreen.png";
    
    private DisplayerTop backButton;
    private DisplayerTop left1Button;
    private DisplayerTop right1Button;
    private DisplayerTop left2Button;
    private DisplayerTop right2Button;
    private DisplayerTop playButton;
    
    // game info variables
    private DisplayerMiddle levelDisplay;
    private DisplayerMiddle difficultyDisplay;
    
    private final int MAX_LEVEL = 8;
    private int levelSelect = 1;
    private final int MAX_DIFFICULTY = 2;
    private int difficulty = 1;
    
    /**
     * Constructor for objects of class LevelSelection.
     * 
     */
    public LevelSelection(GameSettings gs)
    {
        // Create a new world with 512x512 cells with a cell size of 1x1 pixels.
        super(512, 512, 1);
        gameSettings = gs;
        
        // set paint order for the title screen
        // earlier class is drawn on a later class
        setPaintOrder(DisplayerTop.class,
                      DisplayerMiddle.class,
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
        
        // left 1 button
        temp = new GreenfootImage(40, 20);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        left1Button = new DisplayerTop(temp);
        addObject(left1Button,230,250);
        
        // right 1 button
        temp = new GreenfootImage(40, 20);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        right1Button = new DisplayerTop(temp);
        addObject(right1Button,284,250);
        
        // left 2 button
        temp = new GreenfootImage(40, 20);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        left2Button = new DisplayerTop(temp);
        addObject(left2Button,230,400);
        
        // right 2 button
        temp = new GreenfootImage(40, 20);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        right2Button = new DisplayerTop(temp);
        addObject(right2Button,284,400);
        
        // play button
        temp = new GreenfootImage(110, 30);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        playButton = new DisplayerTop(temp);
        addObject(playButton,260,485);
        
        // game info display
        levelDisplay = new DisplayerMiddle("nothing.png");
        addObject(levelDisplay,260,220);
        difficultyDisplay = new DisplayerMiddle("nothing.png");
        addObject(difficultyDisplay,260,370);
        updateInfoDisplay();
    }
    
    private void updateInfoDisplay()
    {
        // set up necessary variables
        GreenfootImage tempImage;
        Font tempFont = new Font("Comic Sans MS", Font.PLAIN, 40);
        int textWidth;
        String str;
        
        // do level display
        // set up the GreenfootImage
        tempImage = new GreenfootImage(200,48);
        tempImage.setColor(Color.BLACK);
        tempImage.setFont(tempFont);
        
        // draw centered text
        // the main challenge is getting a FontMetrics object
        str = Integer.toString(levelSelect);
        textWidth = tempImage.getAwtImage().getGraphics().getFontMetrics(tempFont).stringWidth(str);
        tempImage.drawString(str, 100-textWidth/2, 30);
        
        // update the image
        levelDisplay.setImage(tempImage);
        
        // do difficulty display
        // set up the GreenfootImage
        tempImage = new GreenfootImage(200,48);
        tempImage.setColor(Color.BLACK);
        tempImage.setFont(tempFont);
        
        // draw centered text
        if (difficulty == 1)      str = "Easy";
        else if (difficulty == 2) str = "Hard";
        else                      str = "Unknown";
        textWidth = tempImage.getAwtImage().getGraphics().getFontMetrics(tempFont).stringWidth(str);
        tempImage.drawString(str, 100-textWidth/2, 30);
        
        // update the image
        difficultyDisplay.setImage(tempImage);
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
        updateInfoDisplay();
    }
    
    private void difficultyChange(int amount)
    {
        // change the difficulty
        difficulty += amount;
        
        if (difficulty < 1) {
            // difficulty too low
            difficulty = 1;
        }
        else if (!(levelSelect <= MAX_LEVEL || DEBUG)) {
            // difficulty too high
            difficulty = MAX_DIFFICULTY;
        }
        
        // update number to screen
        updateInfoDisplay();
    }
    
    // level scrolling variables
    int SCROLL_MAX_COOLDOWN = 15;
    int scrollCooldown = 0;
    
    /**
     * Every act, this World reads the user input and responds appropriately.
     */
    public void act()
    {
        // back button
        if (Greenfoot.isKeyDown("escape") || Greenfoot.mouseClicked(backButton)) {
            Greenfoot.setWorld(new Title(gameSettings));
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
        
        // left 1 button
        if ((Greenfoot.isKeyDown("left") && scrollCooldown==0) || Greenfoot.mouseClicked(left1Button)) {
            // do left press
            scrollCooldown = SCROLL_MAX_COOLDOWN;
            levelChange(-1);
        }
        
        // right 1 button
        if ((Greenfoot.isKeyDown("right") && scrollCooldown==0) || Greenfoot.mouseClicked(right1Button)) {
            // do right press
            scrollCooldown = SCROLL_MAX_COOLDOWN;
            levelChange(1);
        }
        
        // left 2 button
        if (Greenfoot.mouseClicked(left2Button)) {
            // do left press
            scrollCooldown = SCROLL_MAX_COOLDOWN;
            difficultyChange(-1);
        }
        
        // right 2 button
        if (Greenfoot.mouseClicked(right2Button)) {
            // do right press
            scrollCooldown = SCROLL_MAX_COOLDOWN;
            difficultyChange(1);
        }
        
        // play button
        if (Greenfoot.mouseClicked(playButton)) {
            // change game settings
            gameSettings.setLevel(levelSelect);
            gameSettings.setDifficulty(difficulty);
            
            try {
                Greenfoot.setWorld(new Game(gameSettings, 2, null));
            }
            catch (IOException e) {
                //
            }
        }
    }
}
