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
    public static final int scrollSpeed = 4; 
    public static final Olaf main = new Olaf();
    public int scrollPosition = 0;
    public static final String bgImageName = "forest_day.png";
    public static final int picWidth = (new GreenfootImage(bgImageName)).getWidth();
    public GreenfootImage bgImage, bgBase;

    public Levels()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1, false); 
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
    
    private void moveAllObjects(int distance) {
       List<Actor> objects = getObjects(Actor.class);
       for(int i = 0; i < objects.size(); i++) {
           if(objects.get(i) == main) continue;
           objects.get(i).setLocation(objects.get(i).getX() + distance, objects.get(i).getY());
        }
    }
    
    private void paint(int position) {
        GreenfootImage bg = getBackground();
        bg.drawImage(bgBase, position, 0);  
        bg.drawImage(bgImage, position + picWidth, 0);  
   }
}
