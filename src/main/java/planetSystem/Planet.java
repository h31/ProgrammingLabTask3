package planetSystem;

class Planet {

    String name;

    int a, b, r, x, y;

    double period;

    Planet(String name, int a, int b, int r) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.r = r;
        period = Math.sqrt(Math.pow(a, 3.0));
    }


}
