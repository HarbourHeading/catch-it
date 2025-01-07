package com.test;

import java.awt.event.KeyEvent;

import org.junit.Test;

import com.app.GamePanel;
import com.app.KeyListen;
import com.entity.Player;

public class AppTest {

	@Test
	public void pauseStateOnStart() { // Correct phase (pause) on game start
		GamePanel gamePanel = new GamePanel();
		assert gamePanel.pauseState == 0;
	}
	@Test
	public void PlayerOutOfBounds() { // REMIND: Setup testing for player out of bounds
	}
}
