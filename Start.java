import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Start extends Actor
{
    private GreenfootSound clickSound = new GreenfootSound("start.mp3"); // Tambahkan file suara

    public Start() {
        setImage("start.png");
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            clickSound.play(); // Mainkan suara klik
            Greenfoot.delay(230); // Tambahkan delay sebelum berpindah world
            Greenfoot.setWorld(new GameWorld()); // Ganti dengan dunia tujuan
        }
    }
}

