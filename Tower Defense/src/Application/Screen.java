package Application;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Screen extends JPanel {
    private Random random;

    public Screen() {
        random = new Random();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                g.setColor(getrndcolor());
                g.fillRect(x * 32, y * 32, 32, 32);
            }
        }
    }

    private Color getrndcolor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
}
