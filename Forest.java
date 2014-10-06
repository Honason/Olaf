import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Forest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Forest extends Levels
{

    public Forest()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1, false);
        bgImageName = "cave_bg.jpg";  
        scrollSpeed = 4;  
        picWidth = (new GreenfootImage(bgImageName)).getWidth();   
        //started();

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
        for(int o = 0; o < 0; o++) {
            addObject(new Fog(), o*1084, 300);
        }
        for(int i = 0; i < 25; i++) {
            if(i == 4 || i == 8 || i == 14 || i == 17 || i == 18) continue;
            Ground ground = new Ground();
            addObject(ground, i*107, 553);
        }
        addObject(main, 50, 100);
        addObject(new Trap("trap2.png"), 300, 478);
        addObject(new Ground(), 642, 446);
        addObject(new EvilViking(), 642, 354);
        addObject(new Bird(), 1000, 100);
        addObject(new Ground(), 1070, 446);
        addObject(new EvilViking(), 1100, 440);
        addObject(new Ground(), 1284, 446);
        addObject(new Ground(), 1712, 446);
        addObject(new Ground(), 1712, 339);
        addObject(new Ground(), 2033, 446);
        addObject(new EvilViking(), 2400, 440);
        
        addObject(new CaveGate(), 2600, 475);
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
        if (!getObjects(Fog.class).isEmpty())  
        {  
            for (Object fogs : getObjects(Fog.class))  
            {  
                ((Fog) fogs).setImage("fog.png");   
            }  
        }
    }

    public void nextLevel() {
        Greenfoot.setWorld(new Desolation());
    }

    public void act() {
        scrollDetect();
    }  
}
