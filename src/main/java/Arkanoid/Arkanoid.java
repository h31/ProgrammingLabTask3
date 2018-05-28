package Arkanoid;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import logic.*;

public class Arkanoid extends JPanel {
    private Color[] rowColors = new Color[]{Color.pink.darker(), Color.blue.darker(), Color.cyan.darker(),
            Color.green.darker(), Color.yellow.darker(), Color.red.darker()};
    public ArrayList<Block> blocks;
    private GameThread gameThread;
    boolean isRunning;
    boolean isPaused;
    private boolean result;
    public Paddle paddle;
    private Ball ball;
    private int balls = 3;
    private int level = 1;
    private static int scoreInt = 0;
    public static int scoreCoefficient = 1;
    public static int coefficientUpper;
    public int widthOfWindow, heightOfWindow;
    private double updateTime = 6;

    private void run() {
        if ((gameThread != null) && (gameThread.isAlive())) gameThread.interrupt();
        restart();
        gameThread = new GameThread(this);
        gameThread.start();
    }

    public Arkanoid(int widthOfWindow, int heightOfWindow) {
        this.widthOfWindow = widthOfWindow;
        this.heightOfWindow = heightOfWindow;
        restart();
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && !isRunning) run();
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) pauseGame();
                if (e.getKeyCode() == KeyEvent.VK_Q) exitToRestart();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                paddle.currentPosition.x = e.getX() - getWidth() / 2;
                repaint();
            }
        });
    }

    public static void counter() {
        scoreInt = scoreCoefficient * 10 + scoreInt;
    }

    private void restart() {
        createBlocks();
        paddle = new Paddle(this);
        ball = new Ball(this);
        if (result) {
            level++;
            updateTime++;
        } else {
            balls = 3;
            scoreInt = 0;
            scoreCoefficient = 1;
            coefficientUpper = 0;
            updateTime = 6;
            level = 1;
        }
    }

    private void pauseGame() {
        isPaused = !isPaused;
    }

    private void exitToRestart() {
        ball.currentPosition = new Point(0, 0);
        createBlocks();
        isRunning = false;
    }

    private void gameWin(boolean win) {
        result = win;
        exitToRestart();
    }

    public void lossOfBall() {
        balls--;
        if (balls <= 0) gameWin(false);
        else {
            ball.currentPosition = new Point(0, 0);
            pauseGame();
        }
    }

    private void createBlocks() {
        blocks = new ArrayList<Block>();
        float widthOfBlock = (((float) widthOfWindow - 10) / 10) - 10;
        float heightOfBlock = 30;
        int distanceBtwBlocks = 10;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 6; y++) {
                Block block = new Block();
                block.mainColor = rowColors[y % rowColors.length];
                block.currentPosition.x = (int) (x * (widthOfBlock + distanceBtwBlocks) + distanceBtwBlocks)
                        - widthOfWindow / 2;
                block.currentPosition.y = (int) (y * (heightOfBlock + distanceBtwBlocks) + distanceBtwBlocks)
                        - heightOfWindow / 2;
                block.heightOfBlock = (int) heightOfBlock;
                block.widthOfBlock = (int) widthOfBlock;
                blocks.add(block);
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.translate((getWidth() - widthOfWindow) / 2, (getHeight() - heightOfWindow) / 2);
        g.translate(widthOfWindow / 2, heightOfWindow / 2);
        setBackground(new Color(5, 4, 80));
        FontMetrics fm = g.getFontMetrics();
        paddle.graphicOfPaddle(g);
        ball.graphicOfBall(g);
        if (blocks != null) for (int i = 0; i < blocks.size(); i++) blocks.get(i).graphicOfBlock(g);
        g.setFont(new Font("Courier", Font.BOLD, 30));
        String windowMessage = "";
        if (!isRunning) windowMessage = "Press Space To Start";
        else if (isPaused) windowMessage = "Game Paused (Press Esc To Continue)";
        g.setColor(Color.black);
        g.drawString(windowMessage, -fm.stringWidth(windowMessage) / 2 - 100, fm.getHeight() / 2 - 50);
        String scoreStr = "Score:     " + scoreInt;
        g.setColor(Color.cyan);
        g.drawString(scoreStr, -fm.stringWidth(scoreStr) / 2 - 550, fm.getHeight() + 100);
        String liveStr = "Lives:      " + balls;
        g.setColor(Color.red);
        g.drawString(liveStr, -fm.stringWidth(liveStr) / 2 - 550, fm.getHeight() + 150);
        String levelStr = "Level:      " + level;
        g.setColor(Color.green);
        g.drawString(levelStr, -fm.stringWidth(levelStr) / 2 - 550, fm.getHeight() + 200);
        String restartStr = "Restart:  Q";
        g.setColor(Color.black);
        g.drawString(restartStr, -fm.stringWidth(restartStr) / 2 + 450, fm.getHeight() + 100);
        String pauseStr = "Pause: Esc";
        g.setColor(Color.black);
        g.drawString(pauseStr, -fm.stringWidth(pauseStr) / 2 + 450, fm.getHeight() + 150);
    }

    void screenUpdate() {
        ball.movementOfBall(updateTime);
        if (blocks.isEmpty()) gameWin(true);
        repaint();
    }
}
