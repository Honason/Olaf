import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stone extends Bird
{
    /**
     * Act - do whatever the Stone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int acceleration = 1;
    public int counter = 0;
    public void act() 
    {
        if(++counter == 2){++acceleration;counter=0;};
        setLocation(getX(), getY() + acceleration);
        if(isTouching(Olaf.class)) Levels.main.takeDamage(this);
        if(outOfBounds()) {Levels lvl = (Levels)getWorld();lvl.removeObject(this);return;}
    }
}
