import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class Listeners extends KeyAdapter implements ActionListener {
    public Listeners(Game game, int Numbers[][], JLabel jLabel, JButton start, JButton help) {
        this.game = game;
        this.Numbers2D = Numbers;
        this.jLabel = jLabel;
        this.start = start;
        this.help = help;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == start) {
            isWin = false;
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    Numbers2D[i][j] = 0;

            score = 0;

            jLabel.setText("SCORE：" + score);
            row1 = rand.nextInt(4);
            row2 = rand.nextInt(4);
            column1 = rand.nextInt(4);
            column2 = rand.nextInt(4);

            while (row1 == row2 && column1 == column2) {
                row2 = rand.nextInt(4);
                column2 = rand.nextInt(4);
            }

            int value1 = rand.nextInt(2) * 2 + 2;
            int value2 = rand.nextInt(2) * 2 + 2;

            Numbers2D[row1][column1] = value1;
            Numbers2D[row2][column2] = value2;
            game.paint(game.getGraphics());
        } else if (event.getSource() == help) {
            JOptionPane.showMessageDialog(game, "控制所有方块向同一个方向运动，\n" +
                    "两个相同数字方块撞在一起之后合并成为他们的和，\n" +
                    "每次操作之后会随机生成一个2或者4，\n" +
                    "最终得到一个“2048”的方块就算胜利了");
        }
    }

    int temporary, previous;
    int Counter = 0, NumberCounter = 0, NumberNearCounter = 0;

    void moveToLeft() {
        for (int h = 0; h < 4; h++) {
            for (int l = 0; l < 4; l++) {
                if (Numbers2D[h][l] != 0) {
                    temporary = Numbers2D[h][l];
                    previous = l - 1;
                    while (previous >= 0 && Numbers2D[h][previous] == 0) {
                        Numbers2D[h][previous] = temporary;
                        Numbers2D[h][previous + 1] = 0;
                        previous--;
                        Counter++;
                    }
                }
            }
        }
        for (int h = 0; h < 4; h++) {
            for (int l = 0; l < 4; l++) {
                if (l + 1 < 4
                        && (Numbers2D[h][l] == Numbers2D[h][l + 1])
                        && (Numbers2D[h][l] != 0 || Numbers2D[h][l + 1] != 0)) {
                    Numbers2D[h][l] = Numbers2D[h][l] + Numbers2D[h][l + 1];
                    Numbers2D[h][l + 1] = 0;
                    Counter++;
                    score += Numbers2D[h][l];
                    if (Numbers2D[h][l] == 2048) {
                        isWin = true;
                    }
                }
            }
        }

        for (int h = 0; h < 4; h++) {
            for (int l = 0; l < 4; l++) {
                if (Numbers2D[h][l] != 0) {
                    temporary = Numbers2D[h][l];
                    previous = l - 1;
                    while (previous >= 0 && Numbers2D[h][previous] == 0) {
                        Numbers2D[h][previous] = temporary;
                        Numbers2D[h][previous + 1] = 0;
                        previous--;
                        Counter++;
                    }
                }
            }
        }
    }

    public void keyPressed(KeyEvent event) {
        Move move = new Move(this);
        if (isWin == false) {
            switch (event.getKeyCode()) {
                case (KeyEvent.VK_LEFT):
                    moveToLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    move.moveToRight();
                    break;
                case KeyEvent.VK_UP:
                    move.moveUp();
                    break;
                case KeyEvent.VK_DOWN:
                    move.moveDown();
                    break;
            }
            this.ifHasNearNumber();
            this.ifFull();
            this.setScore();
            this.setEnding();
        }
    }

    private void setEnding() {
        if (isWin == true) {
            game.paint(game.getGraphics());
            JOptionPane.showMessageDialog(game, "YOU WIN!\nYOUR RESULT：" + score);
        }

        if (NumberCounter == 16 && NumberNearCounter == 0) {
            JOptionPane.showMessageDialog(game, "GAME OVER!!");
        }
        game.paint(game.getGraphics());
    }

    private void ifFull() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Numbers2D[i][j] != 0) {
                    NumberCounter++;
                }
            }
        }
    }

    private void setScore() {
        if (Counter > 0) {
            jLabel.setText("Score：" + score);
            int r1 = rand.nextInt(4);
            int c1 = rand.nextInt(4);
            while (Numbers2D[r1][c1] != 0) {
                r1 = rand.nextInt(4);
                c1 = rand.nextInt(4);
            }
            int value1 = rand.nextInt(2) * 2 + 2;
            Numbers2D[r1][c1] = value1;
        }
    }

    private void ifHasNearNumber() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Numbers2D[i][j] == Numbers2D[i][j + 1]
                        && Numbers2D[i][j] != 0) {
                    NumberNearCounter++;
                }
                if (Numbers2D[i][j] == Numbers2D[i + 1][j]
                        && Numbers2D[i][j] != 0) {
                    NumberNearCounter++;
                }
                if (Numbers2D[3][j] == Numbers2D[3][j + 1]
                        && Numbers2D[3][j] != 0) {
                    NumberNearCounter++;
                }
                if (Numbers2D[i][3] == Numbers2D[i + 1][3]
                        && Numbers2D[i][3] != 0) {
                    NumberNearCounter++;
                }
            }
        }
    }

    private Game game;
    int Numbers2D[][];
    boolean isWin = false;
    private Random rand = new Random();
    private JLabel jLabel;
    int score;
    private int row1, row2, column1, column2;
    private JButton start, help;
}
