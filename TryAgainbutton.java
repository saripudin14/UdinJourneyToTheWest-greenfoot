import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TryAgainbutton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TryAgainbutton extends Actor
{
    private GreenfootSound clickSound = new GreenfootSound("death.mp3"); // Tambahkan file suara

    /**
     * Act - do whatever the TryAgainbutton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public TryAgainbutton() {
        setImage(new GreenfootImage("cobalagi.png"));
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            clickSound.play(); // Mainkan suara klik
            Greenfoot.delay(0); // Tambahkan delay sebelum berpindah world
            Greenfoot.setWorld(new GameWorld());
        }   
    }
}
