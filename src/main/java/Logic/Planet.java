package Logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

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
        try {
            image = ImageIO.read(new File("files/planets/" + imageNumber + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
