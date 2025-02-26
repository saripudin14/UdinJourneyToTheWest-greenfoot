import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GamePause extends World {
    private GameWorld previousWorld; // Simpan world sebelumnya
    private int currentScore; // Simpan skor sementara

    public GamePause(GameWorld previousWorld, int currentScore) { 
        super(600, 400, 1); // Ukuran world tetap sama
        this.previousWorld = previousWorld;
        this.currentScore = currentScore; // Simpan skor yang diterima
        
        setBackground("GamePause.png"); // Set gambar background pause
        
        // Tambahkan tombol continue
        addObject(new continuebutton(previousWorld), 310, 257);

        // Tampilkan skor sementara di tengah layar
        showText("Score: " + currentScore, 310, 150);
    }
}