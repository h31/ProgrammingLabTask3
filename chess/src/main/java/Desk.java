
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Desk {
    Map<Figure, ImageIcon> whiteFigures = new HashMap<>();
    Map<Figure, ImageIcon> blackFigures = new HashMap<>();
    MoveRules moveRules = new MoveRules();

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
        whiteFigures.put(Figure.rook, new ImageIcon("img/Rook.png"));
        whiteFigures.put(Figure.knight, new ImageIcon("img/Knight.png"));
        whiteFigures.put(Figure.bishop, new ImageIcon("img/Bishop.png"));
        whiteFigures.put(Figure.queen, new ImageIcon("img/Queen.png"));
        whiteFigures.put(Figure.king, new ImageIcon("img/King.png"));
        whiteFigures.put(Figure.pawn, new ImageIcon("img/Pawn.png"));
        blackFigures.put(Figure.rook, new ImageIcon("img/BlackRook.jpg"));
        blackFigures.put(Figure.knight, new ImageIcon("img/BlackKnight.jpg"));
        blackFigures.put(Figure.bishop, new ImageIcon("img/BlackBishop.jpg"));
        blackFigures.put(Figure.queen, new ImageIcon("img/BlackQueen.jpg"));
        blackFigures.put(Figure.king, new ImageIcon("img/BlackKing.jpg"));
        blackFigures.put(Figure.pawn, new ImageIcon("img/BlackPawn.jpg"));

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
    public class Cell {
        protected JButton button;
        protected java.awt.Color color;
        protected int cX;
        protected int cY;
        protected Color figureColor;
        Cell(final int x, final int y, final Icon icon) {
            this.button = new JButton();
            this.color = null;
            this.button.setPreferredSize(new Dimension(80, 80));
            this.button.setIcon(icon);
            this.cX = x;
            this.cY = y;
            if (whiteFigures.containsValue(icon)) this.figureColor = Color.WHITE;
            else if (blackFigures.containsValue(icon)) this.figureColor = Color.BLACK;

        }


        void equip() {
            if ((this.getIcon() != null) &&
                    (figureColor == Color.WHITE && turn)
                            || (figureColor == Color.BLACK && !turn)){

                currentIcon = this.getIcon();
                this.button.setBackground(java.awt.Color.GRAY);
                equipped = true;
                showMoves(false, currentX, currentY);
            }
        }
        void put() {
            boolean stay = currentX == endX && currentY == endY;
            if (moveRules.moveIsRight(currentX, currentY, endX, endY,
                    currentIcon, field, whiteFigures, blackFigures) || stay) {
                showMoves(true, currentX, currentY);
                field[currentY][currentX].button.setBackground(field[currentY][currentX].color);
                field[currentY][currentX].button.setIcon(null);
                this.setImageIcon(currentIcon);
                cursor = Cursor.getDefaultCursor();
                if (!stay) {
                    turn = !turn;
                }
                if (blackFigures.containsValue(currentIcon) && endY == 7) {
                    changeFigure(endX, endY, endX, endY, currentColor, blackFigures.get(Figure.rook));
                } else if (whiteFigures.containsValue(currentIcon) && endY == 0) {
                    changeFigure(endX, endY, endX, endY, currentColor, whiteFigures.get(Figure.rook));
                }
                equipped = false;
                //if (!stay) revertDesk();
            }
            else {
                //field[currentY][currentX].setImageIcon(currentIcon);
                equipped = false;
                //showMoves(true, currentX, currentY);
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
                //Color color = field[j][i].endColor;
                //if (field[j][i].endColor == null) color = null;
                int x = field[j][i].cX;
                int y = field[j][i].cY;
                //JButton btn = field[j][i].button;
                //Color endColor = field[toJ][toI].endColor;
                //if (field[toJ][toI].endColor == null) endColor = null;
                //changeFigure(i, j, toI, toJ, endColor, field[toJ][toI].getIcon());
                field[j][i].setImageIcon(field[toJ][toI].getIcon());
                //field[j][i].setColor(endColor);
                field[j][i].cX = field[toJ][toI].cX;
                field[j][i].cY = field[toJ][toI].cY;
                //field[j][i].button = field[toJ][toI].button;
                field[toJ][toI].setImageIcon(icon);
                //field[toJ][toI].setColor(color);
                field[toJ][toI].cX = x;
                field[toJ][toI].cY = y;
                //field[j][i].button = btn;
            }

            for (int j = 4; j < 8; j++) {
                toJ--;
                Icon icon = field[j][i].getIcon();
                //Color color = field[j][i].endColor;
                //if (field[j][i].endColor == null) color = null;
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

        }
    }

    public void changeFigure(int x, int y, int toX, int toY, Color color, Icon icon) {
        field[y][x].setImageIcon(icon);
        field[y][x].cX = field[toY][toX].cX;
        field[y][x].cY = field[toY][toX].cY;
    }

    public void showMoves(boolean cancel, int x, int y){

        java.awt.Color darkBlue = new java.awt.Color(0, 119,128);
        java.awt.Color lightBlue = new java.awt.Color(128, 246, 255);
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                boolean stay =  currentX == j && currentY == i;
                if (moveRules.moveIsRight(x, y, i, j, currentIcon, field, whiteFigures, blackFigures) && !cancel && !stay){
                    if (field[j][i].color == java.awt.Color.BLACK) {
                        field[j][i].button.setBackground(darkBlue);
                    }
                    else if (field[j][i].color == java.awt.Color.WHITE){
                        field[j][i].button.setBackground(lightBlue);
                    }
                }
                else if (cancel){
                    field[j][i].button.setBackground(field[j][i].color);

                }
            }
        }
    }

    public void move(int x, int y){
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
