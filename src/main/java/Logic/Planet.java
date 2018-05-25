package Logic;

public class Planet {

    public String name;

    public int a, b, r, x, y;

    public double period;

    public Planet(String name, int a, int b, int r) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.r = r;
        period = Math.sqrt(Math.pow(a, 3.0));
    }
}
