import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

import java.util.Scanner;
import java.io.InputStream;
import java.io.IOException;

/**
 * Write a description of class Game here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Game extends World
{
    
    private final int MAX_LEVEL = 4;
    private final String LEVEL_PATH= "/levels/level%d.txt";
    
    private final int LEVEL;
    
    private final InputInterface USER_INPUT;
    private final LevelReader LR;
    private final BackgroundHelper BH;
    private boolean bgFading = false;
    
    // player variables
    private Player player;
    private int lives = 3;
    private final int FADE_DURATION = 200;      // duration of any fading animation
    private int fadeAnimation=FADE_DURATION;
    
    /**
     * Constructor for objects of class Game.
     * This method is used for playing a level.
     * 
     */
    public Game(int levelNumber, String replayPath) throws IOException
    {
        // screen size is 512x512 pixels
        super(512, 512, 1, false);
        LEVEL = levelNumber;
        
        // set paint order for the game
        // earlier class is drawn on a later class
        setPaintOrder(DisplayerTop.class,
                      Player.class,
                      BackgroundFade.class,
                      Enemy.class,
                      DisplayerMiddle.class,
                      DisplayerBottom.class);
        
        // initialize player-related variables
        USER_INPUT = new InputInterface(replayPath);
        player = new Player(this);
        this.addObject(player, 256, 384);
        
        // get the level file into a Scanner
        InputStream stream = getClass().getResourceAsStream(String.format(LEVEL_PATH, levelNumber));
        Scanner sc = new Scanner(stream);
        // parse the first line
        String[] tokens = sc.nextLine().split("\\s+");      // first line from Scanner
        // send Scanner to another class for furtherprocessing
        LR = new LevelReader(this, sc);
        
        // add background
        BH = new BackgroundHelper(this, LEVEL, Integer.parseInt(tokens[0]));
        
    }
    
    
    // user input methods
    
    public boolean keyRight()
    {
        return USER_INPUT.right;
    }
    
    public boolean keyUp()
    {
        return USER_INPUT.up;
    }
    
    public boolean keyLeft()
    {
        return USER_INPUT.left;
    }
    
    public boolean keyDown()
    {
        return USER_INPUT.down;
    }
    
    public boolean keyS()
    {
        return USER_INPUT.s;
    }
    
    public boolean keyD()
    {
        return USER_INPUT.d;
    }
    
    
    // player methods
    
    public int getPlayerDist(int x, int y)
    {
        // simple distance formula
        return player.getDist(x, y);
    }
    
    public void playerLoseLife()
    {
        // check if invincible
        if (player.invincible()) {
            return;
        }
        
        // make the player lose a life
        lives--;
        
        // check if game over
        if (lives==0) {
            player.setImage("Isaac/death1.png");
            bgFading = true;
        }
        else {
            // make the player invincible for a while
            player.makeInvincible();
        }
    }
    
    public boolean playerDead()
    {
        return lives<=0;
    }
    
    public boolean isFading()
    {
        return bgFading;
    }
    
    public void act()
    {
        if (bgFading) {
            // check if animation is done
            if (fadeAnimation == 0) {
                if (playerDead() || USER_INPUT.isReplay()) {
                    // player goes back to the title screen
                    Greenfoot.setWorld(new Title());
                }
                else if (LEVEL == MAX_LEVEL) {
                    // player completed the game
                    Greenfoot.setWorld(new WinningScreen());
                }
                else {
                    // player advances to a new level
                    try {
                        Greenfoot.setWorld(new Game(LEVEL+1, null));
                    }
                    catch (IOException e) {
                        Greenfoot.setWorld(new Title());
                    }
                }
            }
            fadeAnimation--;
            
            if (playerDead()) {
                // player is dead
                
                // do player image
                if (fadeAnimation == (FADE_DURATION*5)/6) {
                    player.setImage("Isaac/death2.png");
                }
                if (fadeAnimation == (FADE_DURATION*4)/6) {
                    player.setImage("Isaac/death3.png");
                }
            }
            else {
                // player has completed the level
                for (Object e : getObjects(Enemy.class)) {
                    ((Enemy)e).kill();
                }
            }
            
            // do background fading
            BH.fadeScreen(((FADE_DURATION-fadeAnimation)*255) / FADE_DURATION);
        }
        else {
            // player is not dead
            // check exit button
            if (BH.hitExit()) {
                // kill the player
                lives=0;
                player.setImage("Isaac/death1.png");
                bgFading = true;
                return;
            }
            
            // check if level is done
            if (BH.atLevelEnd()) {
                // check if player is at the end
                if (256-32 <= player.getX() && player.getX() <= 256+32) {
                    if (player.getY() == 32) {
                        // player is at the door
                        bgFading = true;
                    }
                }
            }
            
            // update the private classes
            USER_INPUT.getNewStrokes();
            LR.tick();
            BH.tickBackground();
        }
    }
}
