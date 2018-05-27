package GUI;

import Logic.Planet;
import Logic.PlanetSystem;
import Logic.Star;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class MainPanel extends JPanel {

    PlanetSystem pS = readPlanetSystem();

    private PlanetSystem readPlanetSystem() {
        List<String> list = new ArrayList<>();
        try {
            File f = new File("files/data");
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) list.add(sc.next());
        } catch (FileNotFoundException e) {
            System.out.print("fdfs");
        }
        PlanetSystem pS = new PlanetSystem(new Star(list.get(0), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2))));
        int countOfPlanets = Integer.parseInt(list.get(3));
        System.out.println(list);
        list = list.subList(4, list.size());
        for (int i = 1; i <= countOfPlanets; i++) {
            System.out.println(list);
            Planet p = new Planet(list.get(0), Integer.parseInt(list.get(1)),
                    Integer.parseInt(list.get(2)),Integer.parseInt(list.get(3)), Integer.parseInt(list.get(4)));
            pS.addPlanet(p);
            list = list.subList(5, list.size());
        }
        return pS;
    }


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
            g.drawImage(pS.star.image, pS.star.x - pS.star.r / 2, pS.star.y - pS.star.r / 2, 2 * pS.star.r, 2 * pS.star.r, null);
            g.setFont(new Font("Serif", Font.ITALIC, 20));
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
        g.drawImage(p.image, p.x - p.r, p.y - p.r, 2 * p.r, 2 *p.r, null);
        g.setColor(new Color(0, 128, 0));
        g.setFont(new Font("Serif", Font.ITALIC, 20));
        g.drawString("Day " + pS.t, 20, 20);
        g.setFont(new Font("Serif", Font.ITALIC, 20));
        g.drawString(p.name, p.x + p.r / 2, p.y - p.r);
    }
}