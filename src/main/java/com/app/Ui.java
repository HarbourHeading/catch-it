package com.app;

import java.awt.*;

public class Ui {

    GamePanel gamePanel;
    Graphics2D g2D;
    Font arial_20, arial_40_bold;

    public Ui(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        arial_20 = new Font("Arial", Font.PLAIN, 20);
        arial_40_bold = new Font("Arial", Font.BOLD, 40);
    }

    public void healthDisplay(Graphics2D g2D) {

        int x = (int) (gamePanel.screenWidth * 0.1d);
        int y = (int) (gamePanel.screenHeight * 0.1d);

        g2D.drawString("Health: " + gamePanel.health, x, y);
    }

    public void scoreDisplay(Graphics2D g2D) {

        int x = (int) (gamePanel.screenWidth * 0.1d);
        int y = (int) (gamePanel.screenHeight * 0.15d);

        g2D.drawString("Score: " + gamePanel.score, x, y);
    }

    public void draw(Graphics2D g2D) {

        this.g2D = g2D;

        g2D.setFont(arial_20);
        g2D.setColor(Color.WHITE);

        healthDisplay(g2D);
        scoreDisplay(g2D);

        if (gamePanel.gameState == gamePanel.finishState) {

            g2D.setFont(arial_40_bold);

            String finishMessage = "You got " + gamePanel.score + " points!";

            int messageLength = (int) g2D.getFontMetrics().getStringBounds(finishMessage, g2D).getWidth();
            int x = gamePanel.screenWidth / 2 - messageLength / 2;
            int y = (int) (gamePanel.screenHeight / 2 * 0.25d);

            g2D.drawString(finishMessage, x, y);
        }

        if (gamePanel.gameState == gamePanel.pauseState) {

            g2D.setFont(arial_40_bold);

            String pauseMessage = "Game is paused!";

            int messageLength = (int) g2D.getFontMetrics().getStringBounds(pauseMessage, g2D).getWidth();
            int x = gamePanel.screenWidth / 2 - messageLength / 2;
            int y = gamePanel.screenHeight / 2 + (gamePanel.tileSize * 2);

            g2D.drawString(pauseMessage, x, y);
        }
    }
}
