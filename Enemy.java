import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Enemy extends Grounded
{
    public boolean goingRight = true;

    public void act() 
    {
        // Add your action code here.
    }    
    
    // Returns 1) if obstacle or hole is on the left, 1) if it's on the right, 0) if no obstacle
    public int inFrontOfObstacle() {
        Actor left = getOneObjectAtOffset(-10, 0, Ground.class);
        Actor leftDown = getOneObjectAtOffset(-20, 20, Ground.class);
        if (left != null || leftDown == null) return 1;
        
        Actor right = getOneObjectAtOffset(10, 0, Ground.class);
        Actor rightDown = getOneObjectAtOffset(20, 20, Ground.class);
        if (right != null || rightDown == null) return 2;
        
        return 0;
    }
    
    public void goingRight() {
        if (inFrontOfObstacle() == 1) {
            goingRight = true;
        }
        if (inFrontOfObstacle() == 2) {
            goingRight = false;
        }
    }
    
    public boolean getGoingRight() {
        return goingRight;
    }
    
    public void moveRight(){
        if(!isRightObstacle()){
            actorRight = true;
            chgImgIn = chgImgIn - 1;
            imgNum = 1;
            setLocation(getX() + speed, getY() );
        }
    }

    public void moveLeft(){
        if(!isLeftObstacle()){
            actorRight = false;
            chgImgIn = chgImgIn - 1;
            imgNum = 2;
            setLocation(getX() - speed, getY() );
        }
    }
    
    public int nearOlaf() {
        if (Levels.main.getX() > getX() && (Levels.main.getX()-getX() > 30) && (Levels.main.getY() == getY())) {
            return 1;
        }
        if (Levels.main.getX() < getX() && (getX()-Levels.main.getX() > 30) && (Levels.main.getY() == getY()) ) {
            return 2;
        }
        if (Levels.main.getY() != getY() ) {
            return 0;
        }
        return 3;
    }
    
    public int whereIsOlaf() {
        return Forest.main.getX();
    }
}
