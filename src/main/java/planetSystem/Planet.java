package planetSystem;

public class Planet {

    public String name;

    public int a, b, r, x, y;

    public int t = 0;

    private int dt = 1;

    public double period;

    public Planet(String name, int a, int b, int r) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.r = r;
        period = Math.sqrt(Math.pow(a, 3.0));
    }

    public void step() {
        x = (int) (400 + a / 2 * Math.cos(2 * Math.PI / period * t));
        y = (int) (300 + b / 2* Math.sin(2 * Math.PI / period * t));
        t = t + dt;
    }

    public void step(int dt) {
        t = t + dt;
    }

    public void pause() {
        dt = 0;
    }

    public void play() {
        dt = 1;
    }

}
