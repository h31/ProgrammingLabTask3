package GUI;

import javax.swing.*;

public class DieFrame extends JFrame {
    public DieFrame(String s) {
        super(s);
        setResizable(false);
        setContentPane(new DiePanel());
        setSize(getContentPane().getSize());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DieFrame("2D"));
    }
}