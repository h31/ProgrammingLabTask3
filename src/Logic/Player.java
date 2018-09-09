package Logic;

import javax.swing.*;
import java.awt.*;

public class Player {

    public Image[] images = {new ImageIcon("resourses/player images/stand.png").getImage(),
            new ImageIcon("resourses/player images/walk right 1.png").getImage(),
            new ImageIcon("resourses/player images/walk right 2.png").getImage(),
            new ImageIcon("resourses/player images/walk right 3.png").getImage(),
            new ImageIcon("resourses/player images/walk left 1.png").getImage(),
            new ImageIcon("resourses/player images/walk left 2.png").getImage(),
            new ImageIcon("resourses/player images/walk left 3.png").getImage(),
            new ImageIcon("resourses/player images/attack 1.png").getImage(),
            new ImageIcon("resourses/player images/attack 2.png").getImage(),
            new ImageIcon("resourses/player images/attack 3.png").getImage(),
            new ImageIcon("resourses/player images/attack left 1.png").getImage(),
            new ImageIcon("resourses/player images/attack left 2.png").getImage(),
            new ImageIcon("resourses/player images/attack left 3.png").getImage()};

    public Point position = new Point(0, 340);

    public int currentImage = 0;

    public int jumpLimit = 70;

    public int points = 0;

    public boolean isGoingRight = true;

    public Player() {
    }

    public void stepRight() {
        position.x += 10;
    }

    public void stepLeft() {
        position.x -= 10;
    }

    public void jumpStep() {
        position.y -= 10;
        jumpLimit -= 10;
    }

    public void fallStep() {
        position.y += 10;
    }
}