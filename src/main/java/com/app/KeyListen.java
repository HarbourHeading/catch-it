package com.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListen implements KeyListener {

    final GamePanel gamePanel;
    public boolean leftPressed, rightPressed;

    public KeyListen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyPress = e.getKeyCode();

        if (keyPress == KeyEvent.VK_LEFT || keyPress == KeyEvent.VK_A) { leftPressed = true; }
        if (keyPress == KeyEvent.VK_RIGHT || keyPress == KeyEvent.VK_D) { rightPressed = true; }

        if (keyPress == KeyEvent.VK_ESCAPE) {
            if (gamePanel.gameState == gamePanel.playState) {
                gamePanel.gameState = gamePanel.pauseState;
            }

            else if (gamePanel.gameState == gamePanel.pauseState) {
                gamePanel.gameState = gamePanel.playState;
            }
        }

        if (keyPress == KeyEvent.VK_F) {
            gamePanel.health = 5;
            gamePanel.score = 0;

            gamePanel.gameState = gamePanel.pauseState;
            gamePanel.leaderboardCalculated = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keyPress = e.getKeyCode();

        if (keyPress == KeyEvent.VK_LEFT || keyPress == KeyEvent.VK_A) { leftPressed = false; }
        if (keyPress == KeyEvent.VK_RIGHT || keyPress == KeyEvent.VK_D) { rightPressed = false; }
    }

    // Unused method.
    @Override
    public void keyTyped(KeyEvent e) {
    }
}
