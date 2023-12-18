package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helper.LoadSave;
import helper.Utilities;
import objects.Tower;
import scenes.Playing;
import static Addition.Constants.Towers.*;

public class TowerManager {
	private Playing playing;
	private BufferedImage[] towerImgs;
	private ArrayList<Tower> towers = new ArrayList<>();
	private int towerAmount = 0;

	public TowerManager(Playing playing) {
		this.playing = playing;
		loadTowerImgs();
	}

	private void loadTowerImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		towerImgs = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			towerImgs[i] = atlas.getSubimage((13 + i) * 32, 0, 32, 32);
		}
	}

	public void addTower(Tower selectedTower, int xPos, int yPos) {
		towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
	}

	public void update() {
		for (Tower t : towers) {
			t.update();
			attackEnemyIfClose(t);
		}
	}

	private void attackEnemyIfClose(Tower t) {
		for (Enemy e : playing.getEnemyManager().getEnemies()) {
			if (e.isAlive()) {
				if (isEnemyInRange(t, e)) {
					if (t.isCooldownOver()) {
						playing.shootEnemy(t, e);
						t.resetCooldown();
					}
				} else {

				}
			}
		}

	}

	private boolean isEnemyInRange(Tower t, Enemy e) {
		int range = helper.Utilities.GetHypoDist(t.getX(), t.getY(), e.getX(), e.getY());
		return range < t.getRange();
	}

	public void draw(Graphics g) {
		for (Tower t : towers) {
			g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
		}
		// g.drawImage(towerImgs[ARCHER], tower.getX(), tower.getY(), null);
	}

	public Tower getTowerAt(int x, int y) {
		for (Tower t : towers) {
			if (t.getX() == x) {
				if (t.getY() == y) {
					return t;
				}
			}
		}
		return null;
	}

	public BufferedImage[] getTowerImgs() {
		return towerImgs;
	}

}
