package logic;

import Arkanoid.Arkanoid;

import java.awt.*;

public class Ball {
    private Arkanoid game;
    public Point currentPosition = new Point(0, 0);
    private Point positionOfBall = new Point(1, 1);
    private Rectangle bodyOfBall;
    private Point currentPointVector;
    private Block block;
    private int radius = 10;

    public Ball(Arkanoid game) {
        this.game = game;
    }

    public void movementOfBall(double updateTime) {
        currentPosition.translate((int) (positionOfBall.x * updateTime),
                (int) (positionOfBall.y * updateTime));
        if (Math.abs(currentPosition.x) >= Math.abs(game.widthOfWindow / 2)) positionOfBall.x = -positionOfBall.x;
        if (currentPosition.y <= -game.heightOfWindow / 2) positionOfBall.y = -positionOfBall.y;
        if (currentPosition.y >= game.heightOfWindow / 2) game.lossOfBall();
        bodyOfBall = new Rectangle(currentPosition.x - radius, currentPosition.y - radius,
                radius * 2, radius * 2);
        currentPointVector = game.paddle.bounceVectorOfPaddle(bodyOfBall);
        positionOfBall.x *= currentPointVector.x;
        positionOfBall.y *= currentPointVector.y;
        for (int i = 0; i < game.blocks.size(); i++) {
            block = game.blocks.get(i);
            currentPointVector = block.bounceVectorOfBlock(bodyOfBall);
            positionOfBall.x *= currentPointVector.x;
            positionOfBall.y *= currentPointVector.y;
            if (currentPointVector.y < 0) {
                game.blocks.remove(block);
                Arkanoid.coefficientUpper++;
                if (Arkanoid.coefficientUpper % 10 == 0) Arkanoid.scoreCoefficient++;
                Arkanoid.counter();
            }
        }
    }

    public void graphicOfBall(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(currentPosition.x - radius, currentPosition.y - radius, radius * 2, radius * 2);
        g.setColor(Color.green);
        g.fillOval(currentPosition.x - radius + 2, currentPosition.y - radius + 2,
                radius * 2 - 4, radius * 2 - 4);
    }
}
