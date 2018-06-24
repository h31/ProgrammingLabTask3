import units.teamOne.Knight;
import units.teamTwo.Sectoid;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class Game extends Canvas implements Runnable {
    private boolean running = false;

    public int xOffset, yOffset;

    private Display display;
    private BufferStrategy bs;
    private Graphics g;
    public JFrame frame;
    private Thread thread;

    private int width, height;
    public static int TILE_WIDTH = 64;
    public static int TILE_HEIGHT = 64;
    public static String title = "COFIM Alpha v.0.0.1";

    public int[][] world;
    public int x, y;

    private MouseManager mouseManager;

    public int sectoidX = 0;
    public int sectoidY = 0;
    public int knightX;
    public int knightY;

    public Sectoid sectoid = new Sectoid();
    public Knight knight = new Knight();

    public int[][] getWorld() {
        return world;
    }

    public void init() {
        display = new Display(title,width, height);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        this.frame = display.getFrame();
        Assets.init();
        xOffset = frame.getWidth()/2;
        for(int i = 1; i < world.length;i++){
            for(int j = 1;j < world[1].length;j++){
                int r = ThreadLocalRandom.current().nextInt(0,2 + 1);
                if(r == 0){
                    world[i][j] = 0;
                }
                else if (r == 1){
                    world[i][j] = 1;
                } else {
                    world[i][j] = 2;
                }
            }
        }
        for(int i = 0; i < world.length; i++){
            for(int j = 0; j < world[0].length; j++){
                if (j == 0 || j == world[0].length - 1 || i == world.length - 1){
                    world[i][j] = 0;
                }
            }
        }
    }

    public void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0,0, frame.getWidth(),frame.getHeight());
        for (int x = 0; x < world.length; x++){
            for (int y = 0; y < world[0].length; y++){
                int[] isoCoords = toIso(x,y);
                int fx = isoCoords[0];
                int fy = isoCoords[1];
                if (world[x][y] == 0){
                    g.drawImage(Assets.grass,fx,fy,null);
                } if (world[x][y] == 1) {
                    g.drawImage(Assets.pit,fx,fy,null);
                } else if (world[x][y] == 2) {
                    g.drawImage(Assets.dangers,fx,fy,null);
                }
            }
        }
        int[] coordsKnight = toIso(knightX, knightY);
        int knightX = coordsKnight[0];
        int knightY = coordsKnight[1];
        g.drawImage(Assets.knight, knightX, knightY, null);

        int[] coordsSectoid = toIso(sectoidX, sectoidY);
        int sectoidX = coordsSectoid[0];
        int sectoidY = coordsSectoid[1];
        g.drawImage(Assets.sectoid, sectoidX, sectoidY, null);

        int[] coordsIsoSelected = toIso(x, y);

        g.drawString("Здоровье Knight: " + knight.getHp(),30,40);
        g.drawString("Кол-во аптечек у Knight: " + knight.getHeal(),30,55);
        g.drawString("Здоровье Sectoid: " + sectoid.getHp(),30,70);
        g.drawString("Кол-во аптечек у Sectoid: " + sectoid.getHeal(),30,85);
        if (mouseManager.moveCounter % 2 == 0) {
            g.drawString("Сейчас ход: Knight",30,25);
        } else {
            g.drawString("Сейчас ход: Sectoid",30,25);
        }
        g.drawString("Текущий ход: " + mouseManager.moveCounter, 30, 100);
        g.drawImage(Assets.selected, coordsIsoSelected[0], coordsIsoSelected[1], TILE_WIDTH, TILE_HEIGHT, null);
        bs.show();
        g.dispose();
    }

    private void tick() {
        mouseManager.tick();
        xOffset = frame.getWidth()/2;
    }

    public void run() {
        init();
        int fps = 120;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while (running && (knight.isAlive() && sectoid.isAlive())) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if(delta >= 1){
                tick();
                render();
                delta--;
            }
        }
        if (!sectoid.isAlive()) {
            JOptionPane.showMessageDialog(null, "Победил knight! Закройте игру.");
        } else {
            JOptionPane.showMessageDialog(null, "Победил sectoid! Закройте игру.");
        }
        stop();
    }

    public synchronized void start() {
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if(!running){
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int[] toIso(int x, int y){
        int i = (x - y) * 32;
        int j = (x + y) * 16;
        i += xOffset - 32;
        j += yOffset;
        return new int[]{i,j};
    }

    public int[] toGrid(double i, double j) {
        i-=xOffset;
        j-=yOffset;
        double tx = Math.ceil(((i / 32) + (j / 16))/2);
        double ty = Math.ceil(((j / 16) - (i / 32))/2);
        int x = (int) Math.ceil(tx)-1;
        int y = (int) Math.ceil(ty)-1;
        return new int[]{x,y};
    }

    public Game(int width, int height, int x, int y){
        this.height = height;
        this.width = width;
        this.mouseManager = new MouseManager(this);
        this.world = new int[x][y];
        this.knightX = x - 1;
        this.knightY = y - 1;
    }
}