import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Trap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Trap extends Enemy
{
    public Trap(String image) {
        sprites[0] = image;
    }
    
    public void act() 
    {
        chgImgIn = chgImgIn - 1;
        changeImage(0,1);
        if(isTouching(Olaf.class) && getX()-Levels.main.getX()<40 && getX()-Levels.main.getX()>-40) {
            Levels.main.takeDamage(this);
        }
    }    
}
