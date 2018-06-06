package GUI;

import javax.swing.*;

public class MysticSquareFrame extends JFrame {
    public MysticSquareFrame()
    {
        setTitle("MysticSquare");
        MysticSquarePanel panel = new MysticSquarePanel();
        add(panel);
        pack();
    }
}
