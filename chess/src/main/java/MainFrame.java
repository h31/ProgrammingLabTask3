import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Desk desk = new Desk();
    private JPanel panel = new JPanel();

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setTitle("Chess");
        frame.startJPanel(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(950, 730));
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        //frame.add(frame.panel, FlowLayout.CENTER);
        //frame.add(frame)
        frame.startJPanel(true);
        frame.setVisible(true);
    }

    MainFrame() {
        JMenuItem revertBtn = new JMenuItem("повернуть");
        JMenuItem restartBtn = new JMenuItem("начать заново");
        JPanel infoPanel = new JPanel();
        revertBtn.setPreferredSize(new Dimension(100, 50));
        revertBtn.addActionListener(e -> {
            desk.revertDesk();
            //startJPanel(false);
            //desk.revertDesk();
            desk.turn = !desk.turn;
        });
        JMenuBar menu = new JMenuBar();
        restartBtn.setPreferredSize(new Dimension(100, 25));
        restartBtn.addActionListener(e -> {
            desk.setInitialLocation();
            startJPanel(true);
            desk.revertDesk();
            desk.revertDesk();
            desk.turn = true;
        });
        menu.add(restartBtn);
        menu.add(revertBtn);
        //restartBtn.setLayout(new SpringLayout());
        //add(restartItem);
        infoPanel.add(menu);

        //add(menu);
        //restartBtn.setLocation(0,0);
        add(infoPanel, SpringLayout.WEST);
        add(panel, SpringLayout.EAST);
        //add(restartBtn, SpringLayout.WEST);
    }

    public void startJPanel(boolean restart) {

        if (restart) {
            desk.setInitialLocation();
        } else {
            desk.revertDesk();
        }
        panel.removeAll();
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(720, 720));
        Color dark = new Color(75, 22, 50);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton btn = desk.field[i][j];
                int finalI = i;
                int finalJ = j;
                if (Math.floorMod(i, 2) == 0) {
                    if (Math.floorMod(j, 2) == 0) {
                        btn.setBackground(Color.WHITE);
                        desk.field[i][j].cellColor = Color.WHITE;
                    } else {
                        btn.setBackground(dark);
                        desk.field[i][j].cellColor = dark;
                    }
                } else {
                    if (Math.floorMod(j, 2) == 0) {
                        btn.setBackground(dark);
                        desk.field[i][j].cellColor = dark;
                    } else {
                        btn.setBackground(Color.WHITE);
                        desk.field[i][j].cellColor = Color.WHITE;
                    }
                }
                desk.field[i][j].addActionListener(e -> {
                    JFrame winFrame = new JFrame();
                    winFrame.setLocation(100, 0);
                    winFrame.setVisible(false);
                    winFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    winFrame.setLayout(new FlowLayout());
                    winFrame.setPreferredSize(new Dimension(200, 200));
                    winFrame.pack();
                    desk.move(finalI, finalJ);
                    desk.whiteWin = true; // for test
                    /*
                    if (desk.whiteWin) {
                        winFrame.setVisible(true);
                        ImageIcon img = new ImageIcon("img/WhiteWin.png");
                        JLabel pnl = new JLabel();
                        pnl.setIcon(img);
                        winFrame.add(pnl);
                    }
                    */
                });
                panel.setVisible(true);
                panel.add(desk.field[i][j]);
            }
        }

    }

}

