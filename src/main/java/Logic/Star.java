package Logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Star {

    public String name;

    public int r, x, y;

    public Image image;

    public Star(String name, int r, int numberOfImage) {
        this.name = name;
        this.r = r;
        this.x = 400 - r / 2;
        this.y = 300 - r / 2;
        try {
            image = ImageIO.read(new File("files/stars/" + numberOfImage + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}