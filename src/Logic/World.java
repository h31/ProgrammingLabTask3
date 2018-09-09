package Logic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {

    public Image foundation = new ImageIcon("resourses/Grass plate.png").getImage();

    public Player player = new Player();

    public List<Zombie> zombies = new ArrayList<>();

    public List<Bullet> bullets = new ArrayList<>();

    public List<Dog> dogs = new ArrayList<>();

    public BackGround backGround = new BackGround();

    public World() {
    }

    public void spawnEnemiesAndChangeBackground(int loopNumber) {
        if (loopNumber % 40 == 0) zombies.add(new Zombie(-50, true));
        if (loopNumber % 60 == 0) zombies.add(new Zombie(730, false));
        if (loopNumber % 100 == 0) dogs.add(new Dog(730, false));
        if (loopNumber % 200 == 0) dogs.add(new Dog(-50, true));
        backGround.next();
    }
}