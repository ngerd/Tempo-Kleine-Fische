package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Boat;
import entity.Fish;
import entity.Dice;

public class GamePanel extends JPanel implements Runnable{
    private boolean moveTriggered = false;
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48x48 tile
    final int maxScreenCol = 29;
    final int maxScreenRow = 11;
    final int screenWidth = tileSize * maxScreenCol; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels

    // FPS
    int FPS = 60;
    KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();

    public UI ui = new UI(this);
    Thread gameThread;
    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState =1;
    public final int pauseState = 2;
    public final int endState = 3;

    Boat boat = new Boat(this, keyH);
    Fish fish = new Fish(this);
    Dice dice = new Dice(this, keyH);
    public boolean winningCondition = false;
    public BufferedImage background;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.PINK);
        this.getBackGround();
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        playMusic(0);
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void getBackGround() {
        try {
            background = ImageIO.read(getClass().getResourceAsStream("background/gameScreen.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();

            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0 ) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        if(gameState == playState) {
            dice.update();
        }
        if (gameState == pauseState) {
            //nothing
        }
        // Check if the space key is pressed and movement has not been triggered
        if (dice.isSpacePressed() && !moveTriggered) {
            int faceValue = dice.getFaceValue();
            switch (faceValue) {
                case 6:
                    // Blue color, move BlueFish to the right
                    if (fish.entity[1] != null && fish.entity[1].free == true) {
                        if(fish.entity[1].x < 48*27) {fish.entity[1].x += fish.entity[1].speed;}
                        moveTriggered = true;
                    }else if(fish.entity[1] != null && fish.entity[1].free == false){
                        if(boat.x < 48*25) {boat.x += boat.speed;}
                        moveTriggered = true;
                    }
                    playSE(3);
                    break;
                case 5:
                    // Yellow color, move BlueFish to the right
                    if (fish.entity[0] != null && fish.entity[0].free == true) {
                        if(fish.entity[0].x < 48*27) {fish.entity[0].x += fish.entity[0].speed;}
                        moveTriggered = true;
                    }else if(fish.entity[0] != null && fish.entity[0].free == false){
                        if(boat.x < 48*25) {boat.x += boat.speed;}
                        moveTriggered = true;
                    }
                    playSE(3);
                    break;
                case 4:
                    // Pink color, move BlueFish to the right
                    if (fish.entity[2] != null && fish.entity[2].free == true) {
                        if(fish.entity[2].x < 48*27) {fish.entity[2].x += fish.entity[2].speed;}
                        moveTriggered = true;
                    }else if(fish.entity[2] != null && fish.entity[2].free == false){
                        if(boat.x < 48*25) {boat.x += boat.speed;}
                        moveTriggered = true;
                    }
                    playSE(3);
                    break;
                case 3:
                    // Orange color, move BlueFish to the right
                    if (fish.entity[3] != null && fish.entity[3].free == true) {
                        if(fish.entity[3].x < 48*27) {fish.entity[3].x += fish.entity[3].speed;}
                        moveTriggered = true;
                    }else if(fish.entity[3] != null && fish.entity[3].free == false){
                        if(boat.x < 48*25) {boat.x += boat.speed;}
                        moveTriggered = true;
                    }
                    playSE(3);
                    break;
                case 2:
                case 1:
                    // Red or Green color, move Boat to the right
                    if(boat.x < 48*25) {boat.x += boat.speed;}
                    moveTriggered = true;
                    break;
            }
        } else if (!dice.isSpacePressed()) {
            // Reset the moveTriggered flag when the space key is released
            moveTriggered = false;
        }

        // Check for collision between boat and bluefish
        for(int i=0; i<4; i++) {
            if(boat.x == fish.entity[i].x && fish.entity[i].free == true) {
                fish.entity[i].image = null;
                fish.hasKey--;
                fish.entity[i].free = false;
            }
            if(fish.entity[i].x == 48*27 && fish.entity[i].image != null) {
                fish.free++;
            }
        }

        if(boat.x == 48*25) {
            gameState = endState;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //TITLE SCREEN
        if(gameState == titleState) {
            ui.draw(g2);
        }
        if(gameState == playState){
            g2.drawImage(background, 0, 0, tileSize*29, tileSize*11, null);
            boat.draw(g2);
            fish.draw(g2);
            dice.draw(g2);
            ui.draw(g2);
            g2.dispose();
        }
        //PAUSE
        if(gameState == pauseState){
            g2.drawImage(background, 0, 0, tileSize*29, tileSize*11, null);
            boat.draw(g2);
            fish.draw(g2);
            dice.draw(g2);
            ui.draw(g2);
            g2.dispose();
        }
        //END GAME
        if(gameState == endState){
            g2.drawImage(background, 0, 0, tileSize*29, tileSize*11, null);
            boat.draw(g2);
            fish.draw(g2);
            dice.draw(g2);
            ui.draw(g2);
            g2.dispose();
        }
    }
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic() {
        sound.stop();
    }
    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }
    public void resetDefaultValue(){
        boat.setDefaultValues();
        fish.setDefaultPosition();
        fish.getPlayerImage();
        fish.hasKey = 4;
        fish.free = 0;
    }
}