import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dragon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dragon extends Enemy
{   
    public int startX = -1337;
    public int dHoverIn = 0;
    public int dHoverSwitch = 1;
    public int dropBallIn = 1;
    public static int dSpeed = 0;
    public Dragon() {
        speed = 1;
        for(int i=0; i<8; i++) {
            sprites[i] = "dragon" + (i+1) + ".png";
        }
    }
    
    public void act() 
    {
        if(startX == -1337) startX = getX();
        if(getX() > startX+100) dSpeed = -1;
        else if(getX() < startX-100) dSpeed = 1;
        fly();
        if(getX()-Levels.main.getX()<150 && getX()-Levels.main.getX()>-150) dropFlames();
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
        setLocation(getX()+dSpeed, getY()+dHoverSwitch);
        if(dSpeed<0){
            chgImgIn = chgImgIn - 1;
            changeImage(0,4);
        } else {
            chgImgIn = chgImgIn - 1;
            changeImage(4,4);
        }
    }
    
    public void dropFlames() {
        Levels lvl = (Levels)getWorld();
        dropBallIn--;
        if(dSpeed<0 && dropBallIn==0){
            lvl.addObject(new FireBall(),getX()-40, getY()-30);
            dropBallIn=10;
        } else if(dSpeed>0 && dropBallIn==0) {
            lvl.addObject(new FireBall(),getX()+40, getY()-30);
            dropBallIn=10;
        }
    }
    
    public static int getDragonSpeed() {
        return dSpeed;
    }
}
