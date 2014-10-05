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
        for(int i=0; i<20; i++) {
            sprites[i] = "olaf" + (i+1) + ".png";
        }
    };

    public void act() 
    {   
        if(xWeight == 0 && !animating) checkKeys();
        if(dying)if(getY()<=dieOn) {Greenfoot.delay(60);Levels lvl = (Levels)getWorld();lvl.endGame();animating = false;};
        checkFall();
        if(outOfBounds()) {
            getWorld().removeObject(Levels.heart3);
            getWorld().removeObject(Levels.heart2);
            getWorld().removeObject(Levels.heart1);
            deathAnimation();
        }
        if(immune>0){
            immune--;
        }
    }    

    public int getChangeIn(){
        return chgImgIn;
    }

    private void checkKeys() {
       if(Greenfoot.isKeyDown("space")) {Levels.axeSound();}
        
       if ((Greenfoot.isKeyDown("space") && inAttack == false) || inAttack == true) {
           inAttack = true;
           attack();
           chgImgIn = chgImgIn - 1;
           if(actorRight) {
               changeImage(10,5);
               if (actualImage == 10) {
                   inAttack = false;
               }
           } else {
               changeImage(15,5);
               if (actualImage == 15) {
                   inAttack = false;
               }
           }
       }
       
       if (Greenfoot.isKeyDown("left")) {
            moveLeft();
       } else if (Greenfoot.isKeyDown("right")) {
            moveRight();
       }  else if (Greenfoot.getKey()==null) {
           if (inAttack == false) {
               if(actorRight==true){
                   setImage(sprites[0]);
               } else {
                   setImage(sprites[5]);
               }
           }
       } 
       if (Greenfoot.isKeyDown("up")) {
           jump();
       }
    }

    public void moveRight(){
        if(!isRightObstacle() && !(onGround() && inAttack==true)){
            actorRight = true;
            if(onGround()){
                chgImgIn = chgImgIn - 1;
                changeImage(1,3);
            }
            if(getX() < 500) {
                setLocation(getX() + speed, getY() );
            }
        }
    }

    public void moveLeft(){
        if(!isLeftObstacle() && !(onGround() && inAttack==true)){
            actorRight = false;
            if(onGround()){
                chgImgIn = chgImgIn - 1;
                changeImage(6,3);
            }
            if(getX() > 300) {
                setLocation(getX() - speed, getY() );
            }
        }
    }
    public void attack() {
        List<Enemy> objs = getObjectsInRange(68, Enemy.class );
        if (objs.size() > 0) {  
            for(Enemy o : objs) {
                Enemy temp = o;
                if(actorRight)if(temp.getX() > getX())temp.takeDamage(this);
                if(!actorRight)if(getX() > temp.getX()) temp.takeDamage(this);
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
