import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(){
        setTitle("Русские шашки");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(720,560);
        setLocation(200,50);
        setBackground(Color.BLACK);
        add(new Board());
        setVisible(true);
    }
    public static void main(String[] args) {
        Window mw = new Window();
    }
}