package PlayIt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ShootGame extends JPanel{
    public static void main(String[] args) {
        JFrame frame = new JFrame("飞机大战");
        ShootGame game = new ShootGame(); // 面板对象
        frame.add(game); // 将面板添加到JFrame中
        frame.setSize(WIDTH, HEIGHT); // 设置屏幕大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 默认关闭操作
        frame.setLocationRelativeTo(null); //  将窗口置于屏幕中间
        frame.setVisible(true);

        game.action();
    }

    public static final int WIDTH = 400;
    public static final int HEIGHT = 654;

    //游戏的状态
    private int state;
    private static final int START = 0;
    private static final int RUNNING = 1;
    private static final int PAUSE = 2;
    private static final int GAME_OVER = 3;

    private int score = 0;
    private java.util.Timer timer; // 定时器
    private int intervel = 1000 / 100; // 时间间隔(毫秒)

    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage enemyPlane;
    public static BufferedImage bee;
    public static BufferedImage bullet;
    public static BufferedImage myPlane0;
    public static BufferedImage myPlane1;
    public static BufferedImage pause;
    public static BufferedImage gameover;

    private FlyingObject[] flyings = {};
    private Bullet[] bullets = {};
    private MyPlane myPlane = new MyPlane();

    static {
        try {
            background = ImageIO.read(new FileInputStream(Pictures.background));
            start = ImageIO.read(new FileInputStream(Pictures.start));
            enemyPlane = ImageIO.read(new FileInputStream(Pictures.enemyPlane));
            bee = ImageIO.read(new FileInputStream(Pictures.bee));
            bullet = ImageIO.read(new FileInputStream(Pictures.bullet));
            myPlane0 = ImageIO.read(new FileInputStream(Pictures.myPlane0));
            myPlane1 = ImageIO.read(new FileInputStream(Pictures.myPlane1));
            pause = ImageIO.read(new FileInputStream(Pictures.pause));
            gameover = ImageIO.read(new FileInputStream(Pictures.gameover));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);
        paintHero(g);
        paintBullets(g);
        paintFlyingObjects(g);
        paintScore(g);
        paintState(g);
    }

    //画我的飞机
    public void paintHero(Graphics g) {
        g.drawImage(myPlane.getImage(), myPlane.getX(), myPlane.getY(), null);
    }

    //画子弹
    public void paintBullets(Graphics g) {
        for (int i = 0; i < bullets.length; i++) {
            Bullet b = bullets[i];
            g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2, b.getY(),
                    null);
        }
    }

    //画飞行物
    public void paintFlyingObjects(Graphics g) {
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject f = flyings[i];
            g.drawImage(f.getImage(), f.getX(), f.getY(), null);
        }
    }

    //画分数
    public void paintScore(Graphics g) {
        int x = 275;
        int y = 25;
        Font font = new Font("宋体", Font.BOLD, 20);
        g.setFont(font);
        g.drawString("Score:" + score, x, y);
        y += 20;
        g.drawString("Life:" + myPlane.getLife(), x, y);
    }

    //画游戏状态
    public void paintState(Graphics g) {
        switch (state) {
            case START:
                g.drawImage(start, 0, 0, null);
                break;
            case PAUSE:
                g.drawImage(pause, 0, 0, null);
                break;
            case GAME_OVER:
                g.drawImage(gameover, 0, 0, null);
                break;
        }
    }

    //启动执行代码
    public void action() {
        // 鼠标监听事件
        MouseAdapter m = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) { // 鼠标移动
                if (state == RUNNING) {
                    int x = e.getX();
                    int y = e.getY();
                    myPlane.moveTo(x, y);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) { // 鼠标进入
                if (state == PAUSE) { // 暂停状态下运行
                    state = RUNNING;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) { // 鼠标退出
                if (state != GAME_OVER && state != START) {
                    state = PAUSE;
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) { // 鼠标点击
                switch (state) {
                    case START:
                        state = RUNNING; // 开始游戏
                        break;
                    case GAME_OVER: // 游戏结束，清理现场
                        flyings = new FlyingObject[0];
                        bullets = new Bullet[0];
                        myPlane = new MyPlane();
                        score = 0;
                        state = START;
                        break;
                }
            }
        };

        this.addMouseListener(m); // 处理鼠标点击操作
        this.addMouseMotionListener(m); // 处理鼠标滑动操作

        timer = new Timer(); // 主流程控制
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (state == RUNNING) {
                    enterAction(); // 飞行物入场
                    stepAction(); // 走一步
                    shootAction(); // 射击
                    bulletAction(); // 子弹打飞行物
                    outOfBoundsAction(); // 删除越界飞行物及子弹
                    checkGameOverAction(); // 检查游戏结束
                }
                repaint(); // 重绘，调用paint()方法
            }

        }, intervel, intervel);
    }

    int flyEnteredIndex = 0; // 飞行物入场计数

    //飞行物入场
    public void enterAction() {
        flyEnteredIndex++;
        if (flyEnteredIndex % 30 == 0) { // 300毫秒生成一个飞行物
            FlyingObject obj = nextObj(); // 随机生成一个飞行物
            flyings = Arrays.copyOf(flyings, flyings.length + 1);
            flyings[flyings.length - 1] = obj;
        }
    }

    //走一步
    public void stepAction() {
        for (int i = 0; i < flyings.length; i++) { // 飞行物走一步
            FlyingObject f = flyings[i];
            f.step();
        }

        for (int i = 0; i < bullets.length; i++) { // 子弹走一步
            Bullet b = bullets[i];
            b.step();
        }
        myPlane.step(); // 英雄机走一步
    }

    int shootIndex = 0; // 射击计数

    //射击
    public void shootAction() {
        shootIndex++;
        if (shootIndex % 30 == 0) { // 300毫秒发一颗
            Bullet[] b = myPlane.shoot(); // 我机打出子弹
            bullets = Arrays.copyOf(bullets, bullets.length + b.length);
            System.arraycopy(b, 0, bullets, bullets.length - b.length,
                    b.length); // 追加数组
        }
    }

    //子弹与飞行物碰撞检测
    public void bulletAction() {
        for (int i = 0; i < bullets.length; i++) {
            Bullet b = bullets[i];
            collision(b);
        }
    }

    //删除越界飞行物及子弹
    public void outOfBoundsAction() {
        int index = 0;
        FlyingObject[] flyingLives = new FlyingObject[flyings.length]; // 活着的飞行物
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject f = flyings[i];
            if (!f.outOfBounds()) {
                flyingLives[index++] = f; // 不越界的留着
            }
        }
        flyings = Arrays.copyOf(flyingLives, index); // 将不越界的飞行物都留着

        index = 0; // 索引重置为0
        Bullet[] bulletLives = new Bullet[bullets.length];
        for (int i = 0; i < bullets.length; i++) {
            Bullet b = bullets[i];
            if (!b.outOfBounds()) {
                bulletLives[index++] = b;
            }
        }
        bullets = Arrays.copyOf(bulletLives, index); // 将不越界的子弹留着
    }

    //检查游戏结束
    public void checkGameOverAction() {
        if (isGameOver()) {
            state = GAME_OVER; // 改变状态
        }
    }

    public boolean isGameOver() {
        for (int i = 0; i < flyings.length; i++) {
            int index = -1;
            FlyingObject obj = flyings[i];
            if (myPlane.hit(obj)) { // 检查我机与飞行物是否碰撞
                myPlane.subtractLife(); // 减命
                myPlane.setDoubleFire(0); // 双倍火力解除
                index = i; // 记录碰上的飞行物索引
            }
            if (index != -1) {
                FlyingObject f = flyings[index];
                flyings[index] = flyings[flyings.length - 1];
                flyings[flyings.length - 1] = f; // 碰上的与最后一个飞行物交换
                flyings = Arrays.copyOf(flyings, flyings.length - 1); // 删除碰上的飞行物
            }
        }
        return myPlane.getLife() <= 0;
    }

    public void collision(Bullet bullet) {
        int index = -1; // 击中的飞行物索引
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject obj = flyings[i];
            if (obj.shootBy(bullet)) { // 判断是否击中
                index = i; // 记录被击中的飞行物的索引
                break;
            }
        }
        if (index != -1) { // 有击中的飞行物
            FlyingObject one = flyings[index]; // 记录被击中的飞行物

            FlyingObject temp = flyings[index]; // 被击中的飞行物与最后一个飞行物交换
            flyings[index] = flyings[flyings.length - 1];
            flyings[flyings.length - 1] = temp;

            flyings = Arrays.copyOf(flyings, flyings.length - 1); // 删除最后一个飞行物(即被击中的)

            // 检查one的类型(敌人加分，奖励获取)
            if (one instanceof GetScore) { // 检查类型，是敌人，则加分
                GetScore e = (GetScore) one;
                score += e.getScore();
            } else if (one instanceof Award) { // 若为奖励，设置奖励
                Award a = (Award) one;
                int type = a.getType();
                switch (type) {
                    case Award.DOUBLE_FIRE:
                        myPlane.addDoubleFire(); // 设置双倍火力
                        break;
                    case Award.LIFE:
                        myPlane.addLife(); // 设置加命
                        break;
                }
            }
        }
    }

    // 随机生成飞行物
    public static FlyingObject nextObj() {
        Random random = new Random();
        int type = random.nextInt(20); // 产生0-19的随机数
        if (type == 0) {
            return new Bee();
        } else {
            return new Enemy();
        }
    }

}
