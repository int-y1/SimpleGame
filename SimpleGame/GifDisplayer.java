import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GifDisplayer here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class GifDisplayer extends Actor
{
    
    private GifImage gif;
    
    /**
     * Initialize the displayer's image.
     * The given String is the path to a gif.
     */
    public GifDisplayer(String imagePath) {
        gif = new GifImage(imagePath);
        setImage(gif.getCurrentImage());
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // update the gif
        setImage(gif.getCurrentImage());
    }
}
