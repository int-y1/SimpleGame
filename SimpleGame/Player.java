import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class Player extends Actor
{
    
    // player variables
    protected Game game;
    private final int SPEED_NORMAL = 5;
    private final int SPEED_SLOW = 2;
    private final int INVINCIBILITY_FRAMES = 50;
    private int vx = 0;                             // x velocity of the player
    private int vy = 0;                             // y velocity of the player
    
    // shooting mechanic variables
    private int SHOOT_COOLDOWN = 20;
    private int shootTime = 0;
    private double BULLET_RATIO = 0.5;              // add this multiplier of the player's velocity
    private double BULLET_VY = -5;                  // default bullet speed
    
    public Player(Game g)
    {
        // get this game
        game = g;
        
        setImage("Isaac/forward1.png");
    }
    
    
    // invincibility methods
    private int noHitFrames = 0;
    
    public boolean invincible()
    {
        return noHitFrames > 0;
    }
    
    public void makeInvincible()
    {
        noHitFrames = INVINCIBILITY_FRAMES;
    }
    
    
    // methods for coordinate geometry
    
    public int getDist(int x, int y)
    {
        // simple distance formula
        return (int) Math.sqrt(Math.pow(x-getX(), 2) + Math.pow(y-getY(), 2));
    }
    
    public double getAngle(int x, int y)
    {
        // return angle from (x,y) to here, in radians
        return Math.atan2(getY()-y, getX()-x);
    }
    
    public void displacePlayer(int dx, int dy)
    {
        int newX = getX()+dx;
        int newY = getY()+dy;
        
        if (newX<32)  newX=32;
        if (newX>480) newX=480;
        if (newY<32)  newY=32;
        if (newY>464) newY=464;
        
        setLocation(newX, newY);
    }
    
    
    private void calculateVelocity()
    {
        // calculate player's velocity
        int moveSpeed;
        if (game.keyS()) moveSpeed = SPEED_SLOW;
        else moveSpeed = SPEED_NORMAL;
        // calculate vx from left/right keys
        vx = 0;
        if (game.keyLeft()) {
            vx -= moveSpeed;
        }
        if (game.keyRight()) {
            vx += moveSpeed;
        }
        // calculate vy from up/down keys
        vy = 0;
        if (game.keyUp()) {
            vy -= moveSpeed;
        }
        if (game.keyDown()) {
            vy += moveSpeed;
        }
    }
    
    int walkingCycle = 0;
    int imgNum[] = new int[]{1,2,1,3};
    private void updateAnimation()
    {
        // count if player is walking
        if (vx!=0 || vy!=0) {
            walkingCycle += Math.abs(vx);
            walkingCycle += Math.abs(vy);
        }
        else {
            // reset walking
            walkingCycle = 0;
        }
        
        // change image
        int imgIndex = (walkingCycle/20)%4;
        if (shootTime > 0) {
            setImage(String.format("Isaac/backwards%d.png", imgNum[imgIndex]));
        }
        else {
            setImage(String.format("Isaac/forward%d.png", imgNum[imgIndex]));
        }
        
        // do transparency
        if (noHitFrames == 0) {
            getImage().setTransparency(255);
        }
        else {
            // transparency will be a number between 64 and 128
            getImage().setTransparency(128 - (noHitFrames*64)/INVINCIBILITY_FRAMES);
        }
        setImage(getImage());
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // check if dead
        if (game.isFading()) {
            return;
        }
        
        // do movement
        calculateVelocity();
        displacePlayer(vx, vy);
        
        // do animation sprites
        updateAnimation();
        
        // shooting mechanic
        if (shootTime > 0) shootTime--;
        if (game.keyD()) {
            if (shootTime == 0) {
                // shoot once
                game.addObject(
                    new PlayerBullet(game, vx*BULLET_RATIO, vy*BULLET_RATIO+BULLET_VY),
                    getX(),
                    getY());
                // set cooldown
                shootTime = SHOOT_COOLDOWN;
            }
        }
        
        // invincibility frame transparency
        if (noHitFrames > 0) {
            // count down
            noHitFrames--;
        }
    }
}
