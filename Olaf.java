import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Olaf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Olaf extends Actor
{
    private int speed = 4;
    private int weight = 1;
    private int acceleration = 1;
    private int inJump = 0;
    
    static final int CHG_RATE = 8;
    private int chgImgIn = 2;   
    private int imgNum = 1;
    private int chgImg = 0; 
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
            
            Forest myForest = (Forest) getWorld();
            Olaf myOlaf = myForest.getOlaf();
            myOlaf.setImage(marioimages[imgNum]);
        }
    }    
    
    public int getChangeIn(){
        return chgImgIn;
    }
    
    private void checkKeys() {
        if (Greenfoot.isKeyDown("left")) {
            chgImgIn = chgImgIn - 1;
            imgNum = 2;
            if(getX() > 300) moveLeft();
        }
        if (Greenfoot.isKeyDown("right")) {
            chgImgIn = chgImgIn - 1;
            imgNum = 1;
            if(getX() < 500) moveRight();
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
        setLocation (getX() + speed, getY() );
    }
    public void moveLeft(){
        setLocation (getX() - speed, getY() );
    }
}
