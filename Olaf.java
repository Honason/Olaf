import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Olaf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Olaf extends Actor
{
    private int speed = 10;
    private int weight = 5;
    
    /**
     * Act - do whatever the Olaf wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkKeys();
        fall();
    }    
    
    private void checkKeys() {
        if (Greenfoot.isKeyDown("left")) {
            moveLeft();
        }
        if (Greenfoot.isKeyDown("right")) {
            moveRight();
        }
    }
    
    public void fall(){
        setLocation (getX(), getY() + weight);
    }
    public void moveRight(){
        setLocation (getX() + speed, getY() );
    }
    public void moveLeft(){
        setLocation (getX() - speed, getY() );
    }
}
