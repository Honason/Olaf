import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;

/**
 * Write a description of class Olaf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EvilViking extends Enemy
{   
    public EvilViking(){
        speed = 2;
        for(int i=0; i<18; i++) {
            sprites[i] = "evilViking" + (i+1) + ".png";
        }
    };
    
    public int getChgImgIn() {return chgImgIn;}
    public int getChgImg() {return chgImg;}

    public void act() 
    {   
        if(dying)if(getY()<=dieOn) {getWorld().removeObject(this);return;};
        ai();
        checkFall();
    }    

    public int getChangeIn(){
        return chgImgIn;
    }
    public int getSpeed() {
        if(goingRight)return speed;
        else return -speed;
    }

    private void ai() {
        //jump();
        if(isTouching(Olaf.class)){
            int oX = Levels.main.getX();
            int eX = getX();
            if(oX > eX) { //Attack right
                if(oX - 20 <= eX + 40) Levels.main.takeDamage(this);
            } else { //Attack Left
                if(eX - 40 <= oX + 20) Levels.main.takeDamage(this);
            }
        }
        //if(isTouching(Olaf.class)) Levels.main.takeDamage(this);
        if(nearOlaf() == 0) { 
            goingRight();
            if(goingRight==true){
                moveRight();
            }
            if(goingRight==false){
                moveLeft();
            }
        }
        
        if(nearOlaf() == 1) {
            if (inFrontOfObstacle()==2 && goingRight==true) {
                moveRight();
                jump();
            } else if (inFrontOfObstacle()==20 && goingRight==true && onGround()) {
                knockback(3,-20);
            }
            moveRight();
        }
        if(nearOlaf() == 2) {
            if (inFrontOfObstacle()==1 && goingRight==false) {
                moveLeft();
                jump();
            } else if (inFrontOfObstacle()==10 && goingRight==false && onGround()) {
                knockback(-3,-20);
            }
            moveLeft();
        }
        if(nearOlaf() == 3) {
            if (inFrontOfObstacle()==1 && goingRight==false) {
                moveLeft();
                jump();
            } else if (inFrontOfObstacle()==2 && goingRight==true) {
                moveRight();
                jump();
            } else if (inFrontOfObstacle()==10 && goingRight==false && onGround()) {
                knockback(-5,-20);
            } else if (inFrontOfObstacle()==20 && goingRight==true && onGround()) {
                knockback(5,-20);
            } else {
                goingRight();
                if(goingRight==true){
                    moveRight();
                }
                if(goingRight==false){
                    moveLeft();
                }
            }
            
        }
    }
}
