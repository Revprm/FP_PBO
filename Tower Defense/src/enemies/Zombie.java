package enemies;

import static Addition.Constants.Enemies.ZOMBIE;

import managers.EnemyManager;

public class Zombie extends Enemy {

	public Zombie(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, ZOMBIE, em);
	}

}
