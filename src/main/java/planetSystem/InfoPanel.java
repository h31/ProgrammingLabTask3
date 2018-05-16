package planetSystem;

import javax.swing.*;
import java.util.Arrays;

class InfoPanel extends JPanel {

    PlanetSystem pS = new PlanetSystem(new Star("Solar", 50),
            Arrays.asList(new Planet("Earth", 400, 200, 10), new Planet("Mars", 200, 150, 5),
                    new Planet("Venera", 100, 80, 5), new Planet("Titan", 600, 400, 20),
                    new Planet("Europe", 500, 300, 15)));

    InfoPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel s = new JLabel("Star:");
        add(s);
        JLabel p = new JLabel("Name: " + pS.star.name);
        add(p);
        JLabel p1 = new JLabel("Radius: " + pS.star.r);
        add(p1);
        JLabel s1 = new JLabel("Planets:");
        add(s1);
        for (int i = 0; i < pS.planets.size(); i++) {
            JLabel l1 = new JLabel((i +1) + ") " + "Name:" + pS.planets.get(i).name);
            add(l1);
            JLabel l2 = new JLabel("Radius: " + pS.planets.get(i).r);
            add(l2);
            JLabel l3 = new JLabel("a: " + pS.planets.get(i).a);
            add(l3);
            JLabel l4 = new JLabel("b: " + pS.planets.get(i).b);
            add(l4);
        }
    }
}