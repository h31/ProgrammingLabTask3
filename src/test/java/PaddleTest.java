import Arkanoid.Arkanoid;
import logic.Block;
import logic.Paddle;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaddleTest {
    private Rectangle bodyOfBall;
    private int radius = 10;
    private Point point;
    private Block block = new Block();
    private Arkanoid game = new Arkanoid(1280, 720);
    private Paddle paddle = new Paddle(game);

    @Test
    void testBodyOfPaddle1() {
        bodyOfBall = new Rectangle(-50 - radius, 340 - radius, radius * 2, radius * 2);
        point = paddle.bounceVectorOfPaddle(bodyOfBall);
        assertEquals(-1, point.y);
    }

    @Test
    void testBodyOfPaddle2() {
        bodyOfBall = new Rectangle(50 - radius, 50 - radius, radius * 2, radius * 2);
        point = block.bounceVectorOfBlock(bodyOfBall);
        assertEquals(1, point.y);
    }
}
