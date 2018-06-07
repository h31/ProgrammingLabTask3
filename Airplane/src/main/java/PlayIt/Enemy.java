package PlayIt;

import java.util.Random;

public class Enemy extends FlyingObject implements GetScore {
    private int speed = 4;

    public Enemy() {
        this.image = ShootGame.enemyPlane;
        width = image.getWidth();
        height = image.getHeight();
        y = -height;
        Random rand = new Random();
        x = rand.nextInt(ShootGame.WIDTH - width);
        speed++;
    }

    // 获取分数
    public int getScore() {
        return 10;
    }

    //越界处理
    @Override
    public boolean outOfBounds() {
        return y > ShootGame.HEIGHT;
    }

    // 移动
    @Override
    public void step() {
        y += speed;
    }
}
