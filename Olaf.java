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
        speed = 4;
        for(int i=0; i<6; i++) {
            sprites[i] = "mario" + (i+1) + ".png";
        }
    };

    public void act() 
    {   
        checkKeys();
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
                    setImage(sprites[1]);
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
            imgNum = 1;
            if(getX() < 500) {
                setLocation(getX() + speed, getY() );
            }
        }
    }

    public void moveLeft(){
        if(!isLeftObstacle()){
            actorRight = false;
            chgImgIn = chgImgIn - 1;
            imgNum = 2;
            if(getX() > 300) {
                setLocation(getX() - speed, getY() );
            }
        }
    }
    

}
