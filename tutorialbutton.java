import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class tutorialbutton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tutorialbutton extends Actor
{
    private GreenfootSound clickSound = new GreenfootSound("death.mp3"); // Tambahkan file suara

    public tutorialbutton() {
        setImage("tutorial.png");
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            clickSound.play(); // Mainkan suara klik
            Greenfoot.delay(0); // Tambahkan delay sebelum berpindah world
            Greenfoot.setWorld(new tutorial()); // Ganti dengan dunia tujuan
        }
    }
}
