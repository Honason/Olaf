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
        for(int i=0; i<6; i++) {
            sprites[i] = "evilmario" + (i+1) + ".png";
        }
    };

    public void act() 
    {   
        if(dying)if(getY()<=dieOn) {getWorld().removeObject(this);return;};
        ai();
        checkFall();
        
        if (chgImgIn == 1) {  
            chgImgIn = CHG_RATE; // reset countdown  

            chgImg = (chgImg + 1) % 2;
            if(chgImg == 0) {
                imgNum++;
            } else {
                imgNum--;
            }    

            if(onGround()) {
                setImage(sprites[imgNum]);
            }

        }
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
        if(isTouching(Olaf.class)) Levels.main.takeDamage(this);
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
            moveRight();
        }
        if(nearOlaf() == 2) {
            moveLeft();
        }
    }

    

}
