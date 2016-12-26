import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DisplayerTop here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class DisplayerTop extends Displayer
{
    /**
     * Initialize the DisplayerTop's image.
     * The given String is the path to an image.
     */
    public DisplayerTop(String imagePath) {
        setImage(imagePath);
    }
    
    /**
     * Alternate initialization.
     * The actor's image will be set to the specific GreenfootImage
     */
    public DisplayerTop(GreenfootImage gfImage) {
        setImage(gfImage);
    }
}
