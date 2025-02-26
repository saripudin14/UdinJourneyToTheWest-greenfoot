import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class back here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class back extends Actor
{
    private GreenfootSound clickSound = new GreenfootSound("button.mp3"); // Tambahkan file suara

    public back() {
        setImage("back.png");
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            clickSound.play(); // Mainkan suara klik
            Greenfoot.delay(80); // Tambahkan delay sebelum berpindah world
            Greenfoot.setWorld(new menu()); // Ganti dengan dunia tujuan
        }
    }
}
