import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
// Class GameOver harus extend World
public class GameOver extends World {
    public GameOver(int score) {    
        super(600, 400, 1); // Ukuran world
        
        setBackground(new GreenfootImage("GameOver.png"));
        showText("Final Score: " + score, 310, 150);

        addObject(new TryAgainbutton(), 310, 257); // Perhatikan nama class button harus benar
    }
}

