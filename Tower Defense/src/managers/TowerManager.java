package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import helper.LoadSave;
import objects.Tower;
import scenes.Playing;
import static Addition.Constants.Towers.*;

public class TowerManager {
	private Playing playing;
	private BufferedImage[] towerImgs;
	private Tower tower;

	public TowerManager(Playing playing) {
		this.playing = playing;
		loadTowerImgs();
		initTowers();
	}

	private void initTowers() {
		tower = new Tower(3*32, 6*32, 0, ARCHER);
		
	}

	private void loadTowerImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		towerImgs = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			towerImgs[i] = atlas.getSubimage(4 * i * 32, 32, 32, 32);
		}
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(towerImgs[ARCHER], tower.getX(), tower.getY(), null);
	}
}
