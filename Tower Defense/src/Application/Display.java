package Application;

import javax.swing.*;

public class Display extends JFrame {
    private Screen screen;

    public Display() {
        setSize(640, 640);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        screen = new Screen();
        add(screen);
    }
}
