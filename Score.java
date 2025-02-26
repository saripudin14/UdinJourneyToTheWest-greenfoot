import greenfoot.*;

public class Score extends Actor {
    private int score = 0;
    private boolean active = false; // Flag untuk kontrol aktivasi skor

    public Score() {
        updateImage();
    }

    public void act() {
        // Hanya tambah skor jika aktif
        if (active) {
            score++;
            updateImage();
        }
    }

    // Metode untuk mengaktifkan skor
    public void startScoring() {
        active = true;
    }

    private void updateImage() {
        setImage(new GreenfootImage("Score: " + score, 24, Color.BLACK, new Color(0, 0, 0, 0)));
    }

    public int getScore() {
        return score;
    }
    
    public void stopScoring() {
    Greenfoot.stop(); // Atau cara lain untuk menghentikan perhitungan skor
    }
}