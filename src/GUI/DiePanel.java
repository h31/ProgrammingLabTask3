package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DiePanel extends JPanel {
    public DiePanel() {
        setVisible(true);
        setFocusable(true);
        setLayout(null);
        setSize(720, 480);
        JButton restart = new JButton(new ImageIcon("resourses/buttons/restart.png"));
        restart.setSize(restart.getIcon().getIconWidth(), restart.getIcon().getIconHeight());
        restart.setLocation(330 - restart.getWidth() / 2, 280 - restart.getHeight() / 2);
        restart.setBorderPainted(false);
        restart.setContentAreaFilled(false);
        restart.addActionListener(e -> onRestart());
        JButton exit = new JButton(new ImageIcon("resourses/buttons/exit.png"));
        exit.setSize(restart.getIcon().getIconWidth(), restart.getIcon().getIconHeight());
        exit.setLocation(330 - restart.getWidth() / 2, 350 - restart.getHeight() / 2);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.addActionListener(e -> onExit());
        add(restart);
        add(exit);
    }

    private void onRestart() {
        MainFrame.main(new String[]{});
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    private void onExit() {
        System.exit(0);
    }

    private String readCountOfPoints() {
        try {
            File f = new File("resourses/data");
            Scanner sc = new Scanner(f);
            return sc.next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "0";
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = new ImageIcon("resourses/backgrounds/die back.png").getImage();
        g.drawImage(image, 0, 0, null);
        g.setColor(new Color(0x000000));
        g.setFont(new Font("Serif", Font.PLAIN, 35));
        g.drawString(readCountOfPoints(), 465, 226);
    }
}