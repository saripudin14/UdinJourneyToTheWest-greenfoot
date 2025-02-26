import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class pausebutton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class pausebutton extends Actor {
    public pausebutton() {
        setImage("pause.png"); // Pastikan pause.png ada di folder images
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            GameWorld currentWorld = (GameWorld) getWorld(); // Ambil world saat ini
            int currentScore = currentWorld.getCurrentScore(); // Ambil skor dari GameWorld
            Greenfoot.setWorld(new GamePause(currentWorld, currentScore)); // Pindah ke GamePause dengan skor
        }
    }
}

