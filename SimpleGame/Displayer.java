import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Displayer here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Displayer extends Actor
{
    /**
     * Move the image.
     */
    public void move(int dx, int dy) {
        setLocation(getX()+dx, getY()+dy);
    }
    
    /**
     * Initialize the displayer's image.
     * The given String is the path to an image.
     */
    public Displayer(String imagePath) {
        setImage(imagePath);
    }
    
    /**
     * Alternate initialization.
     * The actor's image will be set to the specific GreenfootImage
     */
    public Displayer(GreenfootImage gfImage) {
        setImage(gfImage);
    }
}
