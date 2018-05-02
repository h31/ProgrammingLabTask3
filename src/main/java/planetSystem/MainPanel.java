package planetSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class MainPanel extends JPanel {

    public int starRadius = 50;

    public List<Planet> planets = Arrays.asList(new Planet(100, 50, 10), new Planet(150, 70, 5),
            new Planet(60, 50, 5), new Planet(200, 150, 20), new Planet(250, 50, 15),
            new Planet(180, 150, 8), new Planet(250, 200, 4), new Planet(170, 100, 5));

    public MainPanel() {
        setBackground(Color.BLACK);
        ActionListener timerListener = e -> {
            for (Planet planet:planets) {
                planet.step();
            }
            repaint();
        };
        Timer timer = new Timer(5, timerListener);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 223, 26));
        g.fillOval(400 - starRadius / 2 ,300 - starRadius / 2  , starRadius,starRadius);
        for (Planet p:planets) {
            paintPlanet(g, p);
        }

        /*
        for (Planet planet:planets) {
            g.setColor(new Color(0xFF2A18));
            g.drawOval(400 - planet.a / 2, 300 - planet.b / 2, planet.a, planet.b);
        }
        */
    }

    private void paintPlanet(Graphics g, Planet p) {
        g.setColor(new Color(0x2AE2FF));
        int radius = p.r;
        g.fillOval(p.x - radius, p.y - radius, 2 * radius, 2 * radius);
    }

}