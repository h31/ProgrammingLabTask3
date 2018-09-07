import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MineSweeper extends JFrame {

    private final int PICTURE_WIDTH = 46;
    private final int PICTURE_HEIGHT = 52;
    private Game game;

    public static void main(String[] args) {
        initSettings();
    }

    MineSweeper(int col, int row, int bomb){
        game = new Game(col, row, bomb);
        game.start();
        Field.setSize(new Coord(col, row));
        initPanel(col, row);
        initFrame();
        setImage();
        setVisible(true);
    }

    private void initFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("MineSweeper");
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(getImage("icon"));
    }

    private static void initSettings(){
        Settings settings = new Settings();
        settings.setVisible(true);
        settings.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        settings.setResizable(false);
    }

    private void initPanel(int cols, int rows){
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawField(g, cols, rows);
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Coord coord = game.findPressedCoord(x, y);
                if(e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord);
                if(e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                if(e.getButton() == MouseEvent.BUTTON2){
                    dispose();
                    initSettings();
                }
                getMessage();
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(
                cols * PICTURE_WIDTH + (int) ((rows == 1) ? 0 : 0.5 * PICTURE_WIDTH),
                (int) (cols * PICTURE_HEIGHT * 0.75) + (int) (PICTURE_HEIGHT * 0.25)));
        add(panel);
    }

    private void getMessage() {
        if (game.getGameState() == GameState.BOMBED)
            JOptionPane.showMessageDialog(null, "You lose!","Ups", JOptionPane.INFORMATION_MESSAGE);
        if (game.getGameState() == GameState.WINNER)
            JOptionPane.showMessageDialog(null, "You win!","Congratulation!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void setImage(){
        for(Cell cell : Cell.values()){
            cell.image = getImage(cell.name());
        }
    }

    private Image getImage (String name){
        String fileName = "resource/pictures/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(fileName);
        return icon.getImage();
    }

    private void drawField(Graphics g, int cols, int rows) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int startX = j * PICTURE_WIDTH + ((i % 2 == 1) ? (int) (0.5 * PICTURE_WIDTH) : 0);
                int startY = i * (int) (PICTURE_HEIGHT * 0.75);
                Point start = new Point(startX, startY);
                drawCell(game.getCell(new Coord(i, j)), start, g);
            }
        }
    }

    private void drawCell(Cell cell, Point start, Graphics g) {
        switch (cell) {
            case ZERO:
                drawImg(Cell.ZERO, g, start); break;
            case NUM1:
                drawImg(Cell.NUM1, g, start); break;
            case NUM2:
                drawImg(Cell.NUM2, g, start); break;
            case NUM3:
                drawImg(Cell.NUM3, g, start); break;
            case NUM4:
                drawImg(Cell.NUM4, g, start); break;
            case NUM5:
                drawImg(Cell.NUM5, g, start); break;
            case NUM6:
                drawImg(Cell.NUM6, g, start); break;
            case BOMB:
                drawImg(Cell.BOMB, g, start); break;
            case CELL:
                drawImg(Cell.CELL, g, start); break;
            case CLOSEDCELL:
                drawImg(Cell.CLOSEDCELL, g, start); break;
            case FLAG:
                drawImg(Cell.FLAG, g, start); break;
            case EXPLODEDBOMB:
                drawImg(Cell.EXPLODEDBOMB, g, start); break;
            case NOBOMB:
                drawImg(Cell.NOBOMB, g, start); break;
        }
    }

    private void drawImg(Cell cell, Graphics g, Point start) {
            switch (cell){
                case ZERO : g.drawImage(getImage("zero"), start.x, start.y, this); break;
                case NUM1 : g.drawImage(getImage("num1"), start.x, start.y, this); break;
                case NUM2 : g.drawImage(getImage("num2"), start.x, start.y, this); break;
                case NUM3 : g.drawImage(getImage("num3"), start.x, start.y, this); break;
                case NUM4 : g.drawImage(getImage("num4"), start.x, start.y, this); break;
                case NUM5 : g.drawImage(getImage("num5"), start.x, start.y, this); break;
                case NUM6 : g.drawImage(getImage("num6"), start.x, start.y, this); break;
                case BOMB : g.drawImage(getImage("bomb"), start.x, start.y, this); break;
                case CELL : g.drawImage(getImage("cell"), start.x, start.y, this); break;
                case CLOSEDCELL : g.drawImage(getImage("closedCell"), start.x, start.y, this); break;
                case FLAG : g.drawImage(getImage("flag"), start.x, start.y, this); break;
                case EXPLODEDBOMB : g.drawImage(getImage("explodedBomb"), start.x, start.y, this); break;
                case NOBOMB : g.drawImage(getImage("noBomb"), start.x, start.y, this); break;
            }
        }
}
