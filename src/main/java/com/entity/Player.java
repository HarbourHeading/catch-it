package com.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.app.GamePanel;
import com.app.KeyListen;

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

		x = 100;
		y = 500;
		velocity = 4;
	}

	public void update() {

		if (keyListen.leftPressed == true) {
			x -= velocity;
			if (x < leftBorder) {
				x = leftBorder;
			}

		}

		if (keyListen.rightPressed == true) {
			x += velocity;
			if (x > gamePanel.screenWidth - gamePanel.tileSize) {
				x = gamePanel.screenWidth - gamePanel.tileSize;
			}
		}
		
		gamePanel.enemy.intersect = false;
		gamePanel.collisionDetection.checkTile(this);
	}

	public void draw(Graphics2D g2D) {

		g2D.setColor(Color.white);
		g2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
	}
}
