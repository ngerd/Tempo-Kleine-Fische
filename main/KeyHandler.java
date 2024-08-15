package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    public boolean upPressed, downPressed, spacePressed, backPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            if (gp.ui.tileScreenState == 0) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                    gp.playSE(1);
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                    gp.playSE(1);
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0) {
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNum == 1) {
                        gp.ui.tileScreenState = 1;
                    }
                    if(gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                }
            }
            if (gp.ui.tileScreenState == 1) {
                if(code == KeyEvent.VK_BACK_SPACE) {
                    gp.ui.tileScreenState = 0;
                }
            }
        }
        //EndState
        if(gp.gameState == gp.endState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
                gp.playSE(1);
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
                gp.playSE(1);
            }
            if(code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.resetDefaultValue();
                }
                if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.titleState;
                    gp.resetDefaultValue();
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if(code == KeyEvent.VK_P) {
            if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
                gp.stopMusic();
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
        }
        if(code == KeyEvent.VK_BACK_SPACE) {
            backPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if(code == KeyEvent.VK_BACK_SPACE) {
            backPressed = false;
        }
    }
}
