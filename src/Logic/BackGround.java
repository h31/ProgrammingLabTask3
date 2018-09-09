package Logic;

import javax.swing.*;
import java.awt.*;

public class BackGround {

    public Image[] images = {new ImageIcon("resourses/backgrounds/BackGround 1.png").getImage(),
            new ImageIcon("resourses/backgrounds/BackGround 2.png").getImage(),
            new ImageIcon("resourses/backgrounds/BackGround 3.png").getImage(),
            new ImageIcon("resourses/backgrounds/BackGround 4.png").getImage(),};

    public int currentImage = 0;

    public BackGround() {
    }

    public void next() {
        currentImage++;
        if (currentImage == 4) currentImage = 0;
    }
}