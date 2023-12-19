package Addition;

public class Constants {

	public static class Projectiles {
		public static final int ARROW = 0;
		public static final int BOMB = 1;
		public static final int CHAINS = 2;

		public static float GetSpeed(int type) {
			switch (type) {
			case ARROW:
				return 8f;
			case BOMB:
				return 4f;
			case CHAINS:
				return 6f;
			}
			return 0f;
		}
	}

	public static class Towers {
		public static final int ARCHER = 0;
		public static final int CANNON = 1;
		public static final int WIZARD = 2;

		public static int GetTowerCost(int towerType) {
			switch (towerType) {
			case ARCHER:
				return 35;
			case CANNON:
				return 65;
			case WIZARD:
				return 45;
			}
			return 0;
		}

		public static String GetName(int towerType) {
			switch (towerType) {
			case ARCHER:
				return "Archer";
			case CANNON:
				return "Cannon";
			case WIZARD:
				return "Wizard";
			}
			return "";
		}

		public static int GetStartDmg(int towerType) {
			switch (towerType) {
			case ARCHER:
				return 5;
			case CANNON:
				return 15;
			case WIZARD:
				return 0;
			}
			return 0;
		}

		public static float GetDefaultRange(int towerType) {
			switch (towerType) {
			case ARCHER:
				return 100;
			case CANNON:
				return 100;
			case WIZARD:
				return 100;
			}
			return 0;
		}

		public static float GetDefaultCooldown(int towerType) {
			switch (towerType) {
			case ARCHER:
				return 25;
			case CANNON:
				return 120;
			case WIZARD:
				return 40;
			}
			return 0;
		}

	}

	public static class Direction {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class Enemies {

		public static final int SKELETON = 0;
		public static final int SLIME = 1;
		public static final int ZOMBIE = 2;

		public static int GetReward(int enemyType) {
			switch (enemyType) {
			case SKELETON:
				return 10;
			case SLIME:
				return 5;
			case ZOMBIE:
				return 25;
			}
			return 0;
		}

		public static float GetSpeed(int enemyType) {
			switch (enemyType) {
			case SKELETON:
				return 0.5f;
			case SLIME:
				return 0.65f;
			case ZOMBIE:
				return 0.3f;
			}
			return 0;
		}

		public static int GetStartHealth(int enemyType) {
			switch (enemyType) {
			case SKELETON:
				return 100;
			case SLIME:
				return 40;
			case ZOMBIE:
				return 100;
			}
			return 0;
		}
	}

	public static class Tiles {
		public static final int WATER_TILE = 0;
		public static final int GRASS_TILE = 1;
		public static final int ROAD_TILE = 2;
	}
}
