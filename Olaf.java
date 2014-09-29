import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Olaf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Olaf extends Actor
{
    private int speed = 4;
    private int weight = 1;
    private int acceleration = 1;
    
    /**
     * Act - do whatever the Olaf wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkKeys();
        checkFall();
    }    
    
    private void checkKeys() {
        if (Greenfoot.isKeyDown("left")) {
            moveLeft();
        }
        if (Greenfoot.isKeyDown("right")) {
            moveRight();
        }
        if (Greenfoot.isKeyDown("up")) {
            jump();
        }
    }
    
    public void jump() {
        if (onGround()==true) {
            weight = -20;
            fall();
        }
    }
    
    public void checkFall(){
        if (onGround()) {
            weight = 0;
        } else {
            fall();
        }
    }
    
    public boolean onGround() {
        Actor under = getOneObjectAtOffset (0, getImage().getHeight()/2, Ground.class);
        return under != null;
    }
    
    public void fall(){
        //setLocation (getX(), getY() + weight);
        
         if (weight < 0) {
            for (int i=weight/2; i<=0; i++) {
                setLocation (getX(), getY() - 1);
                if (onGround()) {
                    break;
                }
            }
        } else {
            for (int i=0; i<=weight/2; i++) {
                setLocation (getX(), getY() + 1);
                if (onGround()) {
                    break;
                }
            }
        }
        
        weight = weight + acceleration;
    }
    public void moveRight(){
        if ()
        setLocation (getX() + speed, getY() );
    }
    public void moveLeft(){
        setLocation (getX() - speed, getY() );
    }
}
