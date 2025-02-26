import greenfoot.*;  // Impor pustaka Greenfoot
import java.util.List;
import java.util.Random;

    public class GameWorld extends World {
    private Player player;
    private Score score;
    private int countdown = 3; // Mulai dari 3 untuk countdown
    private int countdownTimer = 60; 
    private boolean gameStarted = false;
    private boolean waitingForJump = false;
    private boolean scoreStarted = false; // Flag untuk score

    private int obstacleSpawnDelay = 75;
    private int obstacleTimer = 0;
    private double baseSpeed = 1.5; // Kecepatan awal lebih lambat
    private int lastSpeedIncreaseScore = 0;
    private int obstacleCountLimit = 2;

    private ScrollingBackground bg1, bg2;
    private Random random = new Random();
    
    private int level = 1; // Level awal
    private int scoreThreshold = 1000; // Skor yang dibutuhkan untuk naik level
    
    private GreenfootSound backgroundMusic = new GreenfootSound("menu.mp3");

    public GameWorld() {    
        super(600, 400, 1, false);
        
        backgroundMusic.playLoop();

        // Pastikan tidak ada yang berjalan sebelum game dimulai
        bg1 = new ScrollingBackground(0);
        bg2 = new ScrollingBackground(0);

        addObject(bg1, getWidth() / 2, getHeight() / 2);
        addObject(bg2, getWidth() + getWidth() / 2, getHeight() / 2);

        // Tambahkan pemain
        player = new Player();
        addObject(player, 100, getHeight() - 50);

        // Tambahkan skor
        score = new Score();
        addObject(score, 70, 30);
        
        // Tambahkan Pause Button di pojok kanan atas
        pausebutton pause = new pausebutton();
        addObject(pause, 570, 30); 

        // Mulai countdown, pastikan tidak ada yang berjalan sebelum countdown selesai
        showText("3", getWidth() / 2, getHeight() / 2);
    }

    public void act() {
        if (!gameStarted && !waitingForJump) {
            runCountdown();
        } else if (waitingForJump) {
            waitForJump();
        } else {
            runGame();
        }
    }

    private void runCountdown() {
        countdownTimer--;
        if (countdownTimer == 0) {
            countdown--;

            if (countdown > 0) {
                showText("" + countdown, getWidth() / 2, getHeight() / 2);
            } else {
                showText("Tekan SPACE untuk memulai!", getWidth() / 2, getHeight() / 2);
                waitingForJump = true;
            }

            countdownTimer = 60;
        }
    }

    private void waitForJump() {
        if (Greenfoot.isKeyDown("space")) {
            showText("", getWidth() / 2, getHeight() / 2);
            gameStarted = true;
            waitingForJump = false;
            score.startScoring(); // <-- AKTIFKAN SKOR DI SINI
            player.startGame();

            bg1.setSpeed((int) Math.round(baseSpeed));
            bg2.setSpeed((int) Math.round(baseSpeed));

        }
    }

    private void runGame() {
        if (!player.isDead()) {
            if (scoreStarted) {
                score.act();
            }
                    checkLevelUp(); // Cek apakah harus naik level

            obstacleTimer++;
            if (obstacleTimer >= obstacleSpawnDelay && countObstacles() < obstacleCountLimit) {
                spawnObstacle();
                obstacleTimer = 0;
            }

            int currentSpeed = (int) Math.round(baseSpeed);

            // **Update semua background dan obstacle agar selalu sinkron**
            bg1.setSpeed(currentSpeed);
            bg2.setSpeed(currentSpeed);

            List<Obstacle> obstacles = getObjects(Obstacle.class);
            for (Obstacle obstacle : obstacles) {
                obstacle.setSpeed(currentSpeed);
            }

            bg1.act();
            bg2.act();
            increaseDifficulty();
        } else {
            handleGameOver();
        }
    }

    private void spawnObstacle() {
        // Gunakan baseSpeed yang sama dengan background
        int currentSpeed = (int) Math.round(baseSpeed);
        Obstacle obstacle = new Obstacle(currentSpeed);
        addObject(obstacle, getWidth(), getHeight() - 50);
    }

    private void increaseDifficulty() {
        int currentScore = score.getScore();

        if (currentScore >= lastSpeedIncreaseScore + 350) {
            baseSpeed += 0.5;
            lastSpeedIncreaseScore = currentScore;

            int newSpeed = (int) Math.round(baseSpeed);

            // **Update background speed**
            bg1.setSpeed(newSpeed);
            bg2.setSpeed(newSpeed);

            // **Update semua obstacle**
            List<Obstacle> obstacles = getObjects(Obstacle.class);
            for (Obstacle obstacle : obstacles) {
                obstacle.setSpeed(newSpeed);
            }
        }

        if (currentScore % 1000 == 0) {
            obstacleSpawnDelay = Math.max(50, obstacleSpawnDelay - 10);
        }
    }

    private int countObstacles() {
        List<Obstacle> obstacles = getObjects(Obstacle.class);
        return obstacles.size();
    }

   private void handleGameOver() {
    if (player.isDeathAnimationComplete()) { // Pastikan animasi death selesai sebelum game berhenti
        backgroundMusic.stop(); // Hentikan musik sebelum pindah ke world GameOver
        
        World currentWorld = player.getWorld(); // Ambil world tempat player berada

        // Tampilkan teks "GAME OVER"
        currentWorld.showText("Yah, Kamu Kalah :(", currentWorld.getWidth() / 2, currentWorld.getHeight() / 2);
        Greenfoot.delay(80); // Tunggu sebelum teks berikutnya

        // Hapus teks sebelumnya agar efek bergantian lebih jelas
        currentWorld.showText("", currentWorld.getWidth() / 2, currentWorld.getHeight() / 2);
        Greenfoot.delay(30); // Delay kecil sebelum menampilkan teks berikutnya

        // Tampilkan teks "Ayo Coba Lagi !!!"
        currentWorld.showText("Ayo Coba Lagi !!!", currentWorld.getWidth() / 2, currentWorld.getHeight() / 2);
        Greenfoot.delay(80); // Tunggu sebelum pindah ke world GameOver

        // Hapus teks sebelum pindah ke GameOver
        currentWorld.showText("", currentWorld.getWidth() / 2, currentWorld.getHeight() / 2 + 50);
        Greenfoot.delay(30);

        int finalScore = score.getScore(); // Ambil skor terakhir dari Score
        Greenfoot.setWorld(new GameOver(finalScore)); // Pindah ke world GameOver
    }
}

    
    private void checkLevelUp() {
    if (score.getScore() >= level * scoreThreshold) {
        level++;
        increaseDifficulty();
        showText("Level " + level, getWidth() / 2, getHeight() / 2);

        Greenfoot.delay(50);
        showText("", getWidth() / 2, getHeight() / 2);

        // **Jika level bukan kelipatan 3, hapus Boss**
        if (level % 3 != 0) {
            removeBoss();
        }

        // **Jika level kelipatan 3, spawn Boss**
        if (level % 3 == 0) {
            spawnBoss();
        } 
    }
    }


    private void spawnBoss() {
    Boss boss = new Boss(player); // Kirim referensi pemain agar Boss bisa mengincar
    addObject(boss, getWidth() / 2, 70); // Spawn di tengah atas layar
    }

    private void removeBoss() {
    List<Boss> bosses = getObjects(Boss.class); // Ambil semua objek Boss di dunia
    for (Boss boss : bosses) {
        removeObject(boss); // Hapus Boss dari dunia
    }
    }

    public int getCurrentScore() {
    return score.getScore(); // Ambil skor dari objek Score
    }

}
