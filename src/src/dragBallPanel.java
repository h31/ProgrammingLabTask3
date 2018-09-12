import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class dragBallPanel extends JPanel implements MouseMotionListener,MouseListener {
    private int ballSize = 65;
    private int ballX[] = {5, 167, 328, 488, 88, 248, 408, 568, 5, 167, 328, 488, 88, 248, 408, 568, 5, 167, 328, 488, 88, 248, 408, 568};
    private int ballY[] = {7, 7, 7, 7, 87, 87, 87, 87, 167, 167, 167, 167, 407, 407, 407, 407, 487, 487, 487, 487, 567, 567, 567, 567};

    private int dragFromX[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int dragFromY[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private boolean candrag[] = {false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false};

    public dragBallPanel() {
        setForeground(Color.red);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 640; i += 160) {
            for (int j = 0; j < 640; j += 160) {
                g.setColor(Color.gray);
                g.fillRect(j, i, 80, 80);
            }

        }

        for (int i = 80; i < 640; i += 160) {
            for (int j = 80; j < 640; j += 160) {
                g.setColor(Color.gray);
                g.fillRect(j, i, 80, 80);
            }

        }
        for (int i = 0; i < 12; i++) {
            g.setColor(Color.BLACK);
            g.fillOval(ballX[i], ballY[i], ballSize, ballSize);
        }
        for (int i = 12; i < 24; i++) {
            g.setColor(Color.BLUE);
            g.fillOval(ballX[i], ballY[i], ballSize, ballSize);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        for (int i = 0; i <= 23; i++) {
            if ((x >= ballX[i] && x <= ballX[i] + ballSize) && (y >= ballY[i] && y < +ballY[i] + ballSize)) {
                for (int g = 0; g <= 23; g++) {
                    if (i == g) {
                        candrag[g] = true;
                    } else candrag[g] = false;
                }
            }
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (int i = 0; i <= 23; i++) {
            candrag[i] = false;

        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (int i = 0; i <= 23; i++) {
            if (candrag[i]) {
                ballX[i] = e.getX() - dragFromX[i];
                ballY[i] = e.getY() - dragFromY[i];

                this.repaint();
            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
