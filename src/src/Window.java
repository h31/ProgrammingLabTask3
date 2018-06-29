import javax.swing.*;

/**
 * Created by infuntis on 15/01/17.
 */
public class Window extends JFrame {
        public JPanel jPanel = new JPanel();

    public Window(){
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320,336);
        setLocation(500,200);
        add(new Field1());
        ImageIcon icon = new ImageIcon("C:\\Users\\farru\\IdeaProjects\\ProgrammingLabTask3\\src\\src\\App-ksnake-icon.png");
        setIconImage(icon.getImage());
        setVisible(true);
    }
    public static void main(String[] args) {
        Window mw = new Window();
    }
}