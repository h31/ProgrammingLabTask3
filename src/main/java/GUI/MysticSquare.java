package GUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MysticSquare {
    public static void main(String[] args) {
        MysticSquareFrame frame = new MysticSquareFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure?");
                if (result == JOptionPane.YES_OPTION) {
                    frame.dispose();
                }
            }
        });
    }
}
