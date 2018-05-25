package GUI;

import javax.swing.*;

public class PlanetConfigurationFrame extends JFrame {

    public PlanetConfigurationFrame(String s) {
        super(s);
        setSize(800,600);
        setContentPane(new PlanetConfigurationPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlanetConfigurationFrame("Planet System Simulation Settings"));
    }
}
