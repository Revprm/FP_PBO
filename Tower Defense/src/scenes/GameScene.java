package scenes;

import java.awt.image.BufferedImage;

import Application.Game;

public class GameScene {
	
	protected Game game;
	protected int animationIndex;
	protected int ANIMATION_SPEED = 25;
	protected int tick;
	
	public GameScene(Game game) {
		this.game = game;
	}
	
	protected boolean isAnimation(int spriteID) {
		return game.getTileManager().isSpriteAnimation(spriteID);
	}
	
	public Game getGame() {
		return game;
	}
	
	protected void updateTick() {
		tick++;
		if(tick >=20) {
			tick = 0;
			animationIndex++;
			if(animationIndex >= 4)
				animationIndex = 0;
		}
	}
	
	protected BufferedImage getSprite(int spriteID) {
		return game.getTileManager().getSprite(spriteID);
	}

	protected BufferedImage getSprite(int spriteID, int animationIndex) {
		return game.getTileManager().getAniSprite(spriteID, animationIndex);
	}
}
