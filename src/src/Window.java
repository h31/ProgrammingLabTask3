import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(){

    }
    public static void main(String[] args) {
        dragBallPanel p = new dragBallPanel();
        p.setBackground(Color.YELLOW);
        JFrame db = new JFrame("Drag Ball");
        db.setSize(658,680);
        Board board = new Board();
        db.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        db.add(p);
        db.setVisible(true);
    }
}