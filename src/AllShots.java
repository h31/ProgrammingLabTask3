import java.util.ArrayList;

public class AllShots {
    private ArrayList<Shot> shots;

    AllShots(int sizeOfCell) {
        shots = new ArrayList<>();
    }

    void addShot (int x, int y, boolean shot) {
        shots.add(new Shot (x, y, shot));
    }

    boolean hitSamePlace(int x, int y) {
        for (Shot shot: shots) {
            if ((shot.getX() == x) && (shot.getY() == y) && (shot.isShot())) {
                return true;
            }
        }
        return false;
    }
}
