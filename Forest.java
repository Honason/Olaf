import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Forest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Forest extends World
{

    /**
     * Constructor for objects of class Forest.
     * 
     */
    private static final String bgImageName = "forest_day.png";  
    private static final int scrollSpeed = 4;  
    private static final int picWidth = (new GreenfootImage(bgImageName)).getWidth();
    private static final Olaf main = new Olaf();
  
    private GreenfootImage bgImage, bgBase;  
    private int scrollPosition = 0;  
    public Forest()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1, false); 
        setBackground(bgImageName);  
        bgImage = new GreenfootImage(getBackground());  
        bgBase = new GreenfootImage(picWidth, getHeight());  
        bgBase.drawImage(bgImage, 0, 0); 
        
        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        //Ground ground = new Ground();
        for(int i = 0; i < 20; i++) {
            if(i == 5 || i == 8 || i == 12 || i == 13 || i == 18) continue;
            Ground ground = new Ground();
            addObject(ground, i*107, 553);
        }
        //Ground ground = new Ground();
        addObject(new Ground(), 428, 446);
        addObject(new Ground(), 428, 339);
        addObject(new Ground(), 642, 446);
        
        addObject(main, 312, 144);
        
        if (!getObjects(Olaf.class).isEmpty())  
        {  
            for (Object olaf : getObjects(Olaf.class))  
            {  
                ((Olaf) olaf).setImage("mario1.png");   
            }  
        }  
    }
   public void act() {
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
      
    private void paint(int position) {
        GreenfootImage bg = getBackground();
        bg.drawImage(bgBase, position, 0);  
        bg.drawImage(bgImage, position + picWidth, 0);  
   }
   
   private void moveAllObjects(int distance) {
       List<Ground> objects = getObjects(Ground.class);
       for(int i = 0; i < objects.size(); i++) {
           objects.get(i).setLocation(objects.get(i).getX() + distance, objects.get(i).getY());
           //objects.get(i).turn(5);
        }
    }
    
   public Olaf getOlaf() {
       return main;
   }
}
