package enemies;

import static Addition.Constants.Enemies.WOLF;;

public class Wolf extends Enemy {

	public Wolf(float x, float y, int ID) {
		super(x, y, ID, WOLF);
		setStartHealth();
	}

}
