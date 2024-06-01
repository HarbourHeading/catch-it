package com.app;

import javax.swing.*;
import java.util.Objects;


/*
 * Catch is a game where you catch falling objects. Each caught object gives the player a point
 * and each missed object makes the player lose health. The game is written in java and
 * built with maven.
 *
 * The game starts in a paused state. Press the ESC key to unpause. Use A or left arrow to move left,
 * and D or right arrow to move right. Upon losing all 5 lives, you are greeted with a game over screen.
 * The game over screen displays the top 3 scores. If your score beats one of them, it replaces the lower score.
 * Press F to restart.
 *
 * @author Liam
 * @since 1.0
 */



public class Main {

	private static final ImageIcon favicon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/enemy.png")));

	public static void main(String[] args) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Catch it");
		window.setIconImage(favicon.getImage());

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.startGameThread();
	}
}
