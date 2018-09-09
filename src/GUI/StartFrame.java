package GUI;

import javax.swing.*;

public class StartFrame extends JFrame {
    public StartFrame(String s) {
        super(s);
        setResizable(false);
        setContentPane(new StartPanel());
        setSize(getContentPane().getSize());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartFrame("2D"));
    }
}