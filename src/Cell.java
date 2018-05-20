import javafx.scene.control.Button;


public class Cell extends Button {

    private CellType cellType;

    private void setCellType() {
        switch (cellType) {
            case EMPTY:
                this.getStyleClass().add("empty");
                this.setId("empty");
                break;
            case SHIP:
                this.getStyleClass().add("user_ship");
                this.setId("user_ship");
                this.setDisable(false);
                break;
            case INJURED_SHIP:
                this.getStyleClass().add("injured_ship");
                this.setId("injured_ship");
                break;
            case MISS:
                this.getStyleClass().add("miss");
                this.setId("miss");
                break;
            case HIDDEN_SHIP:
                this.getStyleClass().add("hidden_ship");
                this.setId("hidden_ship");
                break;
        }
    }

    private void setCellType(int cellType) {
        switch (cellType) {
            case 0:
                this.cellType = CellType.EMPTY;
                break;
            case 1:
                this.cellType = CellType.SHIP;
                break;
            case 2:
                this.cellType = CellType.INJURED_SHIP;
                break;
            case 3:
                this.cellType = CellType.MISS;
                break;
            case 4:
                this.cellType = CellType.HIDDEN_SHIP;
                break;
        }
        setCellType();
    }


    public Cell(int x, int y, int cellType, int size) {
        this.setMinSize(size, size);
        setCellType(cellType);
    }

    public Cell (int x, int y, int cellType) {
        this(x, y, cellType, 60);
    }
}
