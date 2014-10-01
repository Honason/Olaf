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
    private int health = 3;
    private boolean animating = false;
    public Olaf(){
        animating = false;
        speed = 4;
        for(int i=0; i<6; i++) {
            sprites[i] = "mario" + (i+1) + ".png";
        }
    };

    public void act() 
    {   
        if(xWeight == 0) checkKeys();
        checkFall();
        if(outOfBounds()) deathAnimation();
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
            if(!isLeftObstacle()){
                actorRight = false;
                chgImgIn = chgImgIn - 1;
                imgNum = 2;
                if(getX() > 300) moveLeft();
            }
        } else if (Greenfoot.isKeyDown("right")) {
            if(!isRightObstacle()){
                actorRight = true;
                chgImgIn = chgImgIn - 1;
                imgNum = 1;
                if(getX() < 500) moveRight();
            }
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
    public void takeDamage(Actor troubleMaker) {
        if(getX() <= troubleMaker.getX()) knockback(-7,-7);
        else knockback(7,-7);
        if(--health == 0) {
            animating = false;
            Levels lvl = (Levels)getWorld();
            lvl.endGame();
        }
    }
    public void deathAnimation() {
        if(!animating) {
            animating = true;
            weight = -20;
            inJump = 3;
            fall();
        } else {
            animating = false;
            Levels lvl = (Levels)getWorld();
            lvl.endGame();
        }
    }

}
