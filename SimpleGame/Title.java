import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Title here.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class Title extends World
{
    
    private final boolean DEBUG = true;
    private final String DEBUG_BUTTON_PATH = "map0c.png";
    private DisplayerTop debugPlayButton;
    
    private GameSettings gameSettings;
    
    private final String PLAY_BUTTON_PATH = "map0c.png";
    private final String BG_IMAGE_PATH = "TitleScreen.gif";
    
    private DisplayerTop playButton;
    private DisplayerTop optionsButton;
    private DisplayerTop creditsButton;
    private DisplayerTop exitButton;
    
    /**
     * Constructor to start the game.
     */
    public Title()
    {
        // Create a new world with 512x512 cells with a cell size of 1x1 pixels.
        super(512, 512, 1);
        
        Greenfoot.setWorld(new Title(null));
    }
    
    /**
     * The real constructor for this game.
     */
    public Title(GameSettings gs)
    {
        // Create a new world with 512x512 cells with a cell size of 1x1 pixels.
        super(512, 512, 1);
        
        if (gs == null) {
            gameSettings = new GameSettings();
        }
        else {
            gameSettings = gs;
        }
        
        // set paint order for the title screen
        // earlier class is drawn on a later class
        setPaintOrder(DisplayerTop.class,
                      GifDisplayer.class);
        
        // make the background
        addObject(new GifDisplayer(BG_IMAGE_PATH), 256, 256);
        
        // make the buttons
        GreenfootImage temp;
        
        // play button
        temp = new GreenfootImage(110, 60);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        playButton = new DisplayerTop(temp);
        addObject(playButton, 250, 410);
        
        // options button
        temp = new GreenfootImage(100, 35);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        optionsButton = new DisplayerTop(temp);
        addObject(optionsButton, 238, 473);
        
        // credits button
        temp = new GreenfootImage(80, 60);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        creditsButton = new DisplayerTop(temp);
        addObject(creditsButton, 50, 210);
        
        // exit button
        temp = new GreenfootImage(60, 60);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        exitButton = new DisplayerTop(temp);
        addObject(exitButton, 422, 214);
        
        // create music
        gameSettings.setMusic("menu.mp3");
        
        if (DEBUG) {
            debugPlayButton = new DisplayerTop(PLAY_BUTTON_PATH);
            addObject(debugPlayButton, 64, 480);
        }
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // play button
        if (Greenfoot.isKeyDown("enter") || Greenfoot.mouseClicked(playButton)) {
            Greenfoot.setWorld(new LevelSelection(gameSettings));
        }
        
        // options button
        if (Greenfoot.mouseClicked(optionsButton)) {
            Greenfoot.setWorld(new Options(gameSettings));
        }
        
        // credits button
        if (Greenfoot.mouseClicked(creditsButton)) {
            Greenfoot.setWorld(new Credits(gameSettings));
        }
        
        // exit button
        if (Greenfoot.mouseClicked(exitButton)) {
            // close the game
            System.exit(0);
        }
        
        // debug button
        if (DEBUG && Greenfoot.mouseClicked(debugPlayButton)) {
            try {
                Greenfoot.setWorld(new Game(gameSettings, 10, null));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
