import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DisplayerBottom here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class DisplayerBottom extends Displayer
{
    /**
     * Initialize the DisplayerBottom's image.
     * The given String is the path to an image.
     */
    public DisplayerBottom(String imagePath) {
        setImage(imagePath);
    }
    
    /**
     * Alternate initialization.
     * The actor's image will be set to the specific GreenfootImage
     */
    public DisplayerBottom(GreenfootImage gfImage) {
        setImage(gfImage);
    }
}
