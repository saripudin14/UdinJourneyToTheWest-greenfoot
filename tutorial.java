import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class tutorial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tutorial extends World
{

    /**
     * Constructor for objects of class tutorial.
     * 
     */
    public tutorial()
    {    
        super(600, 400, 1); 
        setBackground(new GreenfootImage("tutorialpage.png"));
        
        addObject(new back(), 300, 350);

    }
}
