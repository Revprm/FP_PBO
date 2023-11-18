package Addition;

public class Constants {
    public static class Projectiles{
        public static final int ARROW = 0;
        public static final int CHAINS = 1;
        public static final int BOMB = 2;

        public static float GetSpeed(int type){
            switch (type){
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

    public static class Direction {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class Enemies {

        public static final int ZOMBIE = 0;
        public static final int SKELETON = 1;
        public static final int GHOST = 2;

        public static int GetReward(int enemyType) {
            switch (enemyType) {
                case ZOMBIE:
                    return 5;
                case SKELETON:
                    return 10;
                case GHOST:
                    return 25;
            }
            return 0;
        }

        public static float GetSpeed(int enemyType) {
            switch (enemyType) {
                case ZOMBIE:
                    return 0.5f;
                case SKELETON:
                    return 0.7f;
                case GHOST:
                    return 0.45f;
            }
            return 0;
        }

        public static int GetStartHealth(int enemyType) {
            switch (enemyType) {
                case ZOMBIE:
                    return 85;
                case SKELETON:
                    return 100;
                case GHOST:
                    return 400;
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
