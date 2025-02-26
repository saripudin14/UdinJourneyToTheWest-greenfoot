import greenfoot.*;

public class Obstacle extends Actor {
    private GreenfootImage obstacleImage;
    private int speed;

    public Obstacle(int speed) {
        obstacleImage = new GreenfootImage("Cactus2.png");
        setImage(obstacleImage);
    }

    public void act() {
        // Gerakkan obstacle ke kiri
        setLocation(getX(), getY());
        move(-6);

        // Hapus obstacle jika keluar dari layar
        if (getX() < 0) {
            getWorld().removeObject(this);
        }
    }

    @Override
    public boolean intersects(Actor other) {
        // Override metode untuk mendeteksi tabrakan dengan hitbox khusus
        if (other instanceof Player) {
            GreenfootImage otherImage = other.getImage();
            int otherX = other.getX() - otherImage.getWidth() / 2;
            int otherY = other.getY() - otherImage.getHeight() / 2;

            int thisX = getX() - obstacleImage.getWidth() / 2 + 10; // Sesuaikan offset X
            int thisY = getY() - obstacleImage.getHeight() / 2 + 5; // Sesuaikan offset Y
            int thisWidth = obstacleImage.getWidth() - 20; // Lebar hitbox
            int thisHeight = obstacleImage.getHeight() - 10; // Tinggi hitbox

            return otherX < thisX + thisWidth && otherX + otherImage.getWidth() > thisX
            && otherY < thisY + thisHeight && otherY + otherImage.getHeight() > thisY;
        }
        return false;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
