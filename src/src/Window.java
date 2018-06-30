import javax.swing.*;

public class Window extends JFrame {
        public JPanel jPanel = new JPanel();

    public Window(){
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320,336);
        setLocation(500,200);
        add(new Field1());
        ImageIcon icon = new ImageIcon("res\\src\\App-ksnake-icon.png");
        setIconImage(icon.getImage());
        setVisible(true);
    }
    public static void main(String[] args) {
        Window mw = new Window();
    }
}