package GUI;

import javax.swing.*;

public class PlanetConfigurationFrame extends JFrame {

    public PlanetConfigurationFrame(String s) {
        super(s);
        setSize(500,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(new PlanetConfigurationPanel());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlanetConfigurationFrame("Planet System Simulator"));
    }
}
