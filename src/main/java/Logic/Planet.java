package Logic;

import javax.swing.*;
import java.awt.*;

public class Planet {

    public String name;

    public int a, b, r, x, y;

    public double period;

    public Image image;

    public Planet(String name, int r, int a, int b, int imageNumber) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.r = r;
        period = Math.sqrt(Math.pow(a, 3.0));
        image = new ImageIcon("files/planets/" + imageNumber + ".png").getImage();
    }
}
