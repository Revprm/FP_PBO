package enemies;

import static Addition.Constants.Enemies.SLIME;

import managers.EnemyManager;

public class Slime extends Enemy {

	public Slime(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, SLIME, em);
	}

}
