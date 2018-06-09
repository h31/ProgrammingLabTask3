import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private Desk desk = new Desk();
    private JPanel panel = new JPanel();

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setTitle("Chess");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(730, 730));
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.startJPanel();
        frame.setVisible(true);
        frame.requestFocus();
    }


    MainFrame() {
        JMenuItem revertItm = new JMenuItem("повернуть");
        JMenuItem restartItm = new JMenuItem("начать заново");
        revertItm.addActionListener(e -> {
            desk.revertDesk();
            desk.turn = !desk.turn;
        });
        JMenu menu = new JMenu("settings");
        restartItm.addActionListener(e -> {
            restart();
        });
        menu.setPopupMenuVisible(true);
        menu.add(restartItm);
        menu.add(revertItm);
        moveCheckBox.setSelected(true);
        revertCheckBox.setSelected(true);
        menu.add(moveCheckBox);
        menu.add(revertCheckBox);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);
        add(panel, BorderLayout.CENTER);
        addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        super.keyPressed(e);
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            desk.revertDesk();
                        }
                    }
                }
        );


    }

    private JCheckBox moveCheckBox = new JCheckBox("показывать ходы");
    private JCheckBox revertCheckBox = new JCheckBox("автоповорот");
    public void startJPanel() {
        desk.setInitialLocation();
        panel.removeAll();
        panel.setLayout(new GridLayout(8,8));
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
                    desk.move(finalI, finalJ, moveCheckBox.isSelected(), revertCheckBox.isSelected());
                    showWinFrame();
                    desk.whiteWin = false;
                    desk.blackWin = false;
                });
                panel.setVisible(true);
                panel.add(desk.field[i][j]);
            }
        }

    }

    private void restart() {
        desk.setInitialLocation();
        startJPanel();
        desk.revertDesk();
        desk.revertDesk();
        desk.turn = true;
    }

    private void showWinFrame() {
        JDialog winFrame = new JDialog();
        winFrame.setLocation(600, 180);
        winFrame.setVisible(false);
        winFrame.setLayout(new BorderLayout());
        winFrame.setPreferredSize(new Dimension(400, 410));
        winFrame.pack();
        if (desk.whiteWin || desk.blackWin) {
            winFrame.setVisible(true);
            ImageIcon img = new ImageIcon("img/WhiteWin.png");
            if (desk.blackWin && !desk.whiteWin) img = new ImageIcon("img/BlackWin.png");
            JLabel pnl = new JLabel();
            pnl.setLayout(new BorderLayout());
            JButton winBtn = new JButton();
            winBtn.setIcon(img);
            winBtn.addActionListener(r -> {
                winFrame.dispose();
                restart();
            });
            pnl.add(winBtn);
            winFrame.add(pnl);
        }

    }
}

