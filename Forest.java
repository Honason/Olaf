import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    private EvilViking evil1 = new EvilViking();
    public Forest()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
       super(900, 600, 1, false);
       bgImageName = "cave_bg.jpg";  
       scrollSpeed = 4;  
       picWidth = (new GreenfootImage(bgImageName)).getWidth();        
        
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
        //addObject(new Ground(), 428, 339);
        addObject(new Ground(), 642, 446);
        
        addObject(main, 300, 133);
        addObject(evil1, 100, 100);
        
        if (!getObjects(Olaf.class).isEmpty())  
        {  
            for (Object olaf : getObjects(Olaf.class))  
            {  
                ((Olaf) olaf).setImage("olaf1.png");   
            }  
        }
        if (!getObjects(EvilViking.class).isEmpty())  
        {  
            for (Object viking : getObjects(EvilViking.class))  
            {  
                ((EvilViking) viking).setImage("evilViking1.png");   
            }  
        } 
    }
   public void act() {
       scrollDetect();
   }  

}
