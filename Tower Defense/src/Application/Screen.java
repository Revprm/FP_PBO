package Application;

import javax.swing.JPanel;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Screen extends JPanel {

	private Game game;
	private Dimension size;
	private Render render;

	private MyMouseListener myMouseListener;
	private KeyboardListener keyboardListener;

	public Screen(Game game) {
		this.game = game;

		setPanelSize();
	}

	public void initInputs() {
		myMouseListener = new MyMouseListener(game);
		keyboardListener = new KeyboardListener();

		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);

		requestFocus();
	}

	private void setPanelSize() {
		size = new Dimension(640, 640);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		game.getRender().render(g);

	}

}
