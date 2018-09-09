package Logic;

import javax.swing.*;
import java.awt.*;

public class Dog {

    public boolean isWalkRight;

    public int position;

    public int currentImage = 0;

    public Image[] images = {new ImageIcon("resourses/dog/dog run right 1.png").getImage(),
            new ImageIcon("resourses/dog/dog run right 2.png").getImage(),
            new ImageIcon("resourses/dog/dog run 1.png").getImage(),
            new ImageIcon("resourses/dog/dog run 2.png").getImage()};

    public Dog(int position, boolean isRunRight) {
        this.position = position;
        this.isWalkRight = isRunRight;
    }

    public void step() {
        if (isWalkRight) position += 20;
        else position -= 20;
    }
}