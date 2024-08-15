package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
public class Dice {
    private boolean spaceKeyReleased = true;
    GamePanel gp;
    KeyHandler keyH;
    Random rnd = new Random();
    int faceValue = 1;
    BufferedImage blue, orange, pink, yellow, red, green;
    public Dice(GamePanel gp, KeyHandler keyH) {
        this.gp=gp;
        this.keyH=keyH;

        getDiceImage();
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void update() {
        if (keyH.spacePressed && spaceKeyReleased) {
            faceValue = rnd.nextInt(6) + 1;
            spaceKeyReleased = false; // Set the flag to false to prevent repeated rolls
        } else if (!keyH.spacePressed) {
            spaceKeyReleased = true; // Reset the flag when the space key is released
        }
    }

    public boolean isSpacePressed() {
        return !spaceKeyReleased;
    }

    public void getDiceImage() {
        try {
            blue = ImageIO.read(getClass().getResourceAsStream("res/BlueDice.png"));
            red = ImageIO.read(getClass().getResourceAsStream("res/RedDice.png"));
            green = ImageIO.read(getClass().getResourceAsStream("res/GreenDice.png"));
            pink = ImageIO.read(getClass().getResourceAsStream("res/PinkDice.png"));
            orange = ImageIO.read(getClass().getResourceAsStream("res/OrangeDice.png"));
            yellow = ImageIO.read(getClass().getResourceAsStream("res/YellowDice.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (faceValue) {
            case 1:
                image = red;
                break;
            case 2:
                image = green;
                break;
            case 3:
                image = orange;
                break;
            case 4:
                image = pink;
                break;
            case 5:
                image = yellow;
                break;
            case 6:
                image = blue;
                break;
        }

        g2.drawImage(image, 48*13,408,gp.tileSize*2, gp.tileSize*2, null);
    }
}
