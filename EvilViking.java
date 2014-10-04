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
        int oX = Levels.main.getX();
        int eX = getX();
        
        if(oX > eX) {
            if(isTouching(Olaf.class)&&(oX-40<=eX+20)) inAttack=true;
        } else {
            if(isTouching(Olaf.class)&&(eX-20<=oX+40)) inAttack=true;
        }
        
        if(inAttack==true){
            
            if(oX > eX) { //Attack right
                chgImgIn = chgImgIn - 1;
                changeImage(11,3);
                if (actualImage == 13) {
                    inAttack = false;
                    if(oX - 20 <= eX + 60) {
                        Levels.main.takeDamage(this);
                    }
                }
            } else { //Attack Left
                chgImgIn = chgImgIn - 1;
                changeImage(15,3);
                if (actualImage == 17) {
                    inAttack = false;
                    if(eX - 60 <= oX + 20) {
                        Levels.main.takeDamage(this);
                    }
                }
            }
        }
        else if(nearOlaf() == 0) { 
            goingRight();
            if(goingRight==true){
                moveRight();
            } else {
                moveLeft();
            }
        }
        else if(nearOlaf() == 1) {
            goingRight = true;
            if (inFrontOfObstacle()==2 && goingRight==true) {
                moveRight();
                jump();
            } else if (inFrontOfObstacle()==20 && goingRight==true && onGround()) {
                knockback(3,-20);
            } else {
                moveRight();
            }
        }
        else if(nearOlaf() == 2) {
            goingRight = false;
            if (inFrontOfObstacle()==1 && goingRight==false) {
                moveLeft();
                jump();
            } else if (inFrontOfObstacle()==10 && goingRight==false && onGround()) {
                knockback(-3,-20);
            } else {
                moveLeft();
            }
        }
    }
}
