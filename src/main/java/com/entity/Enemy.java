package com.entity;

import com.app.GamePanel;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Random;

public class Enemy extends Entity { // REMIND: Create more Enemy instances/threads

	final GamePanel gamePanel;
	private final Random random = new Random();

	public Enemy(GamePanel gamePanel) {

		this.gamePanel = gamePanel;

		setDefaultValues();
	}

	public void setDefaultValues() {

		x = random.nextInt(gamePanel.screenWidth);
		y = -gamePanel.tileSize;
		velocity = 4;
	}

	public void update() {
		y += velocity;

		if (intersect) {
			this.resetPosition();
		}

		if (y > gamePanel.screenHeight) { // Outside screen -> Lose health
			gamePanel.health -= 1;
			if (gamePanel.health <= 0) {
				gamePanel.gameState = gamePanel.finishState;
			}
			this.resetPosition();
		}
	}

	public void draw(Graphics2D g2D) {

		g2D.setColor(Color.red);
		g2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
	}

	public void resetPosition() {

		y = -gamePanel.tileSize;
		x = random.nextInt(gamePanel.screenWidth);
	}
}
