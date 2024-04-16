package com.app;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;

import java.util.List;

public class Ui {

    final GamePanel gamePanel;
    final Font arial_20;
    final Font arial_40_bold;
    final Finish finish = new Finish();
    List<Integer> topScores;

    public Ui(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        topScores = finish.topScores(gamePanel.score);

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

        g2D.setFont(arial_20);
        g2D.setColor(Color.WHITE);

        healthDisplay(g2D);
        scoreDisplay(g2D);

        if (gamePanel.gameState == gamePanel.finishState) {

            String finishMessage = "You got " + gamePanel.score + " points!";

            int finishMessageLength = (int) g2D.getFontMetrics().getStringBounds(finishMessage, g2D).getWidth();
            int x = gamePanel.screenWidth / 2 - finishMessageLength / 2;
            int y = (int) (gamePanel.screenHeight / 2 * 0.25d);

            g2D.drawString(finishMessage, x, y);

            String resetMessage = "Press F to reset!";

            int resetMessageLength = (int) g2D.getFontMetrics().getStringBounds(finishMessage, g2D).getWidth();
            int xResetMessage = gamePanel.screenWidth / 2 - resetMessageLength / 2;
            int yResetMessage = (int) (gamePanel.screenHeight / 2 * 0.35d);

            g2D.drawString(resetMessage, xResetMessage, yResetMessage);

            int xScoreDisplay = (int) (gamePanel.screenWidth * 0.1d);
            int yScoreDisplay = (int) (gamePanel.screenHeight * 0.30d);

            if (!gamePanel.leaderboardCalculated) {
                topScores = finish.topScores(gamePanel.score);
                gamePanel.leaderboardCalculated = true;
            }

            for (int i = 0; i < topScores.size(); i++) {
                g2D.drawString("Top " + (i + 1) + ": " + topScores.get(i), xScoreDisplay, yScoreDisplay + (i * gamePanel.tileSize));
            }
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
