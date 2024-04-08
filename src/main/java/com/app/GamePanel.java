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
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	int fps = 60; // FPS (Frames per second)

	Thread gameThread;
	KeyListen keyHandler = new KeyListen(this);
	Player player = new Player(this, keyHandler);
	Enemy enemy = new Enemy(this);

	// Game State
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 0;

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
