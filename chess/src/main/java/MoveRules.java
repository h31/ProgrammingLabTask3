import javax.swing.*;
import java.util.Map;

class MoveRules {
    private Desk.Cell[][] field;
    MoveRules(Desk.Cell[][] field) {
        this.field = field;
    }
    boolean moveIsRight(int x, int y, int toX, int toY, Icon currentIcon,
                        Map<Desk.Figure, ImageIcon> whiteFigures, Map<Desk.Figure, ImageIcon> blackFigures, boolean revertCheckBoxOn) {
        boolean result = false;
        boolean rookStyle = ((x != toX && y == toY) || (y != toY && x == toX));
        boolean bishopStyle = Math.abs(x - toX) == Math.abs(y - toY);
        boolean knightStyle = (Math.abs(x - toX) == 2 && Math.abs(y - toY) == 1)
                || (Math.abs(y - toY) == 2 && Math.abs(x - toX) == 1);
        boolean kingStyle = (Math.abs(x - toX) <= 1 && Math.abs(y - toY) <= 1);
        boolean yourFigure = field[y][x].figureColor == field[toY][toX].figureColor;
        boolean pawnStyle = (Math.abs(toX - x) == 0 && field[toY][toX].getIcon() == null && (toY == 4 && y > toY &&
                field[y - 1][x].getIcon() == null || (toY - y) == -1)) || (Math.abs(toX - x) == 1 &&
                (!yourFigure && field[toY][toX].getIcon() != null) && (toY - y) == -1);
        boolean pawnStyle2 = (Math.abs(toX - x) == 0 && field[toY][toX].getIcon() == null && (toY == 3 && y < toY &&
                field[y + 1][x].getIcon() == null || (toY - y) == 1)) || (Math.abs(toX - x) == 1 &&
                (!yourFigure && field[toY][toX].getIcon() != null) && (toY - y) == 1);

        if (currentIcon.equals(whiteFigures.get(Desk.Figure.rook)) || currentIcon.equals(blackFigures.get(Desk.Figure.rook))) {
            result = rookWayIsFree(x, y, toX, toY) && rookStyle && !yourFigure;
        }
        if (currentIcon.equals(whiteFigures.get(Desk.Figure.bishop)) || currentIcon.equals(blackFigures.get(Desk.Figure.bishop))) {
            result = bishopStyle && bishopWayIsFree(x, y, toX, toY) && !yourFigure;
        }

        if ((currentIcon.equals(whiteFigures.get(Desk.Figure.pawn)) || (currentIcon.equals(blackFigures.get(Desk.Figure.pawn))) && revertCheckBoxOn)) {
            result = pawnStyle;
        }
        if (currentIcon.equals(blackFigures.get(Desk.Figure.pawn)) && !revertCheckBoxOn) {
            result = pawnStyle2;
        }
        if (currentIcon.equals(whiteFigures.get(Desk.Figure.knight)) || currentIcon.equals(blackFigures.get(Desk.Figure.knight))) {
            result = knightStyle;
        }
        if (currentIcon.equals(whiteFigures.get(Desk.Figure.queen)) || currentIcon.equals(blackFigures.get(Desk.Figure.queen))) {
            result = !yourFigure && (bishopStyle && bishopWayIsFree(x, y, toX, toY) || rookStyle && rookWayIsFree(x, y, toX, toY));
        }
        if (currentIcon.equals(whiteFigures.get(Desk.Figure.king)) || currentIcon.equals(blackFigures.get(Desk.Figure.king))) {
            result = kingStyle;
        }

        return result && !yourFigure;
    }


    private boolean rookWayIsFree(int x, int y, int toX, int toY) {
        boolean result = true;
        for (int i = x + 1; i < toX; i++) { // ->
            if (field[y][i].getIcon() != null) {
                result = false;

            }
        }
        for (int i = x - 1; i > toX; i--) { // <-
            if (field[y][i].getIcon() != null) {
                result = false;

            }
        }
        for (int i = y + 1; i < toY; i++) {// v
            if (field[i][x].getIcon() != null) {
                result = false;

            }
        }
        for (int i = y - 1; i > toY; i--) {// ^
            if (field[i][x].getIcon() != null) {
                result = false;

            }
        }
        return result;
    }

    private boolean bishopWayIsFree(int x, int y, int toX, int toY) {
        boolean result = true;
        int j;
        if (toX >= x && toY >= y) {
            j = y + 1;
            for (int i = x + 1; i < toX && j < toY; i++) {
                if (field[j][i].getIcon() != null) {
                    result = false;
                    break;
                }
                j++;
            }
        } else if (toX >= x) {
            j = y - 1;
            for (int i = x + 1; i < toX && j > toY; i++) {
                if (field[j][i].getIcon() != null) {
                    result = false;
                    break;
                }
                j--;
            }
        } else if (toY >= y) {
            j = y + 1;
            for (int i = x - 1; i > toX && j <= toY; i--) {
                if (field[j][i].getIcon() != null) {
                    result = false;
                    break;
                }
                j++;
            }
        } else if (x != 0 && y != 0) {
            j = y - 1;
            for (int i = x - 1; i > toX && j > toY; i--) {
                if (field[j][i].getIcon() != null) {
                    result = false;
                    break;
                }
                j--;
            }
        }
        return result;
    }

}
