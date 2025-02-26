import greenfoot.*;

public class Boss extends Actor {
    private int speedX = 2; // Kecepatan horizontal
    private int speedY = 1; // Kecepatan vertikal (terbang naik-turun)
    private int directionY = 1; // Arah gerakan vertikal (1 = turun, -1 = naik)
    private int shootTimer = 100; // Timer untuk menembak
    private Player player; // Target pemain

    private GreenfootImage[] flyingFrames; // Array untuk animasi terbang
    private int frameIndex = 0;
    private int frameDelay = 10; // Jumlah frame sebelum mengganti sprite
    private int animationCounter = 0;

    public Boss(Player player) {
        this.player = player;

        // **Load dua sprite untuk animasi terbang**
        flyingFrames = new GreenfootImage[4];
        flyingFrames[0] = new GreenfootImage("bos (1).png"); // Sprite pertama
        flyingFrames[1] = new GreenfootImage("bos (2).png"); // Sprite kedua
        flyingFrames[2] = new GreenfootImage("bos (3).png"); // Sprite pertama
        flyingFrames[3] = new GreenfootImage("bos (4).png"); // Sprite kedua


        setImage(flyingFrames[0]); // Set gambar awal
    }

    public void act() {
        moveBoss();
        shootBullet();
        animateFlying(); // Jalankan animasi terbang
    }

    private void moveBoss() {
        // **Gerakan horizontal (kiri-kanan)**
        if (getX() <= 50 || getX() >= getWorld().getWidth() - 50) {
            speedX = -speedX; // Balik arah jika menyentuh tepi
        }

        // **Gerakan vertikal (atas-bawah)**
        if (getY() <= 50 || getY() >= 200) { // Hanya terbang di area tertentu
            directionY = -directionY; // Balik arah jika menyentuh batas
        }

        setLocation(getX() + speedX, getY() + (speedY * directionY)); // Pindahkan Boss
    }

    private void shootBullet() {
    shootTimer--;

    if (shootTimer <= 0) {
        getWorld().addObject(new BossBullet(), getX(), getY()); // Peluru jatuh dari posisi Boss
        Greenfoot.playSound("shoot.mp3"); // Mainkan suara tembakan (pastikan file ada di folder 'sounds')
        shootTimer = Greenfoot.getRandomNumber(100) + 50; // Acak waktu tembakan
    }
}


    private void animateFlying() {
        animationCounter++;
        if (animationCounter >= frameDelay) {
            frameIndex = (frameIndex + 1) % flyingFrames.length; // Ganti gambar antara 0 dan 1
            setImage(flyingFrames[frameIndex]); // Ubah sprite Boss
            animationCounter = 0; // Reset penghitung animasi
        }
    }
}
