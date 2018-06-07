package PlayIt;

import java.awt.image.BufferedImage;

public class MyPlane extends FlyingObject {
    private BufferedImage[] images = {};
    private int index = 0;

    private int doubleFire;   //双倍火力
    private int life;

    public MyPlane() {
        life = 3;
        doubleFire = 0;
        images = new BufferedImage[]{ShootGame.myPlane0, ShootGame.myPlane1};
        image = ShootGame.myPlane0;   //初始为plane0图片
        width = image.getWidth();
        height = image.getHeight();
        x = 150;
        y = 400;
    }

    // 获取双倍火力
    public int getDoubleFire() {
        return doubleFire;
    }

    public void setDoubleFire(int doubleFire) {
        this.doubleFire = doubleFire;
    }

    public void addDoubleFire() {
        doubleFire = 40;
    }

    public void addLife() {  //增命
        life++;
    }

    public void subtractLife() {   //减命
        life--;
    }

    public int getLife() { //获取命
        return life;
    }

    // 当前物体移动了一下，相对距离，x,y鼠标位置
    public void moveTo(int x, int y) {
        this.x = x - width / 2;
        this.y = y - height / 2;
    }

    @Override
    public boolean outOfBounds() {
        return false;
    }

    //发射子弹
    public Bullet[] shoot() {
        int xStep = width / 4;
        int yStep = 20;
        if (doubleFire > 0) {  //双倍火力
            Bullet[] bullets = new Bullet[2];
            bullets[0] = new Bullet(x + xStep, y - yStep);  //距离飞机上方1/4与3/4处
            bullets[1] = new Bullet(x + 3 * xStep, y - yStep);
            return bullets;
        } else {      //单倍火力
            Bullet[] bullets = new Bullet[1];
            bullets[0] = new Bullet(x + 2 * xStep, y - yStep); //飞机的中央
            return bullets;
        }
    }

    @Override
    public void step() {
        if (images.length > 0) {
            image = images[index++ / 10 % images.length];  //切换2个图片
        }
    }

    // 碰撞算法
    public boolean hit(FlyingObject other) {
        int x1 = other.x - this.width / 2;                 //x坐标最小距离
        int x2 = other.x + this.width / 2 + other.width;   //x坐标最大距离
        int y1 = other.y - this.height / 2;                //y坐标最小距离
        int y2 = other.y + this.height / 2 + other.height; //y坐标最大距离

        int planex = this.x + this.width / 2;               //我机x坐标中心点距离
        int planey = this.y + this.height / 2;              //我机y坐标中心点距离

        return planex > x1 && planex < x2 && planey > y1 && planey < y2;
    }

}
