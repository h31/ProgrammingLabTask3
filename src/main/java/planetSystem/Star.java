package planetSystem;

public class Star {

    String name;

    int r, x, y;

    public Star(String name, int r) {
        this.name = name;
        this.r = r;
        this.x = 400 - r / 2 ;
        this.y = 300 - r / 2;
    }


}
