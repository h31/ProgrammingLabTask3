public class Minesweeper {
    private Field field;
    private boolean gameOver;
    private boolean win;
    private int width;
    private int height;
    private int minesCount;

    public Minesweeper(int width, int height, int minesCount) {
        setGameParams(width, height, minesCount);
        field = new Field(width, height, minesCount);
    }

    public boolean gameOver() {
        return gameOver;
    }

    public boolean win() {
        return win;
    }

    public void clickCell(int x, int y) {
        if (gameOver || win) return;
        if (!field.clickCell(x, y)) gameOver = true;
        if (field.getClosedCells() == field.getMinesCount()) win = true;
        Controller.refresh();
    }

    public void toggleFlag(int x, int y) {
        field.toggleFlag(x, y);
        Controller.refresh();
    }

    public Field getField() {
        return field;
    }

    public void restart() {
        field = new Field(width, height, minesCount);
        gameOver = false;
        win = false;
        Controller.init(width, height);
        Controller.refresh();
    }

    public void setGameParams(int width, int height, int minesCount) {
        this.width = width;
        this.height = height;
        this.minesCount = minesCount;
    }
}
