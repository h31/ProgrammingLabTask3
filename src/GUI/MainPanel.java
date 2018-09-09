package GUI;

import Logic.Bullet;
import Logic.Dog;
import Logic.World;
import Logic.Zombie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainPanel extends JPanel {

    private World world = new World();

    private boolean isWalk = false;

    private boolean isInJump = false;

    private boolean isInFall = false;

    private boolean isShooting = false;

    private boolean isShootingRight;

    private int loopNumber = 0;

    MainPanel() {
        setVisible(true);
        setFocusable(true);
        setSize(720, 518);
        ActionListener timerListener = e -> MainPanel.this.repaint();
        Timer timer = new Timer(100, timerListener);
        timer.start();
        MainPanel.this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && !isShooting) {
                    world.player.isGoingRight = true;
                    isWalk = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT && !isShooting) {
                    world.player.isGoingRight = false;
                    isWalk = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP && !isInJump && !isInFall && !isShooting) {
                    isInJump = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_Z && !isInJump && !isInFall && !isShooting) {
                    isShooting = true;
                    isShootingRight = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_X && !isInJump && !isInFall && !isShooting) {
                    isShooting = true;
                    isShootingRight = true;
                }
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) isWalk = false;
                if (e.getKeyCode() == KeyEvent.VK_LEFT) isWalk = false;
            }
        });
    }

    private void playerDying() {
        File f = new File("resourses/data");
        try {
            PrintWriter pw = new PrintWriter(f);
            pw.close();
            FileWriter fr = new FileWriter(f);
            fr.write(String.valueOf(world.player.points));
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DieFrame.main(new String[]{});
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    private void zombiesStepAndDrawing(Graphics g) {
        for (Zombie zombie : new ArrayList<>(world.zombies)) {
            if (Math.abs(world.player.position.x - zombie.position) < 55 && !zombie.isDying) playerDying();
            if (zombie.isWalkRight && zombie.currentImage == 3) zombie.currentImage = 0;
            if (zombie.currentImage == 6 && !zombie.isDying) zombie.currentImage = 3;
            g.drawImage(zombie.images[zombie.currentImage], zombie.position, 340, null);
            if (zombie.position < -100 || zombie.position > 750) world.zombies.remove(zombie);
            if (zombie.currentImage == 8 && zombie.isWalkRight || zombie.currentImage == 11)
                world.zombies.remove(zombie);
            zombie.currentImage++;
            zombie.step();
        }
    }

    private void dogsStepAndDrawing(Graphics g) {
        for (Dog dog : new ArrayList<>(world.dogs)) {
            g.drawImage(dog.images[dog.currentImage], dog.position, 410, null);
            if (dog.isWalkRight) {
                if (dog.currentImage == 0) dog.currentImage = 1;
                else dog.currentImage = 0;
            }
            if (!dog.isWalkRight) {
                if (dog.currentImage == 2) dog.currentImage = 3;
                else dog.currentImage = 2;
            }
            if (Math.abs(world.player.position.x - dog.position) <= 40 && world.player.position.y > 320) playerDying();
            if (dog.position < -100 || dog.position > 750) world.dogs.remove(dog);
            dog.step();
        }
    }

    private void bulletsStepAndDrawing(Graphics g) {
        for (Bullet bullet : new ArrayList<>(world.bullets)) {
            g.drawImage(bullet.image, bullet.position, 373, null);
            if (bullet.position < 0 || bullet.position > 720) world.bullets.remove(bullet);
            bullet.step();
            for (Zombie zombie : world.zombies) {
                if (Math.abs(zombie.position - bullet.position) < 11) {
                    world.bullets.remove(bullet);
                    zombie.dying();
                    world.player.points += 10;
                }
            }
        }
    }

    private void playerStep() {
        if (!isShooting) {
            if (!isWalk) world.player.currentImage = 0;
            if (isWalk) playerWalk();
            if (isInJump) playerJump();
            isInFall = !(world.player.position.y == 340) && !isInJump;
            if (isInFall) world.player.fallStep();
        } else playerShoot();
    }

    private void playerJump() {
        if (world.player.jumpLimit != 0) {
            world.player.jumpStep();
        } else {
            world.player.jumpLimit = 70;
            isInJump = false;
        }
    }

    private void playerWalk() {
        if (world.player.isGoingRight) {
            if (world.player.currentImage > 2) world.player.currentImage = 1;
            world.player.stepRight();
        }
        if (!world.player.isGoingRight) {
            if (world.player.currentImage < 4 || world.player.currentImage == 6) world.player.currentImage = 4;
            world.player.stepLeft();
        }
        world.player.currentImage++;
    }

    private void playerShoot() {
        world.player.currentImage++;
        if (world.player.currentImage < 8) world.player.currentImage = isShootingRight ? 7 : 10;
        if ((world.player.currentImage == 10 && isShootingRight) || world.player.currentImage == 13) {
            if (world.player.currentImage == 10) {
                world.bullets.add(new Bullet(world.player.position.x + 75, true));
            } else world.bullets.add(new Bullet(world.player.position.x - 5, false));
            world.player.currentImage = 0;
            isShooting = false;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        loopNumber++;
        world.spawnEnemiesAndChangeBackground(loopNumber);
        g.drawImage(world.backGround.images[world.backGround.currentImage], 0, 0, null);
        g.drawImage(world.foundation, 0, 440, null);
        g.setColor(new Color(0xFFFFFF));
        g.setFont(new Font("Serif", Font.ITALIC, 30));
        g.drawString(String.valueOf(world.player.points), 600, 45);
        g.drawImage(world.player.images[world.player.currentImage],
                world.player.position.x, world.player.position.y, null);
        bulletsStepAndDrawing(g);
        dogsStepAndDrawing(g);
        zombiesStepAndDrawing(g);
        playerStep();
    }
}