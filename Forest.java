import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    private static final double scrollSpeed = 5;  
    private static final int picWidth = (new GreenfootImage(bgImageName)).getWidth();
    private static final Olaf main = new Olaf(); 
  
    private GreenfootImage bgImage, bgBase;  
    private int scrollPosition = 0;  
    public Forest()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1); 
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
        Ground ground = new Ground();
        addObject(ground, 308, 300);
        addObject(main, 312, 144);
    }
   public void act() {
         if(main.getX() >= 500 && Greenfoot.isKeyDown("right")) {
             scrollPosition -= scrollSpeed;
             if(scrollPosition < -picWidth) scrollPosition = 0;
             
            }
         else if(main.getX() <= 200 && Greenfoot.isKeyDown("left")) {
             scrollPosition += scrollSpeed;
             if(scrollPosition > 0) scrollPosition = -picWidth;
            }
         paint(scrollPosition);  
   }  
      
    private void paint(int position) {  
        GreenfootImage bg = getBackground();  
        bg.drawImage(bgBase, position, 0);  
        bg.drawImage(bgImage, position + picWidth, 0);  
   }
}
