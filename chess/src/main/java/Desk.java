
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Desk {
    Map<Figure, ImageIcon> whiteArmy = new HashMap<>();
    Map<Figure, ImageIcon> blackArmy = new HashMap<>();


    boolean equipped = false;
    boolean turn = true;
    int currentX;
    int currentY;
    int endX;
    int endY;
    Icon currentIcon = null;

    private enum Color {
        BLACK,
        WHITE
    }

    enum Figure {
        rook,
        knight,
        bishop,
        queen,
        king,
        pawn;
    }

    Color currentColor;

    //public JTabbedPane whiteHeaven = new JTabbedPane();
    //public JTabbedPane blackHeaven = new JTabbedPane();
    Cursor cursor = Cursor.getDefaultCursor();
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    Cell[][] field = new Cell[8][8];

    void setInitialLocation() {
        whiteArmy.put(Figure.rook, new ImageIcon("img/Rook.png"));
        whiteArmy.put(Figure.knight, new ImageIcon("img/Knight.png"));
        whiteArmy.put(Figure.bishop, new ImageIcon("img/Bishop.png"));
        whiteArmy.put(Figure.queen, new ImageIcon("img/Queen.png"));
        whiteArmy.put(Figure.king, new ImageIcon("img/king.png"));
        whiteArmy.put(Figure.pawn, new ImageIcon("img/Pawn.png"));
        blackArmy.put(Figure.rook, new ImageIcon("img/BlackRook.png"));
        blackArmy.put(Figure.knight, new ImageIcon("img/BlackKnight.png"));
        blackArmy.put(Figure.bishop, new ImageIcon("img/BlackBishop.png"));
        blackArmy.put(Figure.queen, new ImageIcon("img/BlackQueen.png"));
        blackArmy.put(Figure.king, new ImageIcon("img/BlackKing.png"));
        blackArmy.put(Figure.pawn, new ImageIcon("img/BlackPawn.png"));

        field[7][0] = new Cell(0, 7, whiteArmy.get(Figure.rook), Color.WHITE);
        field[7][7] = new Cell(7, 7, whiteArmy.get(Figure.rook), Color.WHITE);
        field[7][1] = new Cell(1, 7, whiteArmy.get(Figure.knight), Color.WHITE);
        field[7][6] = new Cell(6, 7, whiteArmy.get(Figure.knight), Color.WHITE);
        field[7][2] = new Cell(2, 7, whiteArmy.get(Figure.bishop), Color.WHITE);
        field[7][5] = new Cell(5, 7, whiteArmy.get(Figure.bishop), Color.WHITE);
        field[7][4] = new Cell(4, 7, whiteArmy.get(Figure.queen), Color.WHITE);
        field[7][3] = new Cell(3, 7, whiteArmy.get(Figure.king), Color.WHITE);

        field[0][0] = new Cell(0, 0, blackArmy.get(Figure.rook), Color.BLACK);
        field[0][7] = new Cell(7, 0, blackArmy.get(Figure.rook), Color.BLACK);
        field[0][1] = new Cell(1, 0, blackArmy.get(Figure.knight), Color.BLACK);
        field[0][6] = new Cell(6, 0, blackArmy.get(Figure.knight), Color.BLACK);
        field[0][2] = new Cell(2, 0, blackArmy.get(Figure.bishop), Color.BLACK);
        field[0][5] = new Cell(5, 0, blackArmy.get(Figure.bishop), Color.BLACK);
        field[0][4] = new Cell(4, 0, blackArmy.get(Figure.queen), Color.BLACK);
        field[0][3] = new Cell(3, 0, blackArmy.get(Figure.king), Color.BLACK);

        for (int i = 0; i < 8; i++) {
            field[6][i] = new Cell(i, 6, whiteArmy.get(Figure.pawn), Color.WHITE);
        }
        for (int i = 0; i < 8; i++) {
            field[1][i] = new Cell(i, 1, blackArmy.get(Figure.pawn), Color.BLACK);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 6; j++) {
                field[j][i] = new Cell(i, j, null, null);
            }
        }
        /*whiteHeaven.setPreferredSize(new Dimension(200, 200));
        blackHeaven.setPreferredSize(new Dimension(200, 200));
        whiteHeaven.setLayout(new BorderLayout());
        blackHeaven.setLayout(new BorderLayout()); */
    }


    private boolean rookWayIsFree(int x, int y, int toX, int toY) {
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

    private boolean globalMoveRules(int x, int y, int toX, int toY, Color endColor) {
        boolean moveIsRight = false;
        boolean yourFigure = whiteArmy.containsValue(currentIcon) && whiteArmy.containsValue(field[toY][toX].getIcon()) ||
                blackArmy.containsValue(currentIcon) && blackArmy.containsValue(field[toY][toX].getIcon());
        boolean rookStyle = ((x != toX && y == toY) || (y != toY && x == toX));
        boolean bishopStyle = Math.abs(x - toX) == Math.abs(y - toY);
        boolean knightStyle = (Math.abs(x - toX) == 2 && Math.abs(y - toY) == 1)
                || (Math.abs(y - toY) == 2 && Math.abs(x - toX) == 1);
        boolean queenStyle = rookStyle || bishopStyle;
        boolean kingStyle = (Math.abs(x - toX) <= 1 && Math.abs(y - toY) <= 1);
        boolean pawnStyle;

        if (blackArmy.containsValue(currentIcon)) {
            pawnStyle = (Math.abs(toX - x) == 0 && field[toY][toX].button.getIcon() == null && (toY == 3 || (toY - y) == 1)) ||
                    (Math.abs(toX - x) == 1 && whiteArmy.containsValue(field[toY][toX].getIcon()) && (toY - y) == 1);

        } else {
            pawnStyle = (Math.abs(toX - x) == 0 && field[toY][toX].button.getIcon() == null && (toY == 4 || (toY - y) == -1)) ||
                    (Math.abs(toX - x) == 1 && blackArmy.containsValue(field[toY][toX].getIcon()) && (toY - y) == -1);
        }

        if (currentIcon.equals(whiteArmy.get(Figure.rook)) || currentIcon.equals(blackArmy.get(Figure.rook)))
            moveIsRight = rookStyle &&
                    rookWayIsFree(x, y, toX, toY);
        else if (currentIcon.equals(whiteArmy.get(Figure.bishop)) || currentIcon.equals(blackArmy.get(Figure.bishop)))
            moveIsRight = bishopStyle &&
                    bishopWayIsFree(x, y, toX, toY);
        else if (currentIcon.equals(whiteArmy.get(Figure.pawn)) || currentIcon.equals(blackArmy.get(Figure.pawn))) {
            moveIsRight = pawnStyle;
            //pawnEvolution(x,y,toX,toY);
        } else if (currentIcon.equals(whiteArmy.get(Figure.knight)) || currentIcon.equals(blackArmy.get(Figure.knight)))
            moveIsRight = knightStyle;
        else if (currentIcon.equals(whiteArmy.get(Figure.queen)) || currentIcon.equals(blackArmy.get(Figure.queen))) {
            moveIsRight = queenStyle;
            if (rookStyle) {
                moveIsRight = queenStyle && rookWayIsFree(x, y, toX, toY);
            } else if (bishopStyle) {
                moveIsRight = queenStyle && bishopWayIsFree(x, y, toX, toY);
            }
        } else if (currentIcon.equals(whiteArmy.get(Figure.king)) || currentIcon.equals(blackArmy.get(Figure.king)))
            moveIsRight = kingStyle;
        return moveIsRight && !yourFigure;
    }


    public class Cell {
        protected JButton button;
        protected Color endColor;
        protected int cX;
        protected int cY;

        Cell(final int x, final int y, final Icon icon, Color color) {
            this.button = new JButton();

            this.button.setPreferredSize(new Dimension(80, 80));
            this.button.setIcon(icon);
            this.endColor = color;
            this.cX = x;
            this.cY = y;
        }

        void setColor(Color color) {
            this.endColor = color;
        }

        void equip() {
            if (this.getIcon() != null &&
                    (whiteArmy.containsValue(field[currentY][currentX].getIcon()) && turn
                            || blackArmy.containsValue(field[currentY][currentX].getIcon()) && !turn)) {
                currentIcon = this.getIcon();
                currentColor = this.endColor;
                this.endColor = null;
                //this.button.setText("null");
                this.setImageIcon(null);
                //mFrame.setCursor(toolkit.createCustomCursor(rookImg.getImage(), new Point(0, 0), null));
                equipped = true;
            }
        }

        void put() {
            boolean stay = currentX == endX && currentY == endY;
            if (globalMoveRules(currentX, currentY, endX, endY, endColor)
                    || stay) {
                this.setImageIcon(currentIcon);
                this.setColor(currentColor);
                //this.button.setText(currentColor.name());
                cursor = Cursor.getDefaultCursor();
                if (!stay) {
                    turn = !turn;
                }
                if (blackArmy.containsValue(currentIcon) && endY == 7) {
                    changeFigure(endX, endY, endX, endY, currentColor, blackArmy.get(Figure.king));
                } else if (whiteArmy.containsValue(currentIcon) && endY == 0) {
                    changeFigure(endX, endY, endX, endY, currentColor, whiteArmy.get(Figure.king));
                }
                equipped = false;
                //if (!stay) revertDesk();
            }
        }

        JButton getButton() {
            return this.button;
        }

        void setImageIcon(Icon icon) {
            this.button.setIcon(icon);
        }

        Icon getIcon() {
            return this.button.getIcon();
        }
    }

    void revertDesk() {
        int toI = 8;
        for (int i = 0; i < 4; i++) {
            toI--;
            int toJ = 8;
            for (int j = 0; j < 4; j++) {
                toJ--;
                Icon icon = field[j][i].getIcon();
                Color color = field[j][i].endColor;
                if (field[j][i].endColor == null) color = null;
                int x = field[j][i].cX;
                int y = field[j][i].cY;
                JButton btn = field[j][i].button;
                //Color endColor = field[toJ][toI].endColor;
                //if (field[toJ][toI].endColor == null) endColor = null;
                //changeFigure(i, j, toI, toJ, endColor, field[toJ][toI].getIcon());
                field[j][i].setImageIcon(field[toJ][toI].getIcon());
                //field[j][i].setColor(endColor);
                field[j][i].cX = field[toJ][toI].cX;
                field[j][i].cY = field[toJ][toI].cY;
                field[j][i].button = field[toJ][toI].button;
                field[toJ][toI].setImageIcon(icon);
                //field[toJ][toI].setColor(color);
                field[toJ][toI].cX = x;
                field[toJ][toI].cY = y;
                field[j][i].button = btn;
            }

            for (int j = 4; j < 8; j++) {
                toJ--;
                Icon icon = field[j][i].getIcon();
                Color color = field[j][i].endColor;
                if (field[j][i].endColor == null) color = null;
                int x = field[j][i].cX;
                int y = field[j][i].cY;
                JButton btn = field[j][i].button;
                Color endColor = field[toJ][toI].endColor;
                if (field[toJ][toI].endColor == null) endColor = null;
                //changeFigure(i, j, toI, toJ, endColor, field[toJ][toI].getIcon());
                field[j][i].setImageIcon(field[toJ][toI].getIcon());
                field[j][i].setColor(endColor);
                field[j][i].cX = field[toJ][toI].cX;
                field[j][i].cY = field[toJ][toI].cY;
                field[j][i].button = field[toJ][toI].button;
                field[toJ][toI].setImageIcon(icon);
                field[toJ][toI].setColor(color);
                field[toJ][toI].cX = x;
                field[toJ][toI].cY = y;
                field[j][i].button = btn;
            }

        }
    }

    public void changeFigure(int x, int y, int toX, int toY, Color color, Icon icon) {
        field[y][x].setImageIcon(icon);
        field[y][x].setColor(color);
        field[y][x].cX = field[toY][toX].cX;
        field[y][x].cY = field[toY][toX].cY;
    }

    /*
        private void pawnEvolution(int x, int y, int toX, int toY) {
            if (blackArmy.containsValue(field[y][x].getIcon()) && toY == 7) {
                changeFigure(x, y, toX, toY, field[y][x].endColor, whiteArmy.get(Figure.king));
            } else if (Objects.equals(field[y][x].endColor, Color.WHITE) && toY == 0) {
                changeFigure(x, y, toX, toY, field[y][x].endColor, whiteArmy.get(Figure.king));
            }
        }
    */



}
