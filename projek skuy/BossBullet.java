import greenfoot.*;

public class BossBullet extends Actor {
    private int speed = 3; // Kecepatan jatuh peluru
    
    private GreenfootImage[] bulletFrames; // Array untuk animasi peluru
    private int frameIndex = 0;
    private int frameDelay = 8; // Waktu antar frame animasi
    private int animationCounter = 0;

    public BossBullet() {
        // **Load animasi peluru**
        bulletFrames = new GreenfootImage[1];
        bulletFrames[0] = new GreenfootImage("bullet.png"); // Sprite pertama peluru

        setImage(bulletFrames[0]); // Set gambar awal
    }

    public void act() {
        fallDown(); // Peluru jatuh ke bawah
        animateBullet(); // Jalankan animasi peluru
        checkCollision(); // Periksa tabrakan dengan pemain atau keluar layar
    }

    private void fallDown() {
        setLocation(getX(), getY() + speed); // Peluru jatuh ke bawah dengan kecepatan tetap
    }

    private void animateBullet() {
        animationCounter++;
        if (animationCounter >= frameDelay) {
            frameIndex = (frameIndex + 1) % bulletFrames.length; // Ganti sprite antara 0 dan 1
            setImage(bulletFrames[frameIndex]); // Set gambar baru
            animationCounter = 0; // Reset penghitung animasi
        }
    }

    private void checkCollision() {
        if (isTouching(Player.class)) {
            Player player = (Player) getOneIntersectingObject(Player.class);
            if (player != null) {
                player.die(); // Pastikan Player memiliki metode die()
            }
            getWorld().removeObject(this);
        } else if (getY() >= getWorld().getHeight()) { // Hapus peluru jika sampai dasar layar
            getWorld().removeObject(this);
        }
    }
}
