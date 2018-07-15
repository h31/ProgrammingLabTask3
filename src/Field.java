import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public class Field {
    private Cell[][] area;
    private int width;
    private int height;
    private int minesCount;
    private int closedCells;

    public Field(int width, int height, int minesCount) {
        this.width = width;
        this.height = height;
        this.minesCount = minesCount;
        area = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                area[i][j] = new Cell(j, i);
            }
        }
        setMines();
        setMinesCount();
        closedCells = width * height;
    }

    private void setMines() {
        int minesRemained = minesCount;
        while (minesRemained != 0) {
            int x = ThreadLocalRandom.current().nextInt(0, width);
            int y = ThreadLocalRandom.current().nextInt(0, height);
            if (!area[y][x].getMine()) {
                area[y][x].setMine();
                minesRemained--;
            }
        }
    }

    private void setMinesCount() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int count = 0;
                for (Cell cell : getNearestCells(j, i)) {
                    if (cell.getMine()) {
                        count++;
                    }
                }
                area[i][j].setNearestMinesCount(count);
            }
        }
    }

    private List<Cell> getNearestCells(int x, int y) {
        LinkedList<Cell> list = new LinkedList<>();
        if (y % 2 == 0) {
            addCell(list, x, y - 2);
            addCell(list, x, y + 2);
            addCell(list, x - 1, y + 1);
            addCell(list, x - 1, y - 1);
            addCell(list, x, y + 1);
            addCell(list, x, y - 1);
        } else {
            addCell(list, x, y - 2);
            addCell(list, x, y + 2);
            addCell(list, x, y - 1);
            addCell(list, x, y + 1);
            addCell(list, x + 1, y - 1);
            addCell(list, x + 1, y + 1);
        }
        return list;
    }

    private void addCell(List<Cell> list, int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return;
        list.add(area[y][x]);
    }

    public boolean clickCell(int x, int y) {
        if (area[y][x].flag() || area[y][x].getVisibility()) return true;
        area[y][x].setVisible();
        closedCells--;
        if (area[y][x].getMine()) {
            return false;
        }
        if (area[y][x].getNearestMinesCount() != 0) {
            return true;
        }
        for (Cell cell : getNearestCells(x, y)) {
            clickCell(cell.getX(), cell.getY());
        }
        return true;
    }

    public Cell[][] getCells() {
        return area;
    }

    public int getClosedCells() {
        return closedCells;
    }

    public void toggleFlag(int x, int y) {
        area[y][x].toggleFlag();
    }

    public int getMinesCount() {
        return minesCount;
    }
}
