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

        int x = (int) (gamePanel.screenWidth * 0.10d);
        int y = (int) (gamePanel.screenHeight * 0.10d);

        g2D.drawString("Health: " + gamePanel.health, x, y);
    }

    public void scoreDisplay(Graphics2D g2D) {

        int x = (int) (gamePanel.screenWidth * 0.10d);
        int y = (int) (gamePanel.screenHeight * 0.15d);

        g2D.drawString("Score: " + gamePanel.score, x, y);
    }

    private void showMessage(Graphics2D g2D, String message, double y) {

        int messageLength = (int) g2D.getFontMetrics().getStringBounds(message, g2D).getWidth();
        int xMessage = gamePanel.screenWidth / 2 - messageLength / 2;
        int yMessage = (int) (gamePanel.screenHeight / 2 * y);

        g2D.drawString(message, xMessage, yMessage);
    }

    public void draw(Graphics2D g2D) {

        g2D.setFont(arial_20);
        g2D.setColor(Color.white);

        healthDisplay(g2D);
        scoreDisplay(g2D);

        if (gamePanel.gameState == gamePanel.pauseState) {

            g2D.setFont(arial_40_bold);

            String pauseMessage = "Game is paused";
            showMessage(g2D, pauseMessage, 0.80d);

            String unpauseMessage = "Press ESC to unpause";
            showMessage(g2D, unpauseMessage, 0.95d);
        }

        else if (gamePanel.gameState == gamePanel.finishState) {

            String finishMessage = "You got " + gamePanel.score + " points!";
            showMessage(g2D, finishMessage, 0.25d);

            String resetMessage = "Press F to reset!";
            showMessage(g2D, resetMessage, 0.35d);

            int xScoreDisplay = (int) (gamePanel.screenWidth * 0.10d);
            int yScoreDisplay = (int) (gamePanel.screenHeight * 0.30d);

            if (!gamePanel.leaderboardCalculated) {
                topScores = finish.topScores(gamePanel.score);
                gamePanel.leaderboardCalculated = true;
            }

            for (int i = 0; i < topScores.size(); i++) {
                g2D.drawString("Top " + (i + 1) + ": " + topScores.get(i), xScoreDisplay, yScoreDisplay + (i * gamePanel.tileSize));
            }

        }

    }

}
