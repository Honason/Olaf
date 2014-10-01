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
    public String bgImageName = "forest_day.png";  
    public int scrollSpeed = 4;  
    public int picWidth = (new GreenfootImage(bgImageName)).getWidth();
    public static final Olaf main = new Olaf();

    public GreenfootImage bgImage, bgBase;  
    public int scrollPosition = 0;  
    public Levels(int width, int height, int cellsize, boolean bounded)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(width, height, cellsize, bounded);

    }

    public void act() {

    }  

    public void paint(int position) {
        GreenfootImage bg = getBackground();
        bg.drawImage(bgBase, position, 0);  
        bg.drawImage(bgImage, position + picWidth, 0);  
    }

    public void scrollDetect() {
        if(main.getX() >= 500 && Greenfoot.isKeyDown("right") && !main.isRightObstacle()) {
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
        for(int i = 0; i < objects.size(); i++) {
            if(objects.get(i) == main) continue;
            objects.get(i).setLocation(objects.get(i).getX() + distance, objects.get(i).getY());
        }
    }

    public Olaf getOlaf() {
        return main;
    }

    public void endGame() {
        Greenfoot.setWorld(new Forest());
    }

}
