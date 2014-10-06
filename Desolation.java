import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;

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
        for(int o = 0; o < 0; o++) {
            addObject(new Fog(), o*1084, 300);
        }
        Integer secondLayer[] = {3,4,7,8,9,10,11,12,13,14,15,18,19,29};
        Integer thirdLayer[]  = {13,14,18,19,29};
        for(int i = 0; i < 30; i++) {
            if(i == 19 || i == 29) addObject(new Ground(), i*107, 292);
            if(Arrays.asList(thirdLayer).indexOf(i) > -1) addObject(new Ground(), i*107, 379);
            if(Arrays.asList(secondLayer).indexOf(i) > -1) addObject(new Ground(), i*107, 466);
            //1st layer
            if(i == 16 || i == 17 ) continue;
            addObject(new Ground(), i*107, 553);
        }
        addObject(main, 50, 100);
        addObject(new EvilViking(), 360, 372);
        addObject(new EvilViking(), 500, 459);
        addObject(new Bird(), 1000, 150);
        addObject(new EvilViking(), 1200,466);
        addObject(new EvilViking(), 1400, 150);
        addObject(new Platform(), 2480, 300);
        addObject(new Platform(), 2682, 420); //Blaze it
        addObject(new Dragon(), 2600, 200);
        addObject(new Horn(), 3103, 150);

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
    public void openGate() {
        
    }
}
