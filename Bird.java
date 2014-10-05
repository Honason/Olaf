import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bird extends Enemy
{
    /**
     * Act - do whatever the Bird wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int startX = -1337;
    public int speed = 1;
    public void act() 
    {
        if(dying) {getWorld().removeObject(this);return;};
        if(startX == -1337) startX = getX();
        if(getX() > startX+200) speed = -1;
        else if(getX() < startX-200) speed = 1;
        setLocation(getX()+speed, getY());
        if(Greenfoot.getRandomNumber(500) < 6) dropStone();
        
    }
    public void dropStone() {
        Levels lvl = (Levels)getWorld();
        lvl.addObject(new Stone(),getX(), getY());
    }
}
