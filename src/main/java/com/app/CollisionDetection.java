package com.app;

public class CollisionDetection {

	final GamePanel gamePanel;

	public CollisionDetection(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void checkTileEnemy() {
		
		if (gamePanel.player.x < (gamePanel.enemy.x + gamePanel.tileSize) && gamePanel.player.y < (gamePanel.enemy.y + gamePanel.tileSize) 
		&& (gamePanel.player.x + gamePanel.tileSize) > gamePanel.enemy.x && (gamePanel.player.y + gamePanel.tileSize) > gamePanel.enemy.y) {
			gamePanel.score += 1;
			gamePanel.enemy.intersect = true;
		}
	}
}
