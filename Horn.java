import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Horn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Horn extends Actor
{
    public int dHoverIn = 0;
    public int dHoverSwitch = 1;
    public void act() 
    {
        fly();
    }    
    
    public void fly() {
        dHoverIn = (dHoverIn + 1) % 40;
        if(dHoverIn==39) {
            if(dHoverSwitch==1) {
                dHoverSwitch = -1;
            } else {
                dHoverSwitch = 1;
            }
        }
        setLocation(getX(), getY()+dHoverSwitch);
    }
}
