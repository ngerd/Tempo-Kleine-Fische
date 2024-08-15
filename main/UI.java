package main;

import entity.FishUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_20;
    BufferedImage fishImage;
    public BufferedImage mainMenu, mainMenuButton, playButton, restartButton, howtoplayButton, QuitButton,
            fishwin, boatwin, Pause, Tie, howtoplayImage, buttonPoint;



    public String currentDialogue = "";
    public int commandNum = 0;
    public int howtoplay = 0;
    //Message:
    public String message ="";
    public int tileScreenState = 0;// 0: the first screen

    public UI(GamePanel gp) {
        this.gp =gp;

        arial_40 = new Font("Monospaced", Font.BOLD, 30);
        arial_20 = new Font("Monospaced", Font.BOLD, 20);

        FishUI fishUI = new FishUI();
        fishImage = fishUI.fishIcon;

        getImage();
    }

    public void getImage() {
        try {
            mainMenu = ImageIO.read(getClass().getResourceAsStream("background/gameMainMenu.png"));
            mainMenuButton = ImageIO.read(getClass().getResourceAsStream("uires/MainMenuButton.png"));
            playButton = ImageIO.read(getClass().getResourceAsStream("uires/PlayButton.png"));
            restartButton = ImageIO.read(getClass().getResourceAsStream("uires/RestartButton.png"));
            howtoplayButton = ImageIO.read(getClass().getResourceAsStream("uires/InfoButton.png"));
            QuitButton = ImageIO.read(getClass().getResourceAsStream("uires/QuitButton.png"));
            fishwin = ImageIO.read(getClass().getResourceAsStream("uires/Fishwin.png"));
            boatwin = ImageIO.read(getClass().getResourceAsStream("uires/Boatwin.png"));
            Pause = ImageIO.read(getClass().getResourceAsStream("uires/PauseButton.png"));
            Tie = ImageIO.read(getClass().getResourceAsStream("uires/Tie.png"));
            buttonPoint = ImageIO.read(getClass().getResourceAsStream("uires/buttonPoint.png"));
            howtoplayImage = ImageIO.read(getClass().getResourceAsStream("background//gameMainMenu (1).png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;
//        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //PAUSE
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState == gp.playState){
            drawStatus();
            showMessage();
        }
        if (gp.gameState == gp.endState){
            drawStatus();
            showMessage();
            drawEndGame();
        }

    }
    //show message
    public void showMessage() {
        g2.setFont(arial_20);
        g2.setColor(Color.BLACK);
        int x = 262;
        int y = 440;
        for (int i = 0; i < 2; i++){
            if(gp.fish.entity[i]. image == null){
//                messageOn = true;
                message = "Caught";
                g2.drawString(message,x,y+(42*i));
            }
            else if(gp.fish.entity[i].x == 48*27 && gp.fish.entity[i].image != null){
                message = "Free";
                g2.drawString(message,x,y+(42*i));
            }
        }
        for (int i = 0; i < 2; i++){
            if(gp.fish.entity[i+2]. image == null){
//                messageOn = true;
                message = "Caught";
                g2.drawString(message,x+48*3,y+(42*i));
            }
            else if(gp.fish.entity[i+2].x == 48*27 && gp.fish.entity[i+2].image != null){
                message = "Free";
                g2.drawString(message,x+48*3,y+(42*i));
            }
        }
    }
    public void drawTitleScreen(){
        if(tileScreenState == 0){
            if(howtoplay == 0){
                //TITLE NAME
                g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
                String text = "Tempo, kleine Fische!";
                int x = getXforCenteredText (text);
                int y = gp.tileSize*3;
                g2.drawImage(mainMenu,0, 0, 48*29, 48*11, null);
                //MENU
                g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));

//            text = "PLAY GAME";
                g2.drawImage(playButton, 104*3, 243, 58*3, 25*3, null);
                if(commandNum == 0){
                    g2.drawImage(buttonPoint, 104*3-48, 243+16,48,48,null);
                }

//            text = "HOW TO PLAY";
                g2.drawImage(howtoplayButton, 104*3, 333, 58*3, 25*3, null);
                if(commandNum == 1){
                    g2.drawImage(buttonPoint, 104*3-48, 333+16,48,48,null);
                }

//            text = "QUIT ";
                g2.drawImage(QuitButton, 104*3, 423, 58*3, 25*3, null);
                if(commandNum == 2){
                    g2.drawImage(buttonPoint, 104*3-48, 423+16,48,48,null);
                }
            }

        }
        if(tileScreenState == 1){
            g2.drawImage(howtoplayImage,0,0,48*29,48*11,null);
        }
    }
    public int getXforCenteredText(String text) {
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return x;
    }
    public void drawStatus(){
        g2.setFont(arial_40);
        g2.setColor(Color.BLACK);

        int x = 48*24-8;
        int y = 56;

        if(gp.fish.hasKey == 4){
            String text = " 4";
            g2.drawString(text,x,y);
        }
        if(gp.fish.hasKey == 3){
            String text = " 3";
            g2.drawString(text,x,y);
        }
        if(gp.fish.hasKey == 2){
            String text = " 2";
            g2.drawString(text,x,y);
        }
        if(gp.fish.hasKey == 1){
            String text = " 1";
            g2.drawString(text,x,y);
        }
        if(gp.fish.hasKey == 0){
            String text = " 0";
            g2.drawString(text,x,y);
        }
    }

    public void drawPause(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.playState){
            //Do play state stuff later
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }
    public void drawPauseScreen(){
//        String text = "PAUSED";
        g2.drawImage(Pause,48*11,48*4, gp.tileSize*6, gp.tileSize*2,null);
    }

    public void drawEndGame() {
        if(gp.gameState == gp.endState){
            if(gp.fish.free >= 3){
                String text ="FISH WIN!";
//            g2.drawString(text,getXforCenteredText(text), gp.screenHeight/2);
                g2.drawImage(fishwin,0, 0, 48*29, 48*11, null);
                drawEndButtons();
            }
            else if(gp.fish.hasKey == 2){
                String text ="TIE!";
//            g2.drawString(text,getXforCenteredText(text), gp.screenHeight/2);
                g2.drawImage(Tie,0, 0, 48*29, 48*11, null);
                drawEndButtons();
            }
            else if(gp.fish.hasKey <= 1){
                String text ="BOAT WIN!";
//            g2.drawString(text,getXforCenteredText(text), gp.screenHeight/2);
                g2.drawImage(boatwin,0, 0, 48*29, 48*11, null);
                drawEndButtons();
            }
        }

    }
    public void drawEndButtons(){
        if(howtoplay == 0){
            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));

//            text = "PLAY GAME";
            g2.drawImage(restartButton, 104*6-24, 215, 58*3, 25*3, null);
            if(commandNum == 0){
                g2.drawImage(buttonPoint, 104*6-72, 215+16,48,48,null);
            }
            //MAIN MENU
            g2.drawImage(mainMenuButton, 104*6-38, 289, 58*3+25, 25*3, null);
            if(commandNum == 1){
                g2.drawImage(buttonPoint, 104*6-72, 289 +16,48,48,null);
            }
//            text = "QUIT ";
            g2.drawImage(QuitButton, 104*6-24, 371, 58*3, 25*3, null);
            if(commandNum == 2){
                g2.drawImage(buttonPoint, 104*6-72, 371+16,48,48,null);
            }
        }
    }
}
