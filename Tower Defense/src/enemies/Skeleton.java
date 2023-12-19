package enemies;

import static Addition.Constants.Enemies.SKELETON;

import managers.EnemyManager;

public class Skeleton extends Enemy {
	public Skeleton(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, SKELETON, em);
	}
}
