import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class continuebutton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class continuebutton extends Actor {
    private GameWorld previousWorld;
    private GreenfootSound clickSound = new GreenfootSound("button.mp3"); // Tambahkan file suara

    public continuebutton(GameWorld previousWorld) {
        this.previousWorld = previousWorld;
        setImage("continue.png"); // Set gambar tombol continue
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            clickSound.play(); // Mainkan suara klik
            Greenfoot.delay(80); // Tambahkan delay sebelum berpindah world
            Greenfoot.setWorld(previousWorld); // Balikin ke world utama (GameWorld)
        }
    }
}
