package UI;

import static Application.GameStates.MENU;
import static Application.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import scenes.Playing;

public class ActionBar extends Bar {

	private int x, y, width, height;

	private MyButton bMenu;
	private Playing playing;

	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		initButtons();

	}

	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 642, 100, 30);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
	}

	public void draw(Graphics g) {
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);
		drawButtons(g);
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);

	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);

	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
	}

}
