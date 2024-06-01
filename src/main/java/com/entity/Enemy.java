package com.entity;

import com.app.GamePanel;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.util.Objects;
import java.util.Random;


public class Enemy extends Entity {

	final GamePanel gamePanel;
	private final Random random = new Random();
	private final ImageIcon enemySprite;

	public Enemy(GamePanel gamePanel) {

		this.gamePanel = gamePanel;
		enemySprite = new ImageIcon(Objects.requireNonNull(getClass().getResource("/enemy.png")));


		this.setDefaultValues();
	}

	public void setDefaultValues() {

		this.resetPosition();
		velocity = 4;
	}

	public void update() {

		y += velocity;

		if (intersect) {
			this.resetPosition();
		}

		else if (y > gamePanel.screenHeight) {  // Outside screen -> Player lose health

			gamePanel.health -= 1;

			if (gamePanel.health <= 0) {
				gamePanel.gameState = gamePanel.finishState;
			}

			this.resetPosition();
		}

	}

	public void draw(Graphics2D g2D) {

		g2D.drawImage(enemySprite.getImage(), x, y, gamePanel.tileSize, gamePanel.tileSize, gamePanel);
	}

	public void resetPosition() {

		x = random.nextInt(gamePanel.screenWidth - gamePanel.tileSize);  // If tileSize is not negated,
																				// it can spawn outside the window
		y = -gamePanel.tileSize;
	}

}
