package planetSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MainPanel extends JPanel {

    PlanetSystem pS = new PlanetSystem(new Star("Solar", 50),
            Arrays.asList(new Planet("Earth",400, 200, 10), new Planet("Mars",200, 150, 5),
            new Planet("Venera",100, 80, 5), new Planet("Titan",600, 400, 20),
            new Planet("Europe",500, 300, 15) ));


    MainPanel() {
            ActionListener timerListener = e -> {
                pS.step();
                repaint();
            };
            Timer timer = new Timer(10, timerListener);
            timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pS.star.r != 0) {
            g.setColor(new Color(255, 223, 26));
            g.fillOval(pS.star.x, pS.star.y, pS.star.r, pS.star.r);
            g.setFont(new Font("Serif", Font.ITALIC, 20));
            g.drawString(pS.star.name, pS.star.x + pS.star.r / 2, pS.star.y);
        }
        if (!pS.planets.isEmpty()) {
            for (Planet p : pS.planets) {
                paintPlanet(g, p);
                paintOrbite(g, p);
            }
        }
    }

    private void paintOrbite(Graphics g, Planet p) {
        g.setColor(new Color(0x2AE2FF));
        g.drawOval(400 - p.a / 2, 300 - p.b / 2, p.a, p.b);
    }

    private void paintPlanet(Graphics g, Planet p) {
        g.setColor(new Color(0x2AE2FF));
        int radius = p.r;
        g.fillOval(p.x - radius, p.y - radius, 2 * radius, 2 * radius);
        g.setColor(new Color(0, 128, 0));
        g.setFont(new Font("Serif", Font.ITALIC, 20));
        g.drawString("Day " + pS.t, 20, 20);
        g.setFont(new Font("Serif", Font.ITALIC, 20));
        g.drawString(p.name, p.x + radius / 2, p.y - radius);
    }

}