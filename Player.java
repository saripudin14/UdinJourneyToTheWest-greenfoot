import greenfoot.*;

class Player extends Actor {
    private double velocity = 0;
    private double gravity = 0.6;
    private int jumpStrength = -12;
    private int groundLevel;
    private boolean isJumping = false;
    private boolean isDead = false;
    private boolean isMoving = false;
    private boolean gameStarted = false;
    private boolean hasJumped = false;
    private boolean deathAnimationComplete = false;

    private GreenfootImage[] idleFrames;
    private GreenfootImage[] runFrames;
    private GreenfootImage[] jumpFrames;
    private GreenfootImage[] deathFrames;
    private int frameIndex = 0;
    private int frameDelay = 8; // **Agar animasi lebih smooth**
    private int animationCounter = 0;

    public Player() {
        // **Load animasi idle**
        idleFrames = new GreenfootImage[1];
        for (int i = 0; i < idleFrames.length; i++) {
            idleFrames[i] = new GreenfootImage("idle (" + (i + 1) + ").png");
        }

        // **Load animasi lari**
        runFrames = new GreenfootImage[6];
        for (int i = 0; i < runFrames.length; i++) {
            runFrames[i] = new GreenfootImage("run (" + (i + 1) + ").png");
        }

        // **Load animasi lompat**
        jumpFrames = new GreenfootImage[8];
        for (int i = 0; i < jumpFrames.length; i++) {
            jumpFrames[i] = new GreenfootImage("jump (" + (i + 1) + ").png");
        }

        // **Load animasi mati**
        deathFrames = new GreenfootImage[8];
        for (int i = 0; i < deathFrames.length; i++) {
            deathFrames[i] = new GreenfootImage("death (" + (i + 1) + ").png");
        }

        setImage(idleFrames[0]);
    }

    @Override
    protected void addedToWorld(World world) {
        groundLevel = getWorld().getHeight() - 50;
    }

    public void act() {
        if (!gameStarted) {
            animateIdle(); // **Animasi idle tetap berjalan sebelum game mulai**
            if (Greenfoot.isKeyDown("space")) {
                gameStarted = true;
                hasJumped = true;
                velocity = jumpStrength;
            }
            return;
        }

        if (!isDead) {
            moveAndJump();
            animate();
            checkCollision();
        } else {
            playDeathAnimation();
        }
    }

    public void startGame() {
        gameStarted = true;
    }

    public boolean hasJumped() {
        return hasJumped;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isDeathAnimationComplete() { // **Metode untuk cek apakah animasi death selesai**
        return deathAnimationComplete;
    }

    private void moveAndJump() {
        if (Greenfoot.isKeyDown("space") && getY() >= groundLevel) {
            velocity = jumpStrength;
            isJumping = true;
            isMoving = true;
            hasJumped = true;
            
            // **Tambahkan efek suara lompat**
            Greenfoot.playSound("jump.mp3");
        }

        velocity += gravity;
        setLocation(getX(), (int) (getY() + velocity));

        if (getY() >= groundLevel) {
            setLocation(getX(), groundLevel);
            isJumping = false;
        }
    }

    private void animate() {
        animationCounter++;
        if (animationCounter % frameDelay == 0) {
            if (isJumping) {
                setImage(jumpFrames[frameIndex % jumpFrames.length]);
            } else if (isMoving) {
                setImage(runFrames[frameIndex % runFrames.length]);
            } else {
                animateIdle();
            }
            frameIndex++;
        }
    }

    private void animateIdle() {
        if (animationCounter % (frameDelay * 2) == 0) {
            setImage(idleFrames[frameIndex % idleFrames.length]);
            frameIndex++;
        }
    }

    private void checkCollision() {
        if (isTouching(Obstacle.class) || (isTouching(BossBullet.class))) {
            isDead = true;
            frameIndex = 0;

            // **Tambahkan efek suara mati**
            Greenfoot.playSound("dead.mp3");
        }
    }
    
    public void die() {
    isDead = true; // Tandai pemain sebagai mati
    Greenfoot.playSound("dead.mp3"); // Putar suara kematian
    }
    
    private void playDeathAnimation() {
        if (frameIndex < deathFrames.length) {
            setImage(deathFrames[frameIndex]);
            frameIndex++;
        } else {
            deathAnimationComplete = true; // **Animasi selesai, beri tanda**
        }
    }
}
