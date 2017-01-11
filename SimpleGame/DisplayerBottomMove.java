import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DisplayerBottomMove here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class DisplayerBottomMove extends DisplayerBottom
{
    /**
     * Initialize the DisplayerBottom's image.
     * The given String is the path to an image.
     */
    public DisplayerBottomMove(String imagePath) {
        super(imagePath);
    }
    
    /**
     * Alternate initialization.
     * The actor's image will be set to the specific GreenfootImage
     */
    public DisplayerBottomMove(GreenfootImage gfImage) {
        super(gfImage);
    }
    
    /**
     * Act - move the image down 1 pixel.
     */
    public void act()
    {
        // move image
        this.move(0, 1);
    }
}
