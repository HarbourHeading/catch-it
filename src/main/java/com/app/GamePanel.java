package com.app;

import com.entity.Enemy;
import com.entity.Player;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = -7037762034212883804L;

	// Screen Settings
	final int originalTileSize = 16;
	final int upScale = 3;
	public final int tileSize = originalTileSize * upScale;

	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	public final Enemy enemy = new Enemy(this);

	Thread gameThread;
	public final CollisionDetection collisionDetection = new CollisionDetection(this);
	final int fps = 60; // FPS (Frames per second)
	final KeyListen keyHandler = new KeyListen(this);
	public final Player player = new Player(this, keyHandler);
	final Ui ui = new Ui(this);

	// Game States
	public int gameState;
	public final int pauseState = 0;
	public final int playState = 1;
	public final int finishState = 2;
	public boolean leaderboardCalculated = false;

	// In-game values
	public int health = 5;
	public int score = 0;

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void setupGame() { gameState = pauseState; }

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double drawInterval = 1000000000d / fps; // 1 second == 1 billion nanoseconds / fps (drawInterval = 0.016667s)
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
				JOptionPane.showMessageDialog(this,
						"An error occurred!",
						"InterruptedException", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}
	}

	public void update() {

		if (gameState == playState) {
			player.update();
			enemy.update();
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		player.draw(g2D);
		enemy.draw(g2D);
		ui.draw(g2D);

		g2D.dispose();
	}
}
