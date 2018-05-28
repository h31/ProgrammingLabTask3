package logic;

import java.awt.*;
import java.util.Random;

public class Block {
    public Color mainColor;
    public int widthOfBlock = 70;
    public int heightOfBlock = 30;
    private Random rnd = new Random();
    private Point outPoint = new Point(1, 1);
    public Point currentPosition = new Point(0, 0);
    private Rectangle rect;

    public Point bounceVectorOfBlock(Rectangle bodyOfRect) {
        rect = new Rectangle(currentPosition.x, currentPosition.y, widthOfBlock, heightOfBlock);
        if (rect.intersects(bodyOfRect)) {
            outPoint.y = -1;
            outPoint.x = rnd.nextInt(3) - 1;
            if (outPoint.x == 0) outPoint.x = 1;
        }
        return outPoint;
    }

    public void graphicOfBlock(Graphics g) {
        g.setColor(mainColor);
        g.fillRect(currentPosition.x, currentPosition.y, widthOfBlock, heightOfBlock);
        for (int i = 0; i < heightOfBlock / 4; i++) {
            g.setColor(mainColor.darker());
            g.drawLine(currentPosition.x + i, currentPosition.y + heightOfBlock - i,
                    currentPosition.x + widthOfBlock - 1, currentPosition.y + heightOfBlock - i);
            g.drawLine(currentPosition.x + widthOfBlock - 1 - i, currentPosition.y + i,
                    currentPosition.x + widthOfBlock - 1 - i, currentPosition.y + heightOfBlock);
            g.setColor(mainColor.brighter());
            g.drawLine(currentPosition.x, currentPosition.y + i,
                    currentPosition.x + widthOfBlock - 1 - i, currentPosition.y + i);
            g.drawLine(currentPosition.x + i, currentPosition.y + heightOfBlock - i,
                    currentPosition.x + i, currentPosition.y);
        }
    }
}
