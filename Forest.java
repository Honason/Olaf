import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Forest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Forest extends Levels
{

    /**
     * Constructor for objects of class Forest.
     * 
     */  
    private static final EvilViking evil1 = new EvilViking();
  
        
    public Forest()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        
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
        addObject(evil1, 200, 100);
        
        if (!getObjects(Olaf.class).isEmpty())  
        {  
            for (Object olaf : getObjects(Olaf.class))  
            {  
                ((Olaf) olaf).setImage("mario1.png");   
            }  
        }  
    }
   public void act() {
         scrollDetect();
   }  
      
    
   
   
    
   public Olaf getOlaf() {
       return main;
   }
}
