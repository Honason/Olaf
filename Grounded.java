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
    
    public String[] sprites = new String[10]; 

    public int attacking = -1;  
    public int animationSpeed = 4;
    public boolean spaceLastPressed = false;
    public String[] enemies = { "Goomba", "Bowser"}; 

    public void act() 
    {
        // Add your action code here.
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
    public void knockback(int xPower, int yPower) {
        weight = xPower;
        xWeight = yPower;
        fall();
    }

    public void checkFall(){
        if (onGround()) {
            weight = 0;
            xWeight = 0;
        } else {
            if(actorRight==true){
                setImage(sprites[2]); 
            } else {
                setImage(sprites[3]);
            }
            fall();
        }
    }

    public boolean onGround() {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Ground.class);
        if (under!=null) {
            inJump = 0;
        }
        return under != null;
    }
    public boolean outOfBounds() {
        return (getY() >= getWorld().getHeight());
    }
    public boolean isRightObstacle() {
        Actor right = getOneObjectAtOffset(10, 0, Ground.class);
        return right != null;
    }

    public boolean isLeftObstacle() {
        Actor left = getOneObjectAtOffset(-10, 0, Ground.class);
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
                } else if(outOfBounds()) {
                    //if(String.valueOf(this.getClass()).equals(String.valueOf(Olaf.class))) {Greenfoot.setWorld(new Forest());
                }
            }
        }
        if(xWeight < 0) {
            for (int i=xWeight/2; i<=0; i++) {
                setLocation(getX() - 1, getY());
                if (onGround()) {
                    break;
                }
            }
        } else if(xWeight > 0) {
            for (int i=0; i<=xWeight/2; i++) {
                setLocation(getX() + 1, getY());
                if (onGround()) {
                    break;
                }
            }
        }
        if (!Greenfoot.isKeyDown("up") && inJump==1) {
            inJump = inJump + 1;
        }
        weight = weight + acceleration;
        //xWeight = xWeight + acceleration;
    }

    public void attack() {
        List<Object> objs = getIntersectingObjects( null );
         if (objs.size() > 0)  
        {  
            for(int i = 0; i < objs.size(); i++) {
                if(Arrays.asList(enemies).indexOf(objs.get(i).getClass()) > -1) ;//objs.get(i).takeDamage();
            }
            
        }
    }
    
}