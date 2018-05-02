package planetSystem;

public class Planet {
    public int a;

    public int b;

    public int r;

    public int x;

    public int y;

    public double t = 0.0;

    public double period;

    public Planet(int a, int b, int r) {
        this.a = a;
        this.b = b;
        this.r = r;
        period = Math.sqrt(Math.pow(a, 3.0));
    }

    public void step() {
        x = (int) (400 + a * Math.cos(2 * Math.PI / period * t));
        y = (int) (300 + a * Math.sin(2 * Math.PI / period * t));
        t = t + 1;
    }

}
