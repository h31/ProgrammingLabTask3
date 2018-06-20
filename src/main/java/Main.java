import java.awt.*;
import javax.swing.*;
import Arkanoid.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Arkanoid");
        frame.setLayout(new BorderLayout());
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Arkanoid game = new Arkanoid(1270, 700);
        frame.add(game, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
