package GUI;

import javax.swing.*;

public class MainFrame extends JFrame {
    private MainFrame(String s) {
        super(s);
        setSize(800, 600);
        setContentPane(new MainPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("Planet System Simulation"));
    }
}
