import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Board extends JPanel {
    private BufferedImage image;
    public int yPosition;
    public int xPosition;

    public Board() {
        try {
            image = ImageIO.read(new File("src\\src\\140807171820-1024x764.jpg"));
        } catch (IOException ex) {
            System.out.println("Dont find picture");
        }
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                xPosition = e.getX();
                yPosition = e.getY();
                repaint();
            }
        });
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
        g.setColor(Color.BLACK);
        g.drawString("X: " + xPosition + ", Y: " + yPosition, xPosition, yPosition);
        if (xPosition <= 485 && xPosition >= 385 && yPosition <= 375 && yPosition >= 325) {
            g.setColor(Color.gray);
            g.fillRect(440, 260, 60, 60);
            g.fillRect(320, 260, 60, 60);
        }
        if (xPosition <= 315 && xPosition >= 265 && yPosition <= 375 && yPosition >= 325) {
            g.setColor(Color.gray);
            g.fillRect(200, 260, 60, 60);
            g.fillRect(320, 260, 60, 60);
        }
        if (xPosition <= 195 && xPosition >= 145 && yPosition <= 375 && yPosition >= 325) {
            g.setColor(Color.gray);
            g.fillRect(200, 260, 60, 60);
            g.fillRect(80, 260, 60, 60);
        }
        if (xPosition <= 75 && xPosition >= 25 && yPosition <= 375 && yPosition >= 325) {
            g.setColor(Color.gray);
            g.fillRect(80, 260, 60, 60);
        }
        if (xPosition <= 485 && xPosition >= 385 && yPosition <= 195 && yPosition >= 145) {
            g.setColor(Color.gray);
            g.fillRect(380, 200, 60, 60);
        }
        if (xPosition <= 375 && xPosition >= 325 && yPosition <= 195 && yPosition >= 145) {
            g.setColor(Color.gray);
            g.fillRect(380, 200, 60, 60);
            g.fillRect(260, 200, 60, 60);
        }
        if (xPosition <= 255 && xPosition >= 205 && yPosition <= 195 && yPosition >= 145) {
            g.setColor(Color.gray);
            g.fillRect(260, 200, 60, 60);
            g.fillRect(140, 200, 60, 60);
        }
        if (xPosition <= 135 && xPosition >= 85 && yPosition <= 195 && yPosition >= 145) {
            g.setColor(Color.gray);
            g.fillRect(140, 200, 60, 60);
            g.fillRect(20, 200, 60, 60);
        }

    }
}

