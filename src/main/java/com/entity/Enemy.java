package com.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import com.app.GamePanel;

public class Enemy extends Entity { // REMIND: Create more Enemy instances/threads

	GamePanel gamePanel;
	private Random random = new Random();

	public Enemy(GamePanel gamePanel) {

		this.gamePanel = gamePanel;

		setDefaultValues();
	}

	public void setDefaultValues() {

		x = random.nextInt(gamePanel.screenWidth);
		y = 0 - gamePanel.tileSize;
		velocity = 4;
	}

	public void update() {
		y += velocity;

		if (intersect) { // Intersected with player
			this.resetPosition();
		}

		if (y > gamePanel.screenHeight) { // Outside screen -> Lose health
			gamePanel.health -= 1;
			if (gamePanel.health <= 0) {
				// REMIND: Display GameOver UI and deploy pauseState
			}
			this.resetPosition();
		}
	}

	public void draw(Graphics2D g2D) {

		g2D.setColor(Color.red);
		g2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
	}

	public void resetPosition() {
		y = 0 - gamePanel.tileSize;
		x = random.nextInt(gamePanel.screenWidth);
	}
}
