import org.junit.jupiter.api.Test;

public class MoveTest {

    private void doNothing() {
        //nothing
    }

    @Test
    public void test() {
        Desk desk = new Desk();
        desk.setInitialLocation();
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

}
