import javafx.scene.control.Button;


public class Cell extends Button {

    private CellType cellType;

    Cell() {
        this.cellType = cellType;
    }

    Cell(CellType cellType) {
        this.cellType = cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public CellType getCellType() {
        return cellType;
    }
}
