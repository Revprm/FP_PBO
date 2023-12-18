package enemies;

import java.awt.Rectangle;
import static Addition.Constants.Direction.*;

public abstract class Enemy {

	private float x, y;
	private Rectangle bounds;
	private int health;
	private int ID;
	private int enemyType;
	private int lastDir;

	public Enemy(float x, float y, int ID, int enemyType) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.enemyType = enemyType;
		bounds = new Rectangle((int) x, (int) y, 32, 32);
		lastDir = -1;
	}

	protected void setStartHealth(){
		health = Addition.Constants.Enemies.GetStartHealth(enemyType);
	}

	public void move(float speed, int dir) {
		lastDir = dir;
		switch (dir) {
		case LEFT:
			this.x -= speed;
			break;
		case UP:
			this.y -= speed;
			break;
		case RIGHT:
			this.x += speed;
			break;
		case DOWN:
			this.x += speed;
			break;
		}
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getHealth() {
		return health;
	}

	public int getID() {
		return ID;
	}

	public int getEnemyType() {
		return enemyType;
	}

	public int getLastDir() {
		return lastDir;
	}

}
