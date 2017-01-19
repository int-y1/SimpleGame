import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Maintains the background.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class BackgroundHelper  
{
    
    private Game game;
    private final int LEVEL;
    private final String BACKGROUND_FILE_PATH = "levels/backgrounds.txt";
    private final String mapPrefix;
    
    // background variables
    private int beginningCountdown = 100;   // length of rest at the level's start
    private int scrollTime;
    private int bgY;
    private int movements = 0;
    
    private DisplayerBottom[] bgArray;
    private DisplayerMiddle wallBegin;
    private DisplayerMiddle wallEnd;
    private BackgroundFade bTint;
    
    // door variable
    private DisplayerMiddle endDoor;
    private boolean doorOpen = false;
    
    /**
     * Constructor for objects of class BackgroundHelper.
     */
    public BackgroundHelper(Game g, int level, int mapLength, String mapPrefix)
    {
        // initialize
        game=g;
        LEVEL=level;
        scrollTime=mapLength;
        this.mapPrefix = mapPrefix;
        GreenfootImage tempImage;   // temporary image
        
        // initialize starting wall
        tempImage = new GreenfootImage(String.format("%sa.png", mapPrefix));
        wallBegin = new DisplayerMiddle(tempImage);
        game.addObject(wallBegin, 256, 512 - wallBegin.getImage().getHeight()/2);
        
        // initialize ending wall
        tempImage = new GreenfootImage(String.format("%sb.png", mapPrefix));
        wallEnd = new DisplayerMiddle(tempImage);
        game.addObject(wallEnd, 256, wallEnd.getImage().getHeight()/2 - mapLength);
        
        // initialize background displayers
        tempImage = new GreenfootImage(String.format("%s.png", mapPrefix));
        bgY=tempImage.getHeight();
        bgArray = new DisplayerBottom[(512/bgY)+2];
        for (int i=0; i<bgArray.length; i++) {
            bgArray[i] = new DisplayerBottom(tempImage);
            game.addObject(bgArray[i], 256, bgY*(i-1));
        }
        
        // initialize the background tint
        bTint = new BackgroundFade();
        game.addObject(bTint, 256, 256);
        
        // initialize the door
        tempImage = new GreenfootImage(String.format("%sc.png", mapPrefix));
        endDoor = new DisplayerMiddle(tempImage);
        game.addObject(endDoor, 256, endDoor.getImage().getHeight()/4 - mapLength);
    }
    
    /**
     * Move the background by a little.
     */
    public void tickBackground()
    {
        // do countdowns
        if (beginningCountdown > 0) {
            // still at the beginning
            beginningCountdown--;
            return;
        }
        
        if (scrollTime == 0) {
            // level is done scrolling
            return;
        }
        scrollTime--;
        
        // have the shift background a little
        // move walls and door
        wallBegin.move(0,1);
        wallEnd.move(0,1);
        endDoor.move(0,1);
        
        // move the background a little down
        for (int i=0; i<bgArray.length; i++) {
            bgArray[i].move(0,1);
        }
        
        // check if enough movements have occurred
        // so background tiles don't run out
        movements++;
        if (movements == bgY) {
            // move the background to its original place
            for (int i=0; i<bgArray.length; i++) {
                bgArray[i].move(0,-bgY);
            }
            movements=0;
        }
        
        // check if level has just stopped scrolling
        if (atLevelEnd()) {
            // open the door now
            openDoor();
        }
    }
    
    /**
     * Return true if the level has finished scrolling, otherwise false.
     */
    public boolean atLevelEnd() {
        return scrollTime == 0;
    }
    
    /**
     * Try to open the door.
     * If the level is scrolling or the door is already open, this method does nothing.
     */
    public void openDoor() {
        // door should open once at the end
        if (!atLevelEnd() || doorOpen) {
            return;
        }
        
        // set the image
        endDoor.setImage(String.format("%sd.png", mapPrefix));
        
        // set the variable
        doorOpen = true;
    }
    
    /**
     * Return true if the door is open, otherwise false.
     */
    public boolean isDoorOpen() {
        return doorOpen;
    }
    
    /**
     * Tint the screen by this transparency.
     */
    public void fadeScreen(int alpha)
    {
        bTint.changeFade(alpha);
    }
}
