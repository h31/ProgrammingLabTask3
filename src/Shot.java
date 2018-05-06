public class Shot {

    private int x;
    private int y;
    private boolean shot;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isShot() {
        return shot;
    }

    Shot (int x, int y, boolean shot) {
        this.x = x;
        this.y = y;
        this.shot = shot;
    }
}
