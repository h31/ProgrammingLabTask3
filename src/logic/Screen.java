package logic;

import UI.Cell;
import UI.Point;
import UI.Ship;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private GridPane gridPane;
    private List<Cell> cells = new ArrayList<>();

    public Screen(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    private void createField(List<Ship> ships, int shipType) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                int cellSize = shipType == 1 ? 30 : 60;
                Cell cell  = new Cell(x, y, 0, cellSize);
                for (Ship ship: ships) {
                    for (Point point : ship.getPoints()) {
                        if ((point.x == x) && (point.y == y)) {
                            cell = new Cell(point.x, point.y, shipType, cellSize);
                        }
                    }
                }
                gridPane.add(cell, x, y);
                cells.add(cell);
            }
        }
    }

    public void blockingScreen(boolean block) {
        cells.forEach(cell -> cell.setDisable(block));
    }

    public void shipScreen(List<Ship> ships, int shipType) {
        if (ships.isEmpty()) {
            ships = Ship.randomGenerateShips();
        }
        createField(ships, shipType);
    }

    public List<Cell> getCells() {
        return cells;
    }
}
