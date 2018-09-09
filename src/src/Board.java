import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Board extends JPanel implements MouseListener, MouseMotionListener {
    private BufferedImage image;
    private int ballX[];
    private int ballY[];
    private int dragFromX[];
    private int dragFromY[];

    private boolean candrag[];

    public Board() {
        try {
            image = ImageIO.read(new File("src\\src\\140807171820-1024x764.jpg"));
        } catch (IOException ex) {
            System.out.println("Dont find picture");
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        Color cl = new Color(139, 69, 19);
        g.drawImage(image, 0, 0, this);
        g.setColor(cl);
        g.fillRect(20, 20, 480, 480);
        for (int i = 20; i <= 480; i += 120) {
            for (int j = 20; j <= 480; j += 120) {
                g.clearRect(i, j, 60, 60);
                if (j < 240) {
                    g.setColor(Color.BLACK);
                    g.fillOval(i + 65, j + 5, 50, 50);
                    g.setColor(cl);
                }

                if (j > 360) {
                    g.setColor(Color.red);
                    g.fillOval(i + 65, j + 5, 50, 50);
                    g.setColor(cl);
                }
            }
        }
        for (int i = 80; i <= 460; i += 120) {
            for (int j = 80; j <= 460; j += 120) {
                g.clearRect(i, j, 60, 60);
                if (j < 120) {
                    g.setColor(Color.BLACK);
                    g.fillOval(i - 55, j + 5, 50, 50);
                    g.setColor(cl);
                }

                if (j > 300) {
                    g.setColor(Color.red);
                    g.fillOval(i - 55, j + 5, 50, 50);
                    g.setColor(cl);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

