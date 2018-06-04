import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MoveRules {
    //Desk desk = new Desk();


    public boolean moveIsRight(int x, int y, int toX, int toY, Icon currentIcon, Desk.Cell[][] field,
                               Map<Desk.Figure, ImageIcon> whiteFigures, Map<Desk.Figure, ImageIcon> blackFigures) {
        boolean result = false;
        boolean yourFigure = whiteFigures.containsValue(currentIcon) && whiteFigures.containsValue(field[toY][toX].getIcon()) ||
                blackFigures.containsValue(currentIcon) && blackFigures.containsValue(field[toY][toX].getIcon());
        boolean rookStyle = ((x != toX && y == toY) || (y != toY && x == toX));
        boolean bishopStyle = Math.abs(x - toX) == Math.abs(y - toY);
        boolean knightStyle = (Math.abs(x - toX) == 2 && Math.abs(y - toY) == 1)
                || (Math.abs(y - toY) == 2 && Math.abs(x - toX) == 1);
        //boolean queenStyle = rookStyle || bishopStyle;
        boolean kingStyle = (Math.abs(x - toX) <= 1 && Math.abs(y - toY) <= 1);
        boolean pawnStyle;

        if (blackFigures.containsValue(currentIcon) && currentIcon.equals(blackFigures.get(Desk.Figure.pawn))) {
            result = (Math.abs(toX - x) == 0 && field[toY][toX].button.getIcon() == null && (toY == 3 && toY > y || (toY - y) == 1)) ||
                    (Math.abs(toX - x) == 1 && whiteFigures.containsValue(field[toY][toX].getIcon()) && (toY - y) == 1);

        } else if (currentIcon.equals(whiteFigures.get(Desk.Figure.pawn))){
            result = (Math.abs(toX - x) == 0 && field[toY][toX].button.getIcon() == null && (toY == 4 && y > toY || (toY - y) == -1)) ||
                    (Math.abs(toX - x) == 1 && blackFigures.containsValue(field[toY][toX].getIcon()) && (toY - y) == -1);
        }

        if (currentIcon.equals(whiteFigures.get(Desk.Figure.rook)) || currentIcon.equals(blackFigures.get(Desk.Figure.rook)))
            result = rookStyle && rookWayIsFree(x, y, toX, toY, field);
        else if (currentIcon.equals(whiteFigures.get(Desk.Figure.bishop)) || currentIcon.equals(blackFigures.get(Desk.Figure.bishop)))
            result = bishopStyle && bishopWayIsFree(x, y, toX, toY, field);
        //else if (currentIcon.equals(whiteFigures.get(Desk.Figure.pawn)) || currentIcon.equals(blackFigures.get(Desk.Figure.pawn))) {
          //  result = pawnStyle;
            //pawnEvolution(x,y,toX,toY);
        //}
        else if (currentIcon.equals(whiteFigures.get(Desk.Figure.knight)) || currentIcon.equals(blackFigures.get(Desk.Figure.knight)))
            result = knightStyle;
        else if (currentIcon.equals(whiteFigures.get(Desk.Figure.queen)) || currentIcon.equals(blackFigures.get(Desk.Figure.queen))) {
            if (rookStyle) {
                result = rookWayIsFree(x, y, toX, toY, field);
            } else if (bishopStyle) {
                result = bishopWayIsFree(x, y, toX, toY, field);
            }
        } else if (currentIcon.equals(whiteFigures.get(Desk.Figure.king)) || currentIcon.equals(blackFigures.get(Desk.Figure.king)))
            result = kingStyle;
        return result && !yourFigure;
    }

    private boolean rookWayIsFree(int x, int y, int toX, int toY, Desk.Cell[][] field) {
        boolean result = true;
        for (int i = x; i < toX; i++) { //-+
            if (field[y][i].getIcon() != null) {
                result = false;
            }
        }
        for (int i = x; i > toX; i--) { //+-
            if (field[y][i].getIcon() != null) {
                result = false;
            }
        }
        for (int i = y; i < toY; i++) {//++
            if (field[i][x].getIcon() != null) {
                result = false;
            }
        }
        for (int i = y; i > toY; i--) {//--
            if (field[i][x].getIcon() != null) {
                result = false;
            }
        }
        return result;
    }

    private boolean bishopWayIsFree(int x, int y, int toX, int toY, Desk.Cell[][] field) {
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
        } else {
            j = y;
            for (int i = x; i > toX && j > toY; i--) {
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
