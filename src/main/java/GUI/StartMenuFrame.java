package GUI;

import javax.swing.*;

public class StartMenuFrame extends JFrame {
    public StartMenuFrame(String s) {
        super(s);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(new StartMenuPanel());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartMenuFrame("Planet System Simulator"));
    }
}