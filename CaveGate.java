import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CaveGate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaveGate extends Actor
{
    /**
     * Act - do whatever the CaveGate wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(isTouching(Olaf.class)) {Forest f = (Forest)getWorld();f.nextLevel();};
    }    
}