package managers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Zombie;
import enemies.Enemy;
import enemies.Slime;
import enemies.Skeleton;
import helper.LoadSave;
import objects.PathPoint;
import scenes.Playing;
import static Addition.Constants.Direction.*;
import static Addition.Constants.Tiles.*;
import static Addition.Constants.Enemies.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[] enemyImgs;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	// private float speed = 0.5f;
	private PathPoint start, end;
	private int HPBarWidth = 20;

	public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
		this.playing = playing;
		enemyImgs = new BufferedImage[4];
		this.start = start;
		this.end = end;

//		addEnemy(SKELETON);
//		addEnemy(SLIME);
//		addEnemy(ZOMBIE);
		loadEnemyImgs();
	}

	private void loadEnemyImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();

		for (int i = 0; i < 3; i++) {
			enemyImgs[i] = atlas.getSubimage((i + 10) * 32, 0, 32, 32);
		}
	}

	public void update() {
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				updateEnemyMove(e);
			}
		}
	}



	public void updateEnemyMove(Enemy e) {
		if (e.getLastDir() == -1) {
			setNewDirAndMove(e);
		}

		int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDir(), e.getEnemyType()));
		int newY = (int) (e.getY() + getSpeedAndHeight(e.getLastDir(), e.getEnemyType()));

		if (getTileType(newX, newY) == ROAD_TILE) {
			e.move(GetSpeed(e.getEnemyType()), e.getLastDir());
		} else if (isAtEnd(e)) {
			e.kill();
			playing.removeOneLife();
		} else {
			setNewDirAndMove(e);
		}
	}

	private void setNewDirAndMove(Enemy e) {
		int dir = e.getLastDir();

		int xCord = (int) e.getX() / 32;
		int yCord = (int) e.getY() / 32;

		fixEnemyOffsetTile(e, dir, xCord, yCord);

		if (isAtEnd(e)) {
			return;
		}

		if (dir == LEFT || dir == RIGHT) {
			int newY = (int) (e.getY() + getSpeedAndHeight(UP, e.getEnemyType()));
			if (getTileType((int) e.getX(), newY) == ROAD_TILE) {
				e.move(GetSpeed(e.getEnemyType()), UP);
			} else {
				e.move(GetSpeed(e.getEnemyType()), DOWN);
			}
		} else {
			int newX = (int) (e.getX() + getSpeedAndWidth(RIGHT, e.getEnemyType()));
			if (getTileType(newX, (int) e.getY()) == ROAD_TILE) {
				e.move(GetSpeed(e.getEnemyType()), RIGHT);
			} else {
				e.move(GetSpeed(e.getEnemyType()), LEFT);
			}
		}

	}

	private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {

		switch (dir) {
		case RIGHT:
			if (xCord < 19)
				xCord++;
			break;
		case DOWN:
			if (yCord < 19)
				yCord++;
			break;
		}

		e.setPos(xCord * 32, yCord * 32);

	}

	private boolean isAtEnd(Enemy e) {
		if (e.getX() == end.getxCord() * 32) {
			if (e.getY() == end.getyCord() * 32) {
				return true;
			}
		}
		return false;
	}

	private int getTileType(int x, int y) {
		return playing.getTileType(x, y);
	}

	private float getSpeedAndHeight(int dir, int enemyType) {
		if (dir == UP)
			return -GetSpeed(enemyType);
		else if (dir == DOWN)
			return GetSpeed(enemyType) + 32;
		return 0;
	}

	private float getSpeedAndWidth(int dir, int enemyType) {
		if (dir == LEFT)
			return -GetSpeed(enemyType);
		else if (dir == RIGHT)
			return GetSpeed(enemyType) + 32;
		return 0;
	}

	public void addEnemy(int enemyType) {
		int x = start.getxCord() * 32;
		int y = start.getyCord() * 32;

		switch (enemyType) {
		case SKELETON:
			enemies.add(new Skeleton(x, y, 0, this));
			break;
		case SLIME:
			enemies.add(new Zombie(x, y, 0, this));
			break;
		case ZOMBIE:
			enemies.add(new Slime(x, y, 0, this));
			break;
		}
	}

	public void draw(Graphics g) {
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				drawEnemy(e, g);
				drawHealthBar(e, g);
			}
		}

	}

	private void drawHealthBar(Enemy e, Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) e.getX() + 16 - (getNewBarWidth(e) / 2), (int) e.getY() - 10, getNewBarWidth(e), 3);
	}

	private int getNewBarWidth(Enemy e) {
		return (int) (HPBarWidth * e.getHealthBarFloat());
	}

	private void drawEnemy(Enemy e, Graphics g) {
		g.drawImage(enemyImgs[e.getEnemyType()], (int) e.getX(), (int) e.getY(), null);
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void spawnEnemy(int nextEnemy) {
		addEnemy(nextEnemy);
		
	}

	public int getAmountOfAliveEnemies() {
		int size = 0;
		for(Enemy e : enemies) {
			if(e.isAlive()) {
				size++;
			}
		}
		return size;
	}

	public void rewardPlayer(int enemyType) {
		playing.rewardPlayer(enemyType);
		
	}
	
	public void reset() {
		enemies.clear();
	}
}
