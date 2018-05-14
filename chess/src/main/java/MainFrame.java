import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JTabbedPane whiteHeaven = new JTabbedPane();
    JPanel blackHeaven = new JPanel();


    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setTitle("Chess");
        Desk desk = new Desk();
        frame.startJPanel(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(1200, 720));
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.add(frame.panel);


        //frame.add(desk.restart());
        //frame.add(frame.revertBtn);
        frame.setVisible(true);
    }



    MainFrame() {
        this.whiteHeaven.setPreferredSize(new Dimension(100, 100));
        this.blackHeaven.setPreferredSize(new Dimension(100, 100));
        this.whiteHeaven.setVisible(true);
        this.blackHeaven.setVisible(true);
        this.add(this.whiteHeaven);
        this.add(this.blackHeaven);
        JButton revertBtn = new JButton("повернуть");
        JButton restartBtn = new JButton("начать заново");
        revertBtn.setPreferredSize(new Dimension(100, 50));
        revertBtn.addActionListener(e -> {
            desk.revertDesk();
            //startJPanel(false);
            //desk.turn = !desk.turn;
        });

        restartBtn.setPreferredSize(new Dimension(100, 50));

        restartBtn.addActionListener(e -> {
            //desk.setInitialLocation();

            startJPanel(true);
            desk.revertDesk();
            desk.revertDesk();
            desk.turn = true;
        });
        add(restartBtn);
        add(revertBtn);
    }

    Desk desk = new Desk();
    JPanel panel = new JPanel();

    public void startJPanel(boolean start) {

        if (start) {
            desk.setInitialLocation();
        } else {
            desk.revertDesk();
        }
        //panel.removeAll();
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(720, 720));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton btn = desk.field[i][j].button;
                int finalI = i;
                int finalJ = j;
                if (Math.floorMod(i, 2) == 0) {
                    if (Math.floorMod(j, 2) == 0) {
                        btn.setBackground(Color.WHITE);
                    } else {
                        btn.setBackground(Color.BLACK);
                    }
                } else {
                    if (Math.floorMod(j, 2) == 0) {
                        btn.setBackground(Color.BLACK);
                    } else {
                        btn.setBackground(Color.WHITE);
                    }
                }
                desk.field[i][j].button.addActionListener(e -> {
                    if (!desk.equipped) {
                        desk.currentX = desk.field[finalI][finalJ].cX;
                        desk.currentY = desk.field[finalI][finalJ].cY;
                        //cellColor[0] = btn.getBackground();
                        //btn.setBackground(Color.RED);
                        desk.field[finalI][finalJ].equip();
                    } else {
                        desk.endX = desk.field[finalI][finalJ].cX;
                        desk.endY = desk.field[finalI][finalJ].cY;
                        //btn.setBackground(cellColor[0]);
                        desk.field[finalI][finalJ].put();
                    }
                });
                panel.setVisible(true);
                panel.add(desk.field[i][j].button);
            }
        }
        //return panel;
    }

    JButton btn;

    private void revert() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton saveBtn = desk.field[i][j].getButton();
            }
        }

    }
}

