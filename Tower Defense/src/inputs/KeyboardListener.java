package inputs;

import static Application.GameStates.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Application.Game;
import Application.GameStates;

public class KeyboardListener implements KeyListener {
	private Game game;

	public KeyboardListener(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (GameStates.gameState == EDIT) {
			game.getEditor().keyPressed(e);
		} else if (GameStates.gameState == PLAYING) {
			game.getPlaying().keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
