package GUI;

import Logic.Planet;
import Logic.PlanetSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    public PlanetSystem pS;

    public MainPanel(PlanetSystem inputPS) {
        this.pS = inputPS;
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
        Image backGround = new ImageIcon("files/backgrounds/space4.png").getImage();
        g.drawImage(backGround, 0, 0, null);
        g.setColor(new Color(255, 237, 0));
        g.setFont(new Font("Serif", Font.ITALIC, 20));
        g.drawString("Day " + pS.t, 20, 20);
        g.drawImage(pS.star.image, pS.star.x - pS.star.r / 2,
                pS.star.y - pS.star.r / 2, 2 * pS.star.r, 2 * pS.star.r, null);
        g.drawString(pS.star.name, pS.star.x + pS.star.r / 2, pS.star.y);
        for (Planet p : pS.planets) {
            paintOrbites(g, p);
            paintPlanet(g, p);
        }
    }

    private void paintOrbites(Graphics g, Planet p) {
        g.setColor(new Color(0x2AE2FF));
        g.drawOval(400 - p.a / 2, 300 - p.b / 2, p.a, p.b);
    }

    private void paintPlanet(Graphics g, Planet p) {
        g.drawImage(p.image, p.x - p.r, p.y - p.r, 2 * p.r, 2 * p.r, null);
        g.setColor(new Color(28, 22, 251));
        g.drawString(p.name, p.x + p.r / 2, p.y - p.r);
    }
}