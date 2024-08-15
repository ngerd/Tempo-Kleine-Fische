package entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Fish extends FishEntity {
    GamePanel gp;
    public FishEntity[] entity;
    int defaultX = 48*13;
    int defaultY = 48*3;
    int space = 48;
    public int hasKey = 4;
    public int free = 0;


    public Fish(GamePanel gp) {
        this.gp=gp;

        entity = new FishEntity[10];

        setDefaultPosition();

        getPlayerImage();
    }

    public void setDefaultPosition() {
        entity[0] = new FishEntity();
        entity[0].x = defaultX;
        entity[0].y = defaultY;
        entity[0].free = true;

        entity[1] = new FishEntity();
        entity[1].x = defaultX;
        entity[1].y = defaultY + space;
        entity[1].free = true;

        entity[2] = new FishEntity();
        entity[2].x = defaultX;
        entity[2].y = defaultY + space*2;
        entity[2].free = true;

        entity[3] = new FishEntity();
        entity[3].x = defaultX;
        entity[3].y = defaultY + space*3;
        entity[3].free = true;
    }

    public void getPlayerImage() {
        try {
            entity[0].image = ImageIO.read(getClass().getResourceAsStream("playerRes/Yellow.png"));

            entity[1].image = ImageIO.read(getClass().getResourceAsStream("playerRes/Blue.png"));

            entity[2].image = ImageIO.read(getClass().getResourceAsStream("playerRes/Pink.png"));

            entity[3].image = ImageIO.read(getClass().getResourceAsStream("playerRes/Orange.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for(int i=0; i<4; i++) {
            if(entity[i].free == true) {
                g2.drawImage(entity[i].image, entity[i].x, entity[i].y, gp.tileSize, gp.tileSize,null);
            }
        }
    }
}
