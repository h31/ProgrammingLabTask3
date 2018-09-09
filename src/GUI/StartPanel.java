package GUI;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
    public StartPanel() {
        setVisible(true);
        setFocusable(true);
        setLayout(null);
        setSize(720, 480);
        JButton start = new JButton(new ImageIcon("resourses/buttons/start.png"));
        start.setSize(start.getIcon().getIconWidth(), start.getIcon().getIconHeight());
        start.setLocation(340 - start.getWidth() / 2, 150 - start.getHeight() / 2);
        start.setBorderPainted(true);
        start.setContentAreaFilled(false);
        start.addActionListener(e -> StartPanel.this.onStart());
        JButton exit = new JButton(new ImageIcon("resourses/buttons/quit.png"));
        exit.setSize(start.getIcon().getIconWidth(), start.getIcon().getIconHeight());
        exit.setLocation(340 - start.getWidth() / 2, 300 - start.getHeight() / 2);
        exit.setBorderPainted(true);
        exit.setContentAreaFilled(false);
        exit.addActionListener(e -> onExit());
        add(start);
        add(exit);
    }

    private void onStart() {
        MainFrame.main(new String[]{});
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    private void onExit() {
        System.exit(0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = new ImageIcon("resourses/backgrounds/BackGroundMenu.png").getImage();
        g.drawImage(image, 0, 0, null);
    }
}