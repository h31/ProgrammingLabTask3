import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class Field1 extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int SNAKE_SIZE = 16;
    private final int ALL_CELLS = 400;
    private int appleX;
    private int appleY;
    private Image snakeImage;
    private Image apple;
    private Image headSnake;
    private int[] x = new int[ALL_CELLS];
    private int[] y = new int[ALL_CELLS];
    private int cells;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;


    public Field1() {
        setBackground(Color.decode("#8B008B"));
        gameImages();
        isSnakeInGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }

    public void moveSnake() {
        for (int i = cells; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (left) {
            x[0] -= SNAKE_SIZE;
        }
        if (right) {
            x[0] += SNAKE_SIZE;
        }
        if (up) {
            y[0] -= SNAKE_SIZE;
        }
        if (down) {
            y[0] += SNAKE_SIZE;
        }
    }

    public void checkBounds() {
        for (int i = cells; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }
        }

        if (x[0] > SIZE) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if (y[0] > SIZE) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
    }

    public void isSnakeInGame() {
        cells = 3;
        for (int i = 0; i < cells; i++) {
            x[i] = 48 - i * SNAKE_SIZE;
            y[i] = 48;
        }
        timer = new Timer(150, this);
        timer.start();
        createApple();
    }

    public void createApple() {
        appleX = new Random().nextInt(18) * SNAKE_SIZE;
        appleY = new Random().nextInt(18) * SNAKE_SIZE;
    }

    public void gameImages() {
        ImageIcon iia = new ImageIcon("C:\\Users\\farru\\IdeaProjects\\ProgrammingLabTask3\\src\\src\\appleicon.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("C:\\Users\\farru\\IdeaProjects\\ProgrammingLabTask3\\src\\src\\defmix_2733.png");
        snakeImage = iid.getImage();
        ImageIcon iihs = new ImageIcon("C:\\Users\\farru\\IdeaProjects\\ProgrammingLabTask3\\src\\src\\defmix_2443.png");
        headSnake = iihs.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < cells - 1; i++) {
                g.drawImage(snakeImage, x[i + 1], y[i + 1], this);
                g.drawImage(headSnake, x[0], y[0], this);
            }
        } else {
            String str = "Game Over";
            String amountApple = "Amount apple:";
            String lengthSnake = "Length snake";
            //Font f = new Font("Arial",14,Font.BOLD);
            g.setColor(Color.white);
            // g.setFont(f);
            g.drawString(str, 125, 140);
            g.drawString(amountApple + " " + (cells - 3), 110, 180);
            g.drawString(lengthSnake + " " + cells, 110, 165);
        }
    }


    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            cells++;
            createApple();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkBounds();
            moveSnake();

        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }

            if (key == KeyEvent.VK_UP && !down) {
                right = false;
                up = true;
                left = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                right = false;
                down = true;
                left = false;

            }
        }
    }
}