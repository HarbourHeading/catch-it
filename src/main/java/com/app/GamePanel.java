package com.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.entity.Enemy;
import com.entity.Player;

public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = -7037762034212883804L;

	// Screen Settings
	final int originalTileSize = 16;
	final int upScale = 3;
	public final int tileSize = originalTileSize * upScale;
	
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // REMIND: Constant -> lower speed the higher screen width
	public final int screenHeight = tileSize * maxScreenRow; // REMIND: Constant -> higher speed the higher screen height

	int fps = 60; // FPS (Frames per second)

	Thread gameThread;
	KeyListen keyHandler = new KeyListen(this);
	public Player player = new Player(this, keyHandler);
	public Enemy enemy = new Enemy(this);
	public CollisionDetection collisionDetection = new CollisionDetection(this);

	// Game State
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 0;
	
	// Health
	public int health = 5;

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);

		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void setupGame() {
		gameState = pauseState;
	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / fps; // 1 second == 1 billion nanoseconds / fps (drawInterval = 0.016667s)
		double nextDrawTime = System.nanoTime() + drawInterval;

		while (gameThread != null) {

			update();
			repaint();

			try {

				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				nextDrawTime += drawInterval;
				Thread.sleep((long) remainingTime);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {

		if (gameState == playState) {
			player.update();
			enemy.update();
		}
		// REMIND: Add score addition on collision. Return enemy to x = randint(gamePanel.screenWidth)
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;
		player.draw(g2D);
		enemy.draw(g2D);

		g2D.dispose();
	}
}
