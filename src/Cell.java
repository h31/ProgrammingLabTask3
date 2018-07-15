public class Cell {
    private int x;
    private int y;
    private int nearestMines;
    private boolean mine;
    private boolean visible;
    private boolean flag;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        flag = false;
        visible = false;
        nearestMines = 0;
    }

    public void setMine() {
        mine = true;
    }

    public void toggleFlag() {
        flag = !flag;
    }

    public boolean flag() {
        return flag;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setVisible() {
        visible = true;
    }

    public boolean getVisibility() {
        return visible;
    }

    public boolean getMine() {
        return mine;
    }

    public int getNearestMinesCount() {
        return nearestMines;
    }

    public void setNearestMinesCount(int mines) {
        nearestMines = mines;
    }
}
