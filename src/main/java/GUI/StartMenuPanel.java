package GUI;

import javax.swing.*;
import java.awt.*;

public class StartMenuPanel extends JPanel {


    public StartMenuPanel() {
        super();
        setBackground(Color.BLUE);
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
}
