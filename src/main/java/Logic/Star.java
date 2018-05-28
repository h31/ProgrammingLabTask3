package Logic;

import javax.swing.*;
import java.awt.*;

public class Star {

    public String name;

    public int r, x, y;

    public Image image;

    public Star(String name, int r, int imageNumber) {
        this.name = name;
        this.r = r;
        this.x = 400 - r / 2;
        this.y = 300 - r / 2;
        image = new ImageIcon("files/stars/" + imageNumber + ".png").getImage();
    }
}