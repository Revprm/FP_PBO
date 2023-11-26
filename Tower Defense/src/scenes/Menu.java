package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import Application.Game;

public class Menu extends GameScene implements SceneMethods {
	
	private BufferedImage img;
	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	private Random random;

	public Menu(Game game) {
		super(game);
		random = new Random();
		importImg();
		loadSprites();
	}

	@Override
	public void render(Graphics g) {
		for (int y = 0; y < 20; y++) {
			for (int x = 0; x < 20; x++) {

				g.drawImage(sprites.get(getRndInt()), x * 32, y * 32, null);

			}
		}

	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("sprite.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadSprites() {

		for (int y = 2; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
			}
		}

	}

	private int getRndInt() {
		return random.nextInt(5);
	}

}
