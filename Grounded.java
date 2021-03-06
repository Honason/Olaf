import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;

/**
 * Write a description of class Grounded here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grounded extends Actor
{
    public int speed = 0; // Actor's speed
    public int weight = 1; // Actor's gravity
    public int xWeight = 0; // Actor's horizontal gravity
    public int acceleration = 1;
    public int inJump = 0; // 0 = not jumping, 1 = in first jump, 2 = in second jump
    public boolean actorRight = true; // Actor heading right? False if left.

    public final int CHG_RATE = 5; // N. of loops between image change
    public int chgImgIn = 2; // Change image in X loops
    public int imgNum = 1; // Image ID
    public int chgImg = 0; // Are we supposed to change the picture? 0/1
    
    public String[] sprites = new String[20];
    public int health = 1;
    public boolean animating = false, dying;
    public int dieOn;
    public int immune = 0;
    public boolean inAttack = false;
    public int actualImage = 0;
    public int animationSpeed = 4;
    public String[] enemies = { "class EvilViking", "Bowser"}; 

    public void act() 
    {
        
    }    
    public void knockback(int xPower, int yPower) {
        weight = yPower;
        xWeight = xPower;
        fall();
    }
    
    public void changeImage(int offset, int animLenght){
        
        if (chgImgIn == 1) {
            chgImgIn = CHG_RATE; // reset countdown  
            chgImg = (chgImg + 1) % animLenght;
            //if(onGround() || inAttack == true) {
                actualImage = chgImg+offset;
                setImage(sprites[actualImage]);
            //}
        }
    }
    
    public void jump() {
        if (onGround()==true) {
            weight = -20;
            fall();
            inJump = 1;
        } else if (inJump==2) {
            weight = -20;
            fall();
            inJump = 3;
        }
    }
    public void checkFall(){
        if (onGround()) {
            weight = 0;
            xWeight = 0;
        } else {

            if(actorRight==true && inAttack == false){
                setImage(sprites[4]);
            } else if (actorRight==false && inAttack == false) {
                setImage(sprites[9]);
            }
            
            fall();
        }
    }

    public boolean onGround() {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Ground.class);
        if(under == null) under = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
        if(under == null && getWorld().getObjects(Dragon.class) != null) under = getOneObjectAtOffset(0, getImage().getHeight()/2, Dragon.class);
        if (under!=null) {
            inJump = 0;
        }
        return under != null;
    }
    public boolean outOfBounds() {
        return (getY() >= getWorld().getHeight());
    }
    public boolean isRightObstacle() {
        Actor right = getOneObjectAtOffset(20, (getImage().getHeight()/2)-1, Ground.class);
        return right != null;
    }

    public boolean isLeftObstacle() {
        Actor left = getOneObjectAtOffset(-20, (getImage().getHeight()/2)-1, Ground.class);
        return left != null;
    }

    public int getJump(){
        return inJump;
    }
    public Object getActiveLevel() {
        Forest forest = new Forest();
        return forest;
    }
    public void fall(){
        //setLocation (getX(), getY() + weight);

        if (weight < 0) {
            for (int i=weight/2; i<=0; i++) {
                setLocation(getX(), getY() - 1);
                if (onGround()) {
                    break;
                }
            }
        } else {
            for (int i=0; i<=weight/2; i++) {
                setLocation(getX(), getY() + 1);
                if (onGround()) {
                    break;
                } 
            }
        }
        if(xWeight < 0) {
            for (int i=xWeight/2; i<=0; i++) {
                if (isLeftObstacle()) {
                    break;
                } else setLocation(getX() - 1, getY());
            }
        } else if(xWeight > 0) {
            for (int i=0; i<=xWeight/2; i++) {
                if (isRightObstacle()) {
                    break;
                } else setLocation(getX() + 1, getY());
            }
        }
        if (!Greenfoot.isKeyDown("up") && inJump==1) {
            inJump = inJump + 1;
        }
        weight = weight + acceleration;
    }
    public void takeDamage(Actor troubleMaker) {
        if(this.getClass() == Olaf.class && Levels.main.immune != 0) return;
        if(getX() <= troubleMaker.getX()) knockback(-7,-7);
        else knockback(7,-7);
        if(--health <= 0) {
            deathAnimation();
        }
        if(this.getClass() == Olaf.class && health >= 0) {Levels lvl = (Levels)getWorld();lvl.updateHearts(-1);Levels.main.immune = 24;};
    }
    public void deathAnimation() {
        dying = true;
        if(this.getClass() == Dragon.class) return;
        dieOn = getY() - 80;
        weight = -20;
        xWeight = 0;
        jump();
    }
}
