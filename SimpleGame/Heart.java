import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Heart here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Heart extends Actor
{
    
    private final String HEART_0_PATH = "emptyHeart.png";
    private final String HEART_1_PATH = "halfHeart.png";
    private final String HEART_2_PATH = "fullHeart.png";
    
    /**
     * Initialize the Heart's image.
     */
    public Heart() {
        setImage(HEART_0_PATH);
    }
    
    public void setHeartFullness(int fullness) {
        if (fullness == 2) {
            setImage(HEART_2_PATH);
        }
        else if (fullness == 1) {
            setImage(HEART_1_PATH);
        }
        else {
            setImage(HEART_0_PATH);
        }
    }
}
