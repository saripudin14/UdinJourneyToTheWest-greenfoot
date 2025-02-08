import greenfoot.*;

public class ScrollingBackground extends Actor {
    private int speed; 

    public ScrollingBackground(int speed) {
        this.speed = speed;
        setImage("BG.png"); 
    }

    public void act() {
        setLocation(getX() - speed, getY());

        if (getX() <= -getImage().getWidth() / 2) {
            setLocation(getX() + getImage().getWidth() * 2, getY());
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
