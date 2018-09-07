import java.awt.*;

class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState gameState;
    private final int PICTURE_WIDTH = 46;
    private final int PICTURE_HEIGHT = 52;


    GameState getGameState() {
        return gameState;
    }

    Game(int cols, int rows, int bombs){
        Field.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    void start(){
        bomb.start();
        flag.start();
        gameState = GameState.PLAYED;
    }

    Cell getCell(Coord coord){
        if (flag.get(coord) == Cell.CELL)
            return bomb.get(coord);
        else
            return flag.get(coord);
    }

    private void checkWin(){
        if (gameState == GameState.PLAYED)
            if (flag.getNumberOfClosedCell() == bomb.getTotalBombs())
                gameState = GameState.WINNER;
    }

    void pressLeftButton(Coord coord) {
        if(gameOver()) return;
        openCell(coord);
        checkWin();
    }

    private void openCell(Coord coord){
        if(flag.get(coord) == null) return;
        switch (flag.get(coord)){
            case CELL : setOpenedToClosedCellsAroundNumber(coord); break;
            case FLAG : break;
            case CLOSEDCELL : switch (bomb.get(coord)){
                case ZERO : openCellsAround(coord); break;
                case BOMB : openBomb(coord); break;
                default : flag.setOpenedToCell(coord); break;
            }
        }
    }

    Coord findPressedCoord(int x, int y) {
        float height25 = PICTURE_HEIGHT * 0.25f;
        float height75 = PICTURE_HEIGHT * 0.75f;
        float width50 = PICTURE_WIDTH * 0.50f;
        int firstRow = (int) Math.floor((y - height25) / height75);
        int lastRow = (int) Math.floor(y / height75);
        int colInEvenRow = (int) Math.floor(x / PICTURE_WIDTH);
        int colInOddRow = (int) Math.floor((x - width50) / PICTURE_WIDTH);
        Point pressedPoint = new Point(x, y);
        double minDistanceToPressPoint = Double.MAX_VALUE;
        Coord pressedCoord = new Coord(-1, -1);
        for (int row = firstRow; row <= lastRow; row++) {
            int col = (row % 2 == 0) ? colInEvenRow : colInOddRow;
            Point center = findCenter(row, col);
            double distanceToPressPoint = center.distance(pressedPoint);
            if (distanceToPressPoint < minDistanceToPressPoint) {
                minDistanceToPressPoint = distanceToPressPoint;
                pressedCoord = new Coord(row, col);
            }
        }
        return pressedCoord;
    }

    private Point findCenter(int col, int row) {
        int centerX = (int) ((row + 1) *  - ((col % 2 == 0) ? PICTURE_WIDTH * 0.50f : 0));
        int centerY = (int) ((col + 1) * PICTURE_HEIGHT * 0.75f - PICTURE_HEIGHT * 0.25f);
        return new Point(centerX, centerY);
    }

    private void setOpenedToClosedCellsAroundNumber(Coord coord){
        if (bomb.get(coord) != Cell.BOMB)
            if (flag.getNumberOfFlaggedCellsAround(coord) == bomb.get(coord).getNumber())
                for (Coord around : Field.getCoordAround(coord))
                    if (flag.get(around) == Cell.CLOSEDCELL)
                        openCell(around);
    }

    private void openBomb(Coord exploded) {
        gameState = GameState.BOMBED;
        flag.setBombedToCell(exploded);
        for (Coord coord : Field.getAllCoords())
            if (bomb.get(coord) == Cell.BOMB)
                flag.setOpenedToClosedBombCell(coord);
            else
                flag.setNoBombToFlaggedCell(coord);
    }

    private void openCellsAround(Coord coord) {
        flag.setOpenedToCell(coord);
        for(Coord around : Field.getCoordAround(coord))
            if(flag.get(around) != Cell.CELL)
                openCell(around);
    }

    void pressRightButton(Coord coord) {
        if(gameOver()) return;
        flag.toggleFlag(coord);
    }

    private boolean gameOver() {
        if(gameState == GameState.PLAYED)
            return false;
        start();
        return true;
    }
}
