package Logic;

import javax.swing.*;
import java.awt.*;

public class Zombie {

    public int currentImage = 0;

    public boolean isWalkRight;

    public int position;

    public boolean isDying = false;

    public Image[] images = {new ImageIcon("resourses/zombie/zombie walk 1.png").getImage(),
            new ImageIcon("resourses/zombie/zombie walk 2.png").getImage(),
            new ImageIcon("resourses/zombie/zombie walk 3.png").getImage(),
            new ImageIcon("resourses/zombie/zombie walk left 1.png").getImage(),
            new ImageIcon("resourses/zombie/zombie walk left 2.png").getImage(),
            new ImageIcon("resourses/zombie/zombie walk left 3.png").getImage(),
            new ImageIcon("resourses/zombie/zombie die 1.png").getImage(),
            new ImageIcon("resourses/zombie/zombie die 2.png").getImage(),
            new ImageIcon("resourses/zombie/zombie die 3.png").getImage(),
            new ImageIcon("resourses/zombie/zombie die left 1.png").getImage(),
            new ImageIcon("resourses/zombie/zombie die left 2.png").getImage(),
            new ImageIcon("resourses/zombie/zombie die left 3.png").getImage()};

    public Zombie(int position, boolean isWalkRight) {
        this.position = position;
        this.isWalkRight = isWalkRight;
    }

    public void dying() {
        isDying = true;
        if (isWalkRight) currentImage = 6;
        else currentImage = 9;
    }

    public void step() {
        if (isWalkRight) position += 10; else position -= 10;
    }
}