import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Random;

public class AllShips {
    private ArrayList<Ship> ships = new ArrayList<>();
    private int[] sizeOfShip = {1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
    private boolean hide;

    AllShips(int sizeOfCell, boolean hide) {
        Random alignmentOfShips = new Random();
        for (int i = 0; i < sizeOfShip.length; i++) {
            Ship ship;
            int x = alignmentOfShips.nextInt(10);
            int y = alignmentOfShips.nextInt(10);
            int position = alignmentOfShips.nextInt(2);
            ship = new Ship(x, y, sizeOfShip[i], position);

            if (ship.isWithin(0, 10) && (!ship.isShipTouching(ship))) {
                ships.add(ship);
            }
        }
    }
    void drawingShips(GraphicsContext shipsDraw) {
        for (Ship ship: ships) {
            ship.drawingShip(shipsDraw, 10, hide);
        }
    }
}
