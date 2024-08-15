package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Boat extends BoatEntity {
    GamePanel gp;
    KeyHandler keyH;
    public Boat(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle(0, 0, 48*2, 48*2);
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.height = 48*2;
        solidArea.width = 48*2;

        setDefaultValues();

        getImage();
    }
    public void setDefaultValues() {
        x = 48;
        y = 48*4;
    }
    public void getImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("playerRes/Boat.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2, null);
    }
}
