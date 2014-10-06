import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

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
    //GreenfootSound backgroundMusic = new GreenfootSound("music.mp3"); 
    public static GreenfootSound axeSound = new GreenfootSound("axe.mp3"); 
    public static GreenfootSound swordSound = new GreenfootSound("sword.mp3");
    
    public int currentLevel = 0;
    
    
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
            scrollPosition -= scrollSpeed/2;
            moveAllObjects(-scrollSpeed);
            if(scrollPosition < -picWidth) scrollPosition = 0;
            paint(scrollPosition);
        }
        else if(main.getX() <= 300 && Greenfoot.isKeyDown("left") && !main.isLeftObstacle()) {
            scrollPosition += scrollSpeed/2;
            moveAllObjects(scrollSpeed);
            if(scrollPosition > 0) scrollPosition = -picWidth;
            paint(scrollPosition);
        } 
    }

    public void moveAllObjects(int distance) {
        List<Actor> objects = getObjects(Actor.class);
        for(Actor o : objects) {
            if(main.getClass() == o.getClass()
            || String.valueOf(o.getClass()).equals("class Heart")) continue;
            o.setLocation(o.getX() + distance, o.getY());
        }
        /*List<Grounded> objects = getObjects(Grounded.class);
        for(Grounded o : objects) {
            o.setLocation(o.getX() + distance, o.getY());
        }
        List<Fog> objectsFog = getObjects(Fog.class);
        for(Fog o : objectsFog) {
            o.setLocation(o.getX() + distance/4*3, o.getY());
        }
        List<Ground> objectsGround = getObjects(Ground.class);
        for(Ground o : objectsGround) {
            o.setLocation(o.getX() + distance, o.getY());
        }*/
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
    public void nextLevel(int i) {
        if(i == 1) Greenfoot.setWorld(new Desolation());
    }
    public void endGame() {
        main = new Olaf();
        Greenfoot.setWorld(new Forest());
        main.health = 3;
        //backgroundMusic.pause();  
    }
    
    //public void stopped(){backgroundMusic.pause();}  
      
    //public void started(){backgroundMusic.playLoop();} 
    public static void axeSound(){axeSound.play(); }
    public static void swordSound(){swordSound.play(); }
}
