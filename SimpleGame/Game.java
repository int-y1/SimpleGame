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
 * @version a0.1
 */
public class Game extends World
{
    
    private final int MAX_LIVES = 10;
    
    private GameSettings gameSettings;
    private final int LEVEL;
    private final int DIFFICULTY;
    private final String LEVEL_PATH= "/levels/level%d.txt";
    
    private final InputInterface USER_INPUT;
    private final LevelReader LR;
    private final BackgroundHelper BH;
    private final LevelInfoDisplayer LID;
    private boolean bgFading = false;
    
    // player variables
    private Player player;
    private int lives;
    private final int FADE_DURATION = 200;      // duration of any fading animation
    private int fadeAnimation=FADE_DURATION;
    
    /**
     * Constructor for objects of class Game.
     * This method is used for playing a level.
     * 
     */
    public Game(GameSettings gs, int lives, String replayPath) throws IOException
    {
        // screen size is 512x512 pixels
        super(512, 512, 1, false);
        
        // get info from game settings
        gameSettings = gs;
        LEVEL = gs.getLevel();
        DIFFICULTY = gs.getDifficulty();
        this.lives = lives;
        
        // set paint order for the game
        // earlier class is drawn on a later class
        setPaintOrder(DisplayerTop.class,
                      Heart.class,
                      Player.class,
                      BackgroundFade.class,
                      PlayerBullet.class,
                      Enemy.class,
                      DisplayerMiddle.class,
                      DisplayerBottom.class);
        
        // get the level file into a Scanner
        InputStream stream = getClass().getResourceAsStream(String.format(LEVEL_PATH, LEVEL));
        if (stream == null) {
            // level does not exist
            // player completed the game
            Greenfoot.setWorld(new WinningScreen());
            // set remaining variables to null
            LR = null;
            BH = null;
            USER_INPUT = null;
            LID = null;
            player = null;
            return;
        }
        else {
            // check highest level reached
        }
        Scanner sc = new Scanner(stream);
        // parse the first line
        String[] tokens = sc.nextLine().split("\\s+");      // first line from Scanner
        // send Scanner to another class for further processing
        LR = new LevelReader(this, sc);
        
        // initialize remaining helpers
        BH = new BackgroundHelper(this, LEVEL, Integer.parseInt(tokens[0]), tokens[1]);
        USER_INPUT = new InputInterface(replayPath, DIFFICULTY == 1);
        LID = new LevelInfoDisplayer(this, lives, MAX_LIVES, 0);
        
        // initialize music
        gameSettings.setMusic(tokens[2]);
        
        // initialize player
        player = new Player(this);
        this.addObject(player, 256, 384);
        if (lives > MAX_LIVES) lives = MAX_LIVES;
        
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
        // send to player
        return player.getDist(x, y);
    }
    
    public double getPlayerAngle(int x, int y)
    {
        // send to player
        return player.getAngle(x, y);
    }
    
    public void playerLoseLife()
    {
        // check if invincible
        if (player.invincible() || isFading()) {
            return;
        }
        
        // make the player lose a life
        lives--;
        LID.setLives(lives);
        
        // check if game over
        if (playerDead()) {
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
        // stop game if player is not initialized
        if (player == null) {
            return;
        }
        
        if (bgFading) {
            // check if animation is done
            if (fadeAnimation == 0) {
                if (playerDead() || USER_INPUT.isReplay()) {
                    // player goes back to the title screen
                    Greenfoot.setWorld(new Title(gameSettings));
                }
                else {
                    // player advances to a new level
                    try {
                        // calculate number of new lives
                        int newLives;
                        if (DIFFICULTY == 1) {
                            if (LEVEL%2 == 0) newLives = 0;
                            else newLives = 1;
                        }
                        else newLives = 1;
                        
                        // update game level
                        gameSettings.setLevel(gameSettings.getLevel()+1);
                        
                        // go to next level
                        Greenfoot.setWorld(new Game(gameSettings, lives+newLives, null));
                    }
                    catch (IOException e) {
                        Greenfoot.setWorld(new Title(gameSettings));
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
            if (LID.hitExit()) {
                // kill the player
                lives=0;
                player.setImage("Isaac/death1.png");
                bgFading = true;
                return;
            }
            
            // check if level is done
            if (BH.isDoorOpen()) {
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
