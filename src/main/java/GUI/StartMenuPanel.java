package GUI;

import javax.swing.*;
import java.awt.*;

public class StartMenuPanel extends JPanel {

    public StartMenuPanel() {
        this.setLayout(null);
        setSize(800, 600);
        setVisible(true);
        JLabel welcomeText = new JLabel("Добро пожаловать в Planet System Simulator");
        welcomeText.setForeground(new Color(0x3A1BFB));
        welcomeText.setFont(new Font("Serif", Font.ITALIC, 40));
        welcomeText.setLocation(20, 20);
        welcomeText.setSize(800, 100);
        JButton newGame = new JButton(new ImageIcon("files/buttons/newgame.png"));
        newGame.setBorderPainted(false);
        newGame.setFocusPainted(false);
        newGame.setContentAreaFilled(false);
        newGame.setSize(newGame.getIcon().getIconWidth(), newGame.getIcon().getIconHeight());
        newGame.setLocation(400 - newGame.getWidth() / 2, 220 - newGame.getHeight() / 2);
        newGame.addActionListener(e -> onNewGame());
        JButton exit = new JButton(new ImageIcon("files/buttons/exit.png"));
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setContentAreaFilled(false);
        exit.setSize(exit.getIcon().getIconWidth(), exit.getIcon().getIconHeight());
        exit.setLocation(400 - exit.getWidth() / 2, 350 - exit.getHeight() / 2);
        exit.addActionListener(e -> onExit());
        add(welcomeText);
        add(newGame);
        add(exit);
    }

    private void onNewGame() {
        NewGameSettingsFrame.main(new String[]{});
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    private void onExit() {
        System.exit(0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = new ImageIcon("files/backgrounds/space.png").getImage();
        g.drawImage(image, 0, 0, 800, 600, null);
    }
}
