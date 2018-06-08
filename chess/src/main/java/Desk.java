
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Desk {
    Map<Figure, ImageIcon> whiteFigures = new HashMap<>();
    Map<Figure, ImageIcon> blackFigures = new HashMap<>();


    boolean equipped = false;
    boolean turn = true;
    int currentX;
    int currentY;
    int endX;
    int endY;
    Icon currentIcon = null;

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

    Color currentColor;

    //public JTabbedPane whiteHeaven = new JTabbedPane();
    //public JTabbedPane blackHeaven = new JTabbedPane();
    Cursor cursor = Cursor.getDefaultCursor();
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    Cell[][] field = new Cell[8][8];

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
        // moveRules = new MoveRules(field);
    }

    //MoveRules moveRules;
    public class Cell extends JButton {
        //protected JButton button;
        protected java.awt.Color cellColor;
        protected int cX;
        protected int cY;
        protected Team figureColor;

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


        void equip() {
            if ((this.getIcon() != null) &&
                    ((this.figureColor.equals(Team.WHITE) && turn) ||
                            (this.figureColor.equals(Team.BLACK) && !turn))) {

                currentIcon = this.getIcon();
                this.setBackground(java.awt.Color.GRAY);
                equipped = true;
                showMoves(false, currentX, currentY);
            }
        }

        void put() {
            boolean stay = currentX == endX && currentY == endY;
            MoveRules moveRules = new MoveRules(field);
            if (moveRules.moveIsRight(currentX, currentY, endX, endY,
                    currentIcon, whiteFigures, blackFigures) || stay) {
                showMoves(true, currentX, currentY);
                field[currentY][currentX].setBackground(field[currentY][currentX].cellColor);
                field[currentY][currentX].setIcon(null);
                Team endFColor = field[currentY][currentX].figureColor;
                field[currentY][currentX].setFigureColor(Team.NULL);
               /* if (this.getIcon().equals(whiteFigures.get(Figure.king))){
                    whiteWin = true;
                }
                if (this.getIcon().equals(blackFigures.get(Figure.king))){
                    blackWin = true;
                }
                */
                this.setIcon(currentIcon);
                this.setFigureColor(endFColor);
                //cursor = Cursor.getDefaultCursor();
                if (!stay) {
                    turn = !turn;
                }
                /*
                if (blackFigures.containsValue(currentIcon) && endY == 7) {
                    changeFigure(endX, endY, endX, endY, currentColor, blackFigures.get(Figure.rook));
                } else if (whiteFigures.containsValue(currentIcon) && endY == 0) {
                    changeFigure(endX, endY, endX, endY, currentColor, whiteFigures.get(Figure.rook));
                }
                */
                equipped = false;
                if (!stay) revertDesk();

            } else {
                field[currentY][currentX].setIcon(currentIcon);
                equipped = false;
                showMoves(true, currentX, currentY);
            }
        }

        public void setFigureColor(Team figureColor1) {
            this.figureColor = figureColor1;
        }
    }

    public boolean whiteWin = false;

    public boolean blackWin = false;


    void revertDesk() {
        int toI = 8;
        for (int i = 0; i < 4; i++) {
            toI--;
            int toJ = 8;
            /*for (int j = 0; j < 4; j++) {
                toJ--;
                changeFigure(i,j,toI,toJ);
            }
*/
            for (int j = 0; j < 8; j++) {
                toJ--;
                changeFigure(i, j, toI, toJ);
            }

        }
    }

    public void changeFigure(int i, int j, int toI, int toJ) {
        Icon icon = field[j][i].getIcon();
        Team fColor = field[j][i].figureColor;
        field[j][i].setIcon(field[toJ][toI].getIcon());
        field[j][i].setFigureColor(field[toJ][toI].figureColor);
        field[toJ][toI].setIcon(icon);
        field[toJ][toI].setFigureColor(fColor);
    }

    public void showMoves(boolean cancel, int x, int y) {
        MoveRules moveRules = new MoveRules(field);
        java.awt.Color darkBlue = new java.awt.Color(38, 139, 153);
        java.awt.Color lightBlue = new java.awt.Color(128, 246, 255);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (moveRules.moveIsRight(x, y, i, j, currentIcon, whiteFigures, blackFigures) && !cancel) {
                    if (field[j][i].cellColor.equals(new java.awt.Color(75, 22, 50))) {
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

    public void move(int x, int y) {
        //moveRules = new MoveRules(field);
        if (!equipped) {
            currentX = field[x][y].cX;
            currentY = field[x][y].cY;
            field[x][y].equip();
        } else {
            endX = field[x][y].cX;
            endY = field[x][y].cY;
            field[x][y].put();
        }
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
