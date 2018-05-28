package GUI;

import Logic.PlanetSystem;

import javax.swing.*;

class InfoPanel extends JPanel {

    InfoPanel(PlanetSystem pS) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel s = new JLabel("Звезда:");
        add(s);
        JLabel p = new JLabel("Имя: " + pS.star.name);
        add(p);
        JLabel p1 = new JLabel("Радиус: " + pS.star.r);
        add(p1);
        JLabel s1 = new JLabel("Планеты:");
        add(s1);
        for (int i = 0; i < pS.planets.size(); i++) {
            JLabel l1 = new JLabel((i + 1) + ") " + "Имя: " + pS.planets.get(i).name);
            add(l1);
            JLabel l2 = new JLabel("Радиус: " + pS.planets.get(i).r);
            add(l2);
            JLabel l3 = new JLabel("Большая полуось: " + pS.planets.get(i).a);
            add(l3);
            JLabel l4 = new JLabel("Меньшая полуось: " + pS.planets.get(i).b);
            add(l4);
        }
    }
}