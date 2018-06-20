package logic;

import Arkanoid.Arkanoid;

import java.awt.*;
import java.util.Random;

public class Paddle {
    private Arkanoid game;
    public Point currentPosition;
    private int widthOfPaddle = 100;
    private int heightOfPaddle = 20;
    private Random rnd = new Random();

    public Paddle(Arkanoid game) {
        this.game = game;
        currentPosition = new Point(0, game.heightOfWindow / 2 - heightOfPaddle);
    }

    public Point bounceVectorOfPaddle(Rectangle bodyOfRect) {
        Point outPoint = new Point(1, 1);
        Rectangle rect = new Rectangle(currentPosition.x - widthOfPaddle / 2,
                currentPosition.y - heightOfPaddle / 2, widthOfPaddle, heightOfPaddle);
        if (rect.intersects(bodyOfRect)) {
            outPoint.y = -1;
            outPoint.x = rnd.nextInt(2) - 1;
            if (outPoint.x == 0) outPoint.x = 1;
        }
        return outPoint;
    }

    public void graphicOfPaddle(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(currentPosition.x - widthOfPaddle / 2, currentPosition.y - heightOfPaddle / 2,
                widthOfPaddle, heightOfPaddle);
        g.setColor(Color.magenta);
        g.fillRect(currentPosition.x - widthOfPaddle / 2 + 2, currentPosition.y - heightOfPaddle / 2 + 2,
                widthOfPaddle - 4, heightOfPaddle - 4);
    }
}