import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Cell {

    private int x;
    private int y;
    private Color color;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
        color = Color.GRAY;
    }

    boolean isHit(int x, int y) {
        if (this.x == x && this.y == y) {
            color = Color.RED;
            return true;
        }
        return false;
    }

    boolean isAlive() {
        return color != Color.RED;
    }

    void drawingGridlines(GraphicsContext gridlines, int sizeOfCell, boolean hide) {
        if (!hide || color == Color.RED) {
            gridlines.setFill(color);
            gridlines.fillRect(x*sizeOfCell+1, y*sizeOfCell+1, sizeOfCell-2, sizeOfCell-2);
        }
    }
}
