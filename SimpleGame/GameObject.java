import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class GameObject here.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class GameObject extends Actor
{
    
    protected Game game;
    protected int timer=0;          // number of frames since this object is created
    
    /**
     * Move the image.
     */
    public void move(int dx, int dy) {
        setLocation(getX()+dx, getY()+dy);
    }
    
    protected void removeFast() {
        game.removeObject(this);
    }
    
    public boolean outOfBounds(int tolerance)
    {
        // check the four edges
        if (getX() < -tolerance)    return true;
        if (getX() > 512+tolerance) return true;
        if (getY() < -tolerance)    return true;
        if (getY() > 496+tolerance) return true;
        
        // this object is within bounds
        return false;
    }
    
    protected ArrayList<String> makeArrayList(int[] arr)
    {
        ArrayList<String> al = new ArrayList<String>();
        for (int i : arr) al.add(Integer.toString(i));
        return al;
    }
}
