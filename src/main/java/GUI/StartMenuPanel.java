package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class StartMenuPanel extends JPanel {


    public StartMenuPanel() {
        repaint();
        setSize(800, 600);

        setVisible(true);
        JLabel welcomeText = new JLabel("Добро пожаловать в Planet System Simulation");
        JButton newGame = new JButton("Новая игра");
        newGame.addActionListener(e -> onNewGame());
        JButton load = new JButton("Загрузить");
        load.addActionListener(e -> onLoad());
        JButton exit = new JButton("Выход");
        exit.addActionListener(e -> onExit());
        add(welcomeText);
        add(newGame);
        add(load);
        add(exit);
    }

    public void onNewGame() {
        NewGameSettingsFrame.main(new String[]{});
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    public void onLoad() {

    }

    public void onExit() {
        System.exit(0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            Image image = ImageIO.read(new File("files/space.png"));
            g.drawImage(image,0, 0, 800, 600, null);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
