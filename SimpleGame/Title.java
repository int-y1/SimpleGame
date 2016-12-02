import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Title here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Title extends World
{
    
    private final String PLAY_BUTTON_PATH = "Closed Door.png";
    
    private Button playButton;
    
    /**
     * Constructor for objects of class Title.
     * 
     */
    public Title()
    {
        // Create a new world with 512x512 cells with a cell size of 1x1 pixels.
        super(512, 512, 1);
        
        // make the buttons
        playButton = new Button(PLAY_BUTTON_PATH);
        this.addObject(playButton, 256, 256);
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(playButton)) {
            System.out.println("You lose!");
        }
    }
}
