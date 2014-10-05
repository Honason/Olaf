import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Desolation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Desolation extends Levels
{

    /**
     * Constructor for objects of class Desolation.
     * 
     */
    public Desolation()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1, false);
        bgImageName = "desolation_bg.jpg";  
        scrollSpeed = 4;  
        picWidth = (new GreenfootImage(bgImageName)).getWidth();   
        started();

        setBackground(bgImageName);  
        bgImage = new GreenfootImage(getBackground());  
        bgBase = new GreenfootImage(picWidth, getHeight());  
        bgBase.drawImage(bgImage, 0, 0); 

        prepare();
    }
    private void prepare()
    {
        for(int o = 0; o < 5; o++) {
            addObject(new Fog(), o*1084, 300);
        }
        for(int i = 0; i < 20; i++) {
            if(i == 5 || i == 8 || i == 12 || i == 13 || i == 18) continue;
            Ground ground = new Ground();
            addObject(ground, i*107, 553);
        }
        //Ground ground = new Ground();
        addObject(new Ground(), 428, 446);
        //addObject(new Ground(), 428, 339);
        addObject(new Ground(), 642, 446);

        addObject(main, 50, 100);
        addObject(new EvilViking(), 600, 100);
        addObject(new EvilViking(), 1000, 100);
        addObject(new EvilViking(), 1400, 100);
        addObject(new Bird(), 200, 100);

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
    public void act() {
        scrollDetect();
    }
}
