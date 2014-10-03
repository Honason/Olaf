import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;

/**
 * Write a description of class Olaf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Olaf extends Grounded
{   
    
    public Olaf(){
        animating = false;
        speed = 4;
        health = 3;
        dying = false;
        for(int i=0; i<6; i++) {
            sprites[i] = "olaf" + (i+1) + ".png";
        }
    };

    public void act() 
    {   
        if(xWeight == 0 && !animating) checkKeys();
        if(dying)if(getY()<=dieOn) {Greenfoot.delay(60);Levels lvl = (Levels)getWorld();lvl.endGame();animating = false;};
        checkFall();
        if(outOfBounds()) deathAnimation();
        
    }    
    
    public void changeImage(int offset, int animLenght){
        if (chgImgIn == 1) {  
            chgImgIn = CHG_RATE; // reset countdown  
            chgImg = (chgImg + 1) % animLenght;
            if(onGround()) {
                setImage(sprites[chgImg+offset]);
            }
        }
    }

    public int getChangeIn(){
        return chgImgIn;
    }

    private void checkKeys() {
        if (Greenfoot.isKeyDown("left")) {
            moveLeft();
        } else if (Greenfoot.isKeyDown("right")) {
            moveRight();
        } else {
            if (attacking != -1)  
            {  
                /*if (dir == 0)  
                attDir = dir;  
                else  
                attDir = dir + 7;  */
                setImage(sprites[attacking / animationSpeed + 4]);
                attack();
                if (++attacking > 1 * animationSpeed) attacking = -1;
            } else if (Greenfoot.isKeyDown("space") && !spaceLastPressed) {
                spaceLastPressed = true;
                attacking = 0;
                if(getImage().getWidth() == 17) setLocation(getX() + 4, getY());
            }
            else if(spaceLastPressed && !Greenfoot.isKeyDown("space")) spaceLastPressed = false;
            else if (Greenfoot.getKey()==null) {
                if(actorRight==true){
                    setImage(sprites[0]);
                } else {
                    setImage(sprites[3]);
                }
            }
        }

        if (Greenfoot.isKeyDown("up")) {
            jump();
        }

    }

    public void moveRight(){
        if(!isRightObstacle()){
            actorRight = true;
            chgImgIn = chgImgIn - 1;
            changeImage(0,3);
            if(getX() < 500) {
                setLocation(getX() + speed, getY() );
            }
        }
    }

    public void moveLeft(){
        if(!isLeftObstacle()){
            actorRight = false;
            chgImgIn = chgImgIn - 1;
            changeImage(3,3);
            if(getX() > 300) {
                setLocation(getX() - speed, getY() );
            }
        }
    }
    public void attack() {
        List<Enemy> objs = getIntersectingObjects( Enemy.class );
         if (objs.size() > 0)  
        {  
            for(Enemy o : objs) {
                Enemy temp = o;
                temp.takeDamage(this);
            }
        }
    }
    public void deathAnimation() {
        animating = true;
        dying = true;
        dieOn = getY() - 80;
        weight = -20;
        xWeight = 0;
        jump();
    }
}
