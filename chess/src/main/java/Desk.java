
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class Desk {
    private Map<Figure, ImageIcon> whiteFigures = new HashMap<>();
    private Map<Figure, ImageIcon> blackFigures = new HashMap<>();
    private boolean equipped = false;
    boolean turn = true;
    private int currentX;
    private int currentY;
    private int endX;
    private int endY;
    private Icon currentIcon = null;
    private Cell[][] field = new Cell[8][8];
    public enum Team {
        BLACK,
        WHITE,
        NULL
    }

    enum Figure {
        rook,
        knight,
        bishop,
        queen,
        king,
        pawn;
    }


    public Map<Figure, ImageIcon> getWhiteFigures() {
        return whiteFigures;
    }

    public Map<Figure, ImageIcon> getBlackFigures() {
        return blackFigures;
    }

    public Cell[][] getField() {
        return field;
    }

    void setInitialLocation() {
        whiteFigures.put(Figure.rook, new ImageIcon("img/Rook.png"));
        whiteFigures.put(Figure.knight, new ImageIcon("img/Knight.png"));
        whiteFigures.put(Figure.bishop, new ImageIcon("img/Bishop.png"));
        whiteFigures.put(Figure.queen, new ImageIcon("img/Queen.png"));
        whiteFigures.put(Figure.king, new ImageIcon("img/King.png"));
        whiteFigures.put(Figure.pawn, new ImageIcon("img/Pawn.png"));
        blackFigures.put(Figure.rook, new ImageIcon("img/BlackRook.png"));
        blackFigures.put(Figure.knight, new ImageIcon("img/BlackKnight.png"));
        blackFigures.put(Figure.bishop, new ImageIcon("img/BlackBishop.png"));
        blackFigures.put(Figure.queen, new ImageIcon("img/BlackQueen.png"));
        blackFigures.put(Figure.king, new ImageIcon("img/BlackKing.png"));
        blackFigures.put(Figure.pawn, new ImageIcon("img/BlackPawn.png"));

        field[7][0] = new Cell(0, 7, whiteFigures.get(Figure.rook));
        field[7][7] = new Cell(7, 7, whiteFigures.get(Figure.rook));
        field[7][1] = new Cell(1, 7, whiteFigures.get(Figure.knight));
        field[7][6] = new Cell(6, 7, whiteFigures.get(Figure.knight));
        field[7][2] = new Cell(2, 7, whiteFigures.get(Figure.bishop));
        field[7][5] = new Cell(5, 7, whiteFigures.get(Figure.bishop));
        field[7][4] = new Cell(4, 7, whiteFigures.get(Figure.queen));
        field[7][3] = new Cell(3, 7, whiteFigures.get(Figure.king));

        field[0][0] = new Cell(0, 0, blackFigures.get(Figure.rook));
        field[0][7] = new Cell(7, 0, blackFigures.get(Figure.rook));
        field[0][1] = new Cell(1, 0, blackFigures.get(Figure.knight));
        field[0][6] = new Cell(6, 0, blackFigures.get(Figure.knight));
        field[0][2] = new Cell(2, 0, blackFigures.get(Figure.bishop));
        field[0][5] = new Cell(5, 0, blackFigures.get(Figure.bishop));
        field[0][4] = new Cell(4, 0, blackFigures.get(Figure.queen));
        field[0][3] = new Cell(3, 0, blackFigures.get(Figure.king));

        for (int i = 0; i < 8; i++) {
            field[6][i] = new Cell(i, 6, whiteFigures.get(Figure.pawn));
        }
        for (int i = 0; i < 8; i++) {
            field[1][i] = new Cell(i, 1, blackFigures.get(Figure.pawn));
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 6; j++) {
                field[j][i] = new Cell(i, j, null);
            }
        }
    }

    class Cell extends JButton {
        java.awt.Color cellColor;
        int cX;
        int cY;
        Team figureColor;

        Cell(final int x, final int y, final Icon icon) {
            this.cellColor = null;
            this.setPreferredSize(new Dimension(80, 80));
            this.setIcon(icon);
            this.cX = x;
            this.cY = y;
            if (whiteFigures.containsValue(icon)) this.figureColor = Team.WHITE;
            else if (blackFigures.containsValue(icon)) this.figureColor = Team.BLACK;
            else this.figureColor = Team.NULL;

        }


        void equip(boolean moveCheckBoxOn, boolean revertCheckBoxOn) {
            if ((this.getIcon() != null) &&
                    ((this.figureColor.equals(Team.WHITE) && turn) ||
                            (this.figureColor.equals(Team.BLACK) && !turn))) {

                currentIcon = this.getIcon();
                this.setBackground(java.awt.Color.GRAY);
                equipped = true;
                if (moveCheckBoxOn){
                    showMoves(false, currentX, currentY, revertCheckBoxOn);
                }
            }
        }

        void put(boolean moveCheckBoxOn, boolean revertCheckBoxOn) {
            boolean stay = currentX == endX && currentY == endY;
            MoveRules moveRules = new MoveRules(field);
            if (moveRules.moveIsRight(currentX, currentY, endX, endY,
                    currentIcon, whiteFigures, blackFigures, revertCheckBoxOn) || stay) {
                if (moveCheckBoxOn) {
                    showMoves(true, currentX, currentY, revertCheckBoxOn);
                }
                field[currentY][currentX].setIcon(null);
                Team endFColor = field[currentY][currentX].figureColor;
                field[currentY][currentX].setFigureColor(Team.NULL);
                if (this.getIcon() == whiteFigures.get(Figure.king)){
                    blackWin = true;
                }
                else if (this.getIcon() == blackFigures.get(Figure.king)){
                    whiteWin = true;
                }
                this.setIcon(currentIcon);
                this.setFigureColor(endFColor);
                if (!stay) {
                    turn = !turn;
                }
                equipped = false;
                if (!stay && !whiteWin && !blackWin && revertCheckBoxOn) revertDesk();

            } else {
                field[currentY][currentX].setIcon(currentIcon);
                equipped = false;
                showMoves(true, currentX, currentY, revertCheckBoxOn);
            }
        }
        void setFigureColor(Team figureColor1) {
            this.figureColor = figureColor1;
        }
    }

    boolean whiteWin = false;
    boolean blackWin = false;


    void revertDesk() {
        int toI = 8;
        for (int i = 0; i < 4; i++) {
            toI--;
            int toJ = 8;
            for (int j = 0; j < 8; j++) {
                toJ--;
                changeFigure(i, j, toI, toJ);
            }

        }
    }

    private void changeFigure(int i, int j, int toI, int toJ) {
        Icon icon = field[j][i].getIcon();
        Team fColor = field[j][i].figureColor;
        field[j][i].setIcon(field[toJ][toI].getIcon());
        field[j][i].setFigureColor(field[toJ][toI].figureColor);
        field[toJ][toI].setIcon(icon);
        field[toJ][toI].setFigureColor(fColor);
    }

    private void showMoves(boolean cancel, int x, int y, boolean revertCheckBoxOn) {
        MoveRules moveRules = new MoveRules(field);
        java.awt.Color darkBlue = new java.awt.Color(38, 139, 153);
        java.awt.Color lightBlue = new java.awt.Color(128, 246, 255);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (moveRules.moveIsRight(x, y, i, j, currentIcon, whiteFigures, blackFigures, revertCheckBoxOn) && !cancel) {
                    if (field[j][i].cellColor.equals(new java.awt.Color(150, 200, 50))) {
                        field[j][i].setBackground(darkBlue);
                    } else if (field[j][i].cellColor == java.awt.Color.WHITE) {
                        field[j][i].setBackground(lightBlue);
                    }
                } else if (cancel) {
                    field[j][i].setBackground(field[j][i].cellColor);
                }
            }
        }
    }

    void move(int x, int y, boolean moveCheckBoxOn, boolean revertCheckBoxOn) {
        if (!equipped) {
            currentX = field[x][y].cX;
            currentY = field[x][y].cY;
            field[x][y].equip(moveCheckBoxOn, revertCheckBoxOn);
        } else {
            endX = field[x][y].cX;
            endY = field[x][y].cY;
            field[x][y].put(moveCheckBoxOn, revertCheckBoxOn);
        }
    }
}
