package com.entity;

import com.app.GamePanel;
import com.app.KeyListen;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity {

	GamePanel gamePanel;
	KeyListen keyListen;
	int leftBorder = 0;

	public Player(GamePanel gamePanel, KeyListen keyListen) {

		this.gamePanel = gamePanel;
		this.keyListen = keyListen;
		
		setDefaultValues();
	}

	public void setDefaultValues() {

		x = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2; // Center screen
		y = (int) (gamePanel.screenHeight * 0.8);
		velocity = 4;
	}

	public void update() {

		if (keyListen.leftPressed) {
			x -= velocity;
			if (x < leftBorder) {
				x = leftBorder;
			}
		}

		if (keyListen.rightPressed) {
			x += velocity;
			if (x > gamePanel.screenWidth - gamePanel.tileSize) {
				x = gamePanel.screenWidth - gamePanel.tileSize;
			}
		}
		
		gamePanel.enemy.intersect = false;
		gamePanel.collisionDetection.checkTile();
	}

	public void draw(Graphics2D g2D) {

		g2D.setColor(Color.white);
		g2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
	}
}
