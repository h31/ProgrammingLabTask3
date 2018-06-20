import Arkanoid.Arkanoid;
import logic.Block;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class BlocksTest {
    private Rectangle bodyOfBall;
    private int radius = 10;
    private Point point;
    private Block block = new Block();
    private Arkanoid game = new Arkanoid(1280, 720);

    @Test
    void testBodyOfBlock1() {
        bodyOfBall = new Rectangle(300 - radius, 200 - radius, radius * 2, radius * 2);
        point = block.bounceVectorOfBlock(bodyOfBall);
        assertEquals(1, point.y);
    }

    @Test
    void testBodyOfBlock2() {
        bodyOfBall = new Rectangle(530 - radius, -150 - radius, radius * 2, radius * 2);
        block = game.blocks.get(59);
        point = block.bounceVectorOfBlock(bodyOfBall);
        assertEquals(-1, point.y);
    }

    @Test
    void testBodyOfBlock3() {
        bodyOfBall = new Rectangle(10 - radius, -340 - radius, radius * 2, radius * 2);
        block = game.blocks.get(30);
        point = block.bounceVectorOfBlock(bodyOfBall);
        assertEquals(-1, point.y);
    }

    @Test
    void testBodyOfBlock4() {
        bodyOfBall = new Rectangle(-370 - radius, -250 - radius, radius * 2, radius * 2);
        block = game.blocks.get(14);
        point = block.bounceVectorOfBlock(bodyOfBall);
        assertEquals(-1, point.y);
    }

    @Test
    void testBodyOfBlock5() {
        bodyOfBall = new Rectangle(-400 - radius, -250 - radius, radius * 2, radius * 2);
        block = game.blocks.get(14);
        point = block.bounceVectorOfBlock(bodyOfBall);
        assertEquals(1, point.y);
    }
}
