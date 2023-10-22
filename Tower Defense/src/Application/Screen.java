package Application;

import javax.swing.JPanel;
import java.awt.*;

public class Screen extends JPanel {
    public Screen(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.green);
        g.fillRect(0, 0, 640, 640);
    }
}
