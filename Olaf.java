import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Olaf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Olaf extends Actor
{
    private int speed = 4; // Olaf's speed
    private int weight = 1; // Olaf's gravity
    private int acceleration = 1;
    private int inJump = 0; // 0 = not jumping, 1 = in first jump, 2 = in second jump
    private boolean olafRight = true; // Olaf heading right? False if left.
    
    static final int CHG_RATE = 5; // N. of loops between image change
    private int chgImgIn = 2; // Change image in X loops
    private int imgNum = 1; // Image ID
    private int chgImg = 0; // Are we supposed to change the picture? 0/1
    private String[] marioimages = { "mario1.png", "mario2.png", "mario3.png", "mario4.png" };  
    
    /**
     * Act - do whatever the Olaf wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
                Forest myForest = (Forest) getWorld();
                Olaf myOlaf = myForest.getOlaf();
                myOlaf.setImage(marioimages[imgNum]);
            }
            
        }
    }    
    
    public int getChangeIn(){
        return chgImgIn;
    }
    
    private void checkKeys() {
        if (Greenfoot.isKeyDown("left")) {
            if(!isLeftObstacle()){
                olafRight = false;
                chgImgIn = chgImgIn - 1;
                imgNum = 2;
                if(getX() > 300) moveLeft();
            }
        } else if (Greenfoot.isKeyDown("right")) {
            if(!isRightObstacle()){
                olafRight = true;
                chgImgIn = chgImgIn - 1;
                imgNum = 1;
                if(getX() < 500) moveRight();
            }
        } else {
            if (Greenfoot.getKey()==null) {
                Forest myForest = (Forest) getWorld();
                Olaf myOlaf = myForest.getOlaf();
                if(olafRight==true){
                    myOlaf.setImage(marioimages[0]);
                } else {
                    myOlaf.setImage(marioimages[1]);
                }
            }
        }
        
        if (Greenfoot.isKeyDown("up")) {
            jump();
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
        } else {
            Forest myForest = (Forest) getWorld();
            Olaf myOlaf = myForest.getOlaf();
            
            if(olafRight==true){
                myOlaf.setImage(marioimages[2]); 
            } else {
                myOlaf.setImage(marioimages[3]);
            }
            fall();
        }
    }
    
    public boolean onGround() {
        Actor under = getOneObjectAtOffset (0, getImage().getHeight()/2, Ground.class);
        if (under!=null) {
            inJump = 0;
        }
        return under != null;
    }
    public boolean isRightObstacle() {
        Actor right = getOneObjectAtOffset (10, 0, Ground.class);
        return right != null;
    }
    public boolean isLeftObstacle() {
        Actor left = getOneObjectAtOffset (-10, 0, Ground.class);
        return left != null;
    }
    
    public int getJump(){
        return inJump;
    }
    
    public void fall(){
        //setLocation (getX(), getY() + weight);
        
         if (weight < 0) {
            for (int i=weight/2; i<=0; i++) {
                setLocation (getX(), getY() - 1);
                if (onGround()) {
                    break;
                }
            }
        } else {
            for (int i=0; i<=weight/2; i++) {
                setLocation (getX(), getY() + 1);
                if (onGround()) {
                    break;
                }
            }
        }
        
        if (!Greenfoot.isKeyDown("up") && inJump==1) {
            inJump = inJump + 1;
        }
        weight = weight + acceleration;
    }
    public void moveRight(){
        if(!isRightObstacle()){
            setLocation (getX() + speed, getY() );
        }
    }
    public void moveLeft(){
        if(!isLeftObstacle()){
            setLocation (getX() - speed, getY() );
        }
    }
}
