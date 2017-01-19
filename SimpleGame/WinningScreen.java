import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The WinningScreen class tells the player that the game is over.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class WinningScreen extends World
{
    
    GameSettings gameSettings;
    
    /**
     * Constructor for objects of class WinningScreen.
     * 
     */
    public WinningScreen(GameSettings gs)
    {    
        // set screen to 512x512 with 1x1 pixels
        super(512, 512, 1);
        
        // get info from game settings
        gameSettings = gs;
        
        // actually, this World is incomplete
        // send the player to the credits
        Greenfoot.setWorld(new Credits(gs));
    }
}
