package enemies;

import static Addition.Constants.Enemies.KNIGHT;

public class Knight extends Enemy {

	public Knight(float x, float y, int ID) {
		super(x, y, ID, KNIGHT);
		setStartHealth();
	}

}