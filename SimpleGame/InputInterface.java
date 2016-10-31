import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * The methods in this class handles the user's input and possible replaying of files.
 * The variables in this class are NOT protected.
 * It is up to the other classes to declare this object as private.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InputInterface
{
    private final String REPLAY_FOLDER = "replays";
    public final boolean REPLAY;
    
    private Queue<Character> keystrokes = new LinkedList<Character>();
    
    public boolean right;
    public boolean up;
    public boolean left;
    public boolean down;
    
    public InputInterface() {
        REPLAY = false;
        // try creating the replayFolder
        new File(REPLAY_FOLDER).mkdirs();
    }
    
    public InputInterface(String replayFile) throws IOException {
        REPLAY = true;
        // get the file input
        File tempFile = new File(REPLAY_FOLDER, replayFile);
        FileReader in = new FileReader(tempFile);
        // read all bytes
        int letter = in.read();
        while (letter!=-1) {
            System.out.println(letter);
            keystrokes.add((char) letter);
            letter = in.read();
        }
        System.out.println(tempFile.getPath());
    }
    
    public void getNewStrokes() {
        if (REPLAY) {
            addStroke();
        }
        else {
            putStroke();
        }
    }
    
    public void addStroke() {
        // replace the keys
        right=Greenfoot.isKeyDown("right");
        up=Greenfoot.isKeyDown("up");
        left=Greenfoot.isKeyDown("left");
        down=Greenfoot.isKeyDown("down");
        // get the corresponding number
        // note that the bits are inserted backwards
        // the least significant bits should be last in the number
        int num = 0;
        num = ((num << 1) | (down? 1 : 0));
        num = ((num << 1) | (left? 1 : 0));
        num = ((num << 1) | (up? 1 : 0));
        num = ((num << 1) | (right? 1 : 0));
        // ASCII values 33-126 are good non-whitespace characters for the file
        // only 33-48 are used right now
        keystrokes.add((char) (num+33));
    }
    
    public void putStroke() {
        // remove a stroke from the queue
    }
}
