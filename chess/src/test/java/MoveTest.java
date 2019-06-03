import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class MoveTest {
    private Desk desk;
    private void doNothing() {
        //nothing
    }

    @Before
    public void initDesk() {
        desk = new Desk();
        desk.setInitialLocation();
    }
    @Test
    public void test() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    doNothing();
                } catch (Exception e) {
                    System.out.println("Something went wrong...");
                }
            }
        }
    }

    @Test
    public void moveIsRightTest(){
        MoveRules moveRules = new MoveRules(desk.getField());
       boolean moveIsRight =  moveRules.moveIsRight(1, 1, 0, 0,
                new ImageIcon("img/Pawn.png"),
                desk.getWhiteFigures(), desk.getBlackFigures(), true);
        Assert.assertFalse(moveIsRight);
    }

}
