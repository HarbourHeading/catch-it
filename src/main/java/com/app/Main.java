package com.app;

import javax.swing.JFrame;

/**
 * The program runs a game of "Catch", where you collect eggs into a basket to
 * get scores. Game starts paused. Unpause or pause by clicking ESC. Not
 * catching an egg will make you lose a life, which you only have 5 of. Losing
 * all 5 lives gives a game over screen where the top scores will be displayed
 * and added to if you got more than the one of the top 3 scores. A retry button
 * is present in the game over screen. Controls are A/Left_arrow (Left) or
 * D/Right_Arrow (Right).
 * 
 * @author Liam
 * @since 1.0
 */

public class Main {
	public static void main(String[] args) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Catch - GAME");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.startGameThread();
	}
}
