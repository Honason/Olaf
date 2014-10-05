import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Levels here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Levels extends World
{

    /**
     * Constructor for objects of class Levels.
     * 
     */
    public String bgImageName = "cave_bg.jpg";  
    public int scrollSpeed = 4;  
    public int picWidth = (new GreenfootImage(bgImageName)).getWidth();
    public static Olaf main = new Olaf();

    public GreenfootImage bgImage, bgBase;  
    public int scrollPosition = 0;  
    public Levels(int width, int height, int cellsize, boolean bounded)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(width, height, cellsize, bounded);
        prepareWorld();
    }

    public void act() {

    }  

    public void prepareWorld() {
        for(int i=0;i<main.health;i++) {
            Heart h = new Heart();
            addObject(h, 20+i*42, 20);
        }
    }

    public void paint(int position) {
        GreenfootImage bg = getBackground();
        bg.drawImage(bgBase, position, 0);  
        bg.drawImage(bgImage, position + picWidth, 0);  
    }

    public void scrollDetect() {
        if((main.getX() >= 500 && Greenfoot.isKeyDown("right") && !main.isRightObstacle())) {
            scrollPosition -= scrollSpeed;
            moveAllObjects(-scrollSpeed);
            if(scrollPosition < -picWidth) scrollPosition = 0;
            paint(scrollPosition);
        }
        else if(main.getX() <= 300 && Greenfoot.isKeyDown("left") && !main.isLeftObstacle()) {
            scrollPosition += scrollSpeed;
            moveAllObjects(scrollSpeed);
            if(scrollPosition > 0) scrollPosition = -picWidth;
            paint(scrollPosition);
        } 
    }

    public void moveAllObjects(int distance) {
        List<Actor> objects = getObjects(Actor.class);
        for(Actor o : objects) {
            if(o.getClass() == Heart.class) continue;
            o.setLocation(o.getX() + distance, o.getY());
        }
    }

    public void updateHearts(int d) {
        List<Heart> currentHearts = getObjects(Heart.class);
        int heartsCount = currentHearts.size();
        if(d > 0) {
            for(int i=heartsCount;i<heartsCount+d;i++) {
                addObject(new Heart(), 20+i*42, 20);
            }
        } else {
            for(int i=heartsCount+d;i<heartsCount;i++) {
                removeObject(currentHearts.get(i));
            }
        }
    }

    public Olaf getOlaf() {
        return main;
    }

    public void endGame() {
        main = new Olaf();
        Greenfoot.setWorld(new Forest());
        main.health = 3;
    }

}
