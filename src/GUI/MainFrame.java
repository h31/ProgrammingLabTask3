package GUI;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(String s) {
        super(s);
        setResizable(false);
        setContentPane(new MainPanel());
        setSize(getContentPane().getSize());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("2D"));
    }
}