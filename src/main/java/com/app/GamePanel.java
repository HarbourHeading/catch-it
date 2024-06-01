package com.app;

import com.entity.Enemy;
import com.entity.Player;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;


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
	final int fps = 60;  // FPS (Frames per second)

	private Thread gameThread;
	public final CollisionDetection collisionDetection = new CollisionDetection(this);
	private final KeyListen keyHandler = new KeyListen(this);
	public final Player player = new Player(this, keyHandler);
	private final Ui ui = new Ui(this);
	public final Enemy enemy = new Enemy(this);
	private final ImageIcon backgroundImage;

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
		backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/background.png")));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		this.gameState = pauseState;
	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double drawInterval = 1000000000d / fps;  // 1 second == 1 billion nanoseconds / fps (drawInterval = 0.016667s)
		double nextDrawTime = System.nanoTime() + drawInterval;

		while (gameThread != null) {

			this.update();
			this.repaint();

			try {

				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				nextDrawTime += drawInterval;
				Thread.sleep((long) Math.max(0, remainingTime));  // In case of exception where remainingTime < 0: take max where 0 is lowest

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

		else if (gameState == finishState) {
			player.x = this.screenWidth / 2 - this.tileSize / 2;
		}

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		g2D.drawImage(backgroundImage.getImage(), 0, 0, screenWidth, screenHeight, this);


		player.draw(g2D);
		enemy.draw(g2D);
		ui.draw(g2D);

		g2D.dispose();
	}

}
