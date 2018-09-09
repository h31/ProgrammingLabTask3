package Logic;

import javax.swing.*;
import java.awt.*;

public class Bullet {

    public Image image = new ImageIcon("resourses/bullet.png").getImage();

    public int position;

    public boolean isFlyRight;

    public Bullet(int position, boolean isFlyRight) {
        this.position = position;
        this.isFlyRight = isFlyRight;
    }

    public void step() {
        if (isFlyRight) position += 15;
        else position -= 15;
    }
}