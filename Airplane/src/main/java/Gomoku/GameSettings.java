package Gomoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameSettings extends JPanel implements MouseListener, Runnable {
    static int[][] chessArray = new int[15][15];
    int x, y;
    Boolean checkPlay = false;
    Boolean blackTurns = true;
    Thread t = new Thread(this);
    int blackTime = 120;
    int whiteTime = 120;
    String message = "";

    public boolean getBlackTurns() {
        return this.blackTurns;
    }

    public void setBlackTurns(boolean blackTurns) {
        this.blackTurns = blackTurns;
    }

    public GameSettings() {
        this.repaint();
        addMouseListener((MouseListener) this);
        t.start();
        t.suspend();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x, y;
        g.setFont(new Font("宋体", Font.BOLD, 18));
        g.drawString("Black time：" + blackTime, 30, 470);
        g.drawString("White time：" + whiteTime, 305, 470);
        for (int i = 0; i < 15; i++) {
            g.drawLine(30, 30 + 30 * i, 450, 30 + 30 * i);
            g.drawLine(30 + 30 * i, 30, 30 + 30 * i, 450);
        }

        g.fillRect(240 - 5, 240 - 5, 10, 10);
        g.fillRect(360 - 5, 360 - 5, 10, 10);
        g.fillRect(360 - 5, 120 - 5, 10, 10);
        g.fillRect(120 - 5, 360 - 5, 10, 10);
        g.fillRect(120 - 5, 120 - 5, 10, 10);

        //draw chess pieces
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                drawChessPieces(g, i, j);
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        if (checkPlay) {
            x = e.getX();
            y = e.getY();
            playTheChess();
        }
    }

    // condition of win
    private boolean winCondition(int x, int y) {
        boolean flag = false;
        int color = chessArray[x][y];
        int count1 = this.countPieces(x, y, 1, 0, color);
        int count2 = this.countPieces(x, y, 0, 1, color);
        int count3 = this.countPieces(x, y, 1, -1, color);
        int count4 = this.countPieces(x, y, 1, 1, color);

        if (count1 >= 5 || count2 >= 5 || count3 >= 5 || count4 >= 5) flag = true;
        return flag;
    }

    //count the number of chess pieces
    private int countPieces(int x, int y, int changeX, int changeY, int color) {
        int count = 1;
        int tempX = changeX;
        int tempY = changeY;
        int digit1 = x + changeX;
        int digit2 = x - changeX;
        int digit3 = y + changeY;
        int digit4 = y - changeY;
        boolean flag1 = digit1 >= 0 && digit1 <= 14 && digit3 >= 0 && digit3 <= 14;
        boolean flag2 = digit2 >= 0 && digit2 <= 14 && digit4 >= 0 && digit4 <= 14;

        while (flag1 && color == chessArray[digit1][digit3]) {
            count++;
            if (changeX != 0) {
                changeX++;
            }
            if (changeY != 0) {
                if (changeY > 0) {
                    changeY++;
                } else {
                    changeY--;
                }
            }
        }
        changeX = tempX;
        changeY = tempY;
        while (flag2 && color == chessArray[digit2][digit4]) {
            count++;
            if (changeX != 0) {
                changeX++;
            }
            if (changeY != 0) {
                if (changeY > 0) {
                    changeY++;
                } else {
                    changeY--;
                }
            }
        }
        return count;
    }

    public void drawChessPieces(Graphics g, int i, int j) {
        if (chessArray[i][j] == 1) {
            g.setColor(Color.black);
            g.fillOval(30 * i + 30 - 7, 30 * j + 30 - 7, 14, 14);
            g.drawString(message, 200, 20);

        }
        if (chessArray[i][j] == 2) {
            g.setColor(Color.white);
            g.fillOval(30 * i + 30 - 7, 30 * j + 30 - 7, 14, 14);
            g.setColor(Color.black);
            g.drawString(message, 200, 20);
        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void run() {
            while (true) {
                if (blackTurns) {
                    blackTime--;
                    if (blackTime == 0) {
                        JOptionPane.showMessageDialog(this,
                                "Black overtime，White win，game over!");
                    }
                } else {
                    whiteTime--;
                    if (whiteTime == 0) {
                        JOptionPane.showMessageDialog(this,
                                "White overtime，Black win，game over!");
                    }
                }

                this.repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    public void startGame() {
        this.checkPlay = true;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                chessArray[i][j] = 0;
            }
        }
        if (checkPlay) {
            t.resume();
        }
        this.repaint();
        JOptionPane.showMessageDialog(this, "The game is starting，get ready to play");
    }

    public void playTheChess() {
        if (x >= 29 && x <= 451 && y >= 29 && y <= 451) {
            x = (x + 15) / 30 - 1;
            y = (y + 15) / 30 - 1;
            if (chessArray[x][y] == 0) {
                if (blackTurns) {
                    chessArray[x][y] = 1;
                    blackTurns = false;
                    blackTime = 120;
                    message = "White, your turn";
                } else {
                    chessArray[x][y] = 2;
                    blackTurns = true;
                    whiteTime = 120;
                    message = "Black, your turn";
                }
            }

            boolean win = this.winCondition(x, y);
            this.repaint();
            if (win) {
                JOptionPane.showMessageDialog(this, "Game over,"
                        + (chessArray[x][y] == 1 ? "black" : "white") + "win!");
                checkPlay = false;
            }
        }
    }
}
