package entity;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FishUI {
    public BufferedImage fishIcon;
    public FishUI() {
        try {
            fishIcon = ImageIO.read(getClass().getResourceAsStream("playerRes/Blue.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
