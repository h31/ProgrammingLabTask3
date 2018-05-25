package GUI;

import javax.swing.*;

public class StartMenuFrame extends JFrame {
    public StartMenuFrame(String s) {
        super(s);
        setSize(800,600);
        setContentPane(new StartMenuPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartMenuFrame("Planet System Simulation Settings"));
    }
}