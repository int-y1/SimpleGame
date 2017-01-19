import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * The methods in this class handles the user's input and possible replaying of files.
 * The variables in this class are NOT protected.
 * It is up to the other classes to declare this object as private.
 * 
 * This interface can be extended to view past replays.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class InputInterface
{
    
    // constants
    public final boolean REPLAY;
    public final boolean readD;
    
    // keystrokes, either from the player or a string
    private Queue<Character> keystrokes = new LinkedList<Character>();
    
    // this is the order of the key inputs
    public boolean right;
    public boolean up;
    public boolean left;
    public boolean down;
    public boolean s;
    public boolean d;
    
    /**
     * Initialize the InputInterface object.
     * If replayKeys is not empty/null, use the keystrokes in the string and ignore the user.
     */
    public InputInterface(String replayKeys, boolean readD) throws IOException {
        // set variable readD
        this.readD = readD;
        
        if (replayKeys == null || replayKeys == "") {
            REPLAY = false;
        }
        else {
            System.out.printf("Reading replay of size: %d\n", replayKeys.length());
            REPLAY = true;
            // get the file input
            InputStream stream = getClass().getResourceAsStream(replayKeys);
            InputStreamReader in = new InputStreamReader(stream);
            // read all bytes
            int letter = in.read();
            while (letter != -1) {
                System.out.println(letter);
                keystrokes.add((char) letter);
                letter = in.read();
            }
            System.out.printf("Done Reading\n");
        }
    }
    
    /**
     * Get the next set of inputs.
     */
    public void getNewStrokes() {
        if (REPLAY) {
            readFileStroke();
        }
        else {
            readUserStroke();
        }
    }
    
    /**
     * Read the set of inputs from the user.
     */
    public void readUserStroke() {
        // replace the keys
        right = Greenfoot.isKeyDown("right");
        up    = Greenfoot.isKeyDown("up");
        left  = Greenfoot.isKeyDown("left");
        down  = Greenfoot.isKeyDown("down");
        s     = Greenfoot.isKeyDown("s");
        d     = readD && Greenfoot.isKeyDown("d");
        
        // encode A
        // get the corresponding number
        // note that the bits are inserted backwards
        // the least significant bits should be last in the number
        int num = 0;
        num = (num*2 + (d? 1:0));
        num = (num*2 + (s? 1:0));
        num = (num*2 + (down? 1:0));
        num = (num*2 + (left? 1:0));
        num = (num*2 + (up? 1:0));
        num = (num*2 + (right? 1:0));
        
        // encode B
        // ASCII values 33-126 are good non-whitespace characters for the file
        // only 33-48 are used right now
        num += 33;
        keystrokes.add((char) num);
    }
    
    /**
     * Read the set of inputs from the replay string.
     */
    public void readFileStroke() {
        // get a keystroke
        int num;
        if (keystrokes.isEmpty()) {
            num = 0;
        }
        else {
            // decode B
            num = (int) keystrokes.poll() - 33;
        }
        
        // decode A
        right = (num%2 == 1);
        num /= 2;
        up = (num%2 == 1);
        num /= 2;
        left = (num%2 == 1);
        num /= 2;
        down = (num%2 == 1);
        num /= 2;
        s = (num%2 == 1);
        num /= 2;
        d = (num%2 == 1);
    }
    
    /**
     * Return true if this is a replay, otherwise false.
     */
    public boolean isReplay() {
        return REPLAY;
    }
    
    /**
     * Return the String that represents the user's replay.
     * It is assumed that the user was playing the level.
     */
    public String getReplay() throws IOException {
        // assume that REPLAY is false
        
        // get keystrokes as a char array
        char[] keyArr = new char[keystrokes.size()];
        for (int i=0; !keystrokes.isEmpty(); i++) {
            keyArr[i] = keystrokes.poll();
        }
        
        // return the String
        return new String(keyArr);
    }
}
