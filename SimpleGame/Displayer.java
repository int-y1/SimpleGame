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
}
