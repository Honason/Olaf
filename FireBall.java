import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FireBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireBall extends Dragon
{
    public int acceleration = 1;
    public int counter = 0;
    public FireBall() {
        for(int i=0; i<3; i++) {
            sprites[i] = "flame" + (i+1) + ".png";
        }
    }
    
    public void act() 
    {
        chgImgIn = chgImgIn - 1;
        changeImage(0,3);
        if(++counter == 4){++acceleration;counter=0;};
        int drSpeed = Dragon.getDragonSpeed();
        if(drSpeed == -1){
            setLocation(getX()-2, getY() + acceleration);
        } else if(drSpeed == 1) {
            setLocation(getX()+2, getY() + acceleration);
        }
        if(isTouching(Olaf.class) && getX()-Levels.main.getX()<20 && getX()-Levels.main.getX()>-20) {
            Levels.main.takeDamage(this);
        }
        if(outOfBounds() || isTouching(Ground.class)) {Levels lvl = (Levels)getWorld();lvl.removeObject(this);return;}
    } 
}
