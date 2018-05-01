import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Ship {

    private ArrayList<Cell> cells = new ArrayList<>();

    Ship(int x, int y, int length, int position) {
        for (int i = 0; i < length; i++) {
            cells.add(new Cell(x + i * ((position == 1) ? 0 : 1), y + i * ((position == 1) ? 1 : 0)));
        }
    }

    boolean isWithin(int top, int bottom) {
        for (Cell cell : cells) {
            if ((cell.getX() < top) && (cell.getY() < top) &&
                    (cell.getX() > bottom) && (cell.getY() > bottom)) {
                return true;
            }
        }
        return false;
    }

    boolean isCellTouching(Cell workingCell) {
        for (Cell cell : cells) {
            for (int distanceX = -1; distanceX < 2; distanceX++) {
                for (int distanceY = -1; distanceY < 2; distanceY++) {
                    if ((workingCell.getX() == cell.getX() + distanceX) &&
                            (workingCell.getY() == cell.getY() + distanceY)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean isShipTouching(Ship workingShip) {
        for (Cell cell : cells) {
            if (workingShip.isCellTouching(cell)) {
                return true;
            }
        }
        return false;
    }

    boolean isHit(int x, int y) {
        for (Cell cell : cells) {
            if (cell.isHit(x, y)) {
                return true;
            }
        }
        return false;
    }

    boolean isAlive() {
        for (Cell cell : cells) {
            if (cell.isAlive()) {
                return true;
            }
        }
        return false;
    }

    void drawingShip(GraphicsContext ship, int sizeOfCell, boolean hide) {
        for (Cell cell : cells) {
            cell.drawingGridlines(ship, sizeOfCell, hide);
        }
    }
}