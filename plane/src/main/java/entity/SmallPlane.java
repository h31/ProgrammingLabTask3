package entity;

import UI.GamePlayingPanel;
import config.Config;
import config.EnemyPlaneType;
import config.ImageConstants;
import until.Images;

import java.awt.*;

public class SmallPlane extends EnemyPlane {

    public SmallPlane(GamePlayingPanel getPlayingPanel, EnemyPlaneType enemyType) {
        super(getPlayingPanel, enemyType);
    }

    @Override
    public void drawFighting(Graphics g) {
        new Thread(new DrawFighting(g)).start();
    }

    private void drawFightingRun(Graphics g) {
        this.setPlaneImage(Images.SMALL_PLANE_FIGHTING_IMG);
        this.setWidth(ImageConstants.SMALL_PLANE_FIGHTING_WIDTH);
        this.setHeight(ImageConstants.SMALL_PLANE_FIGHTING_HEIGHT);
        super.draw(g);
        try {
            Thread.sleep(Config.SMALL_PLANE_STATUS_CHANGE_INTERVAL);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void drawKilled(Graphics g) {
        new Thread(new DrawKilled(g)).start();
    }

    private void drawKilledRun(Graphics g) {
        this.setPlaneImage(Images.SMALL_PLANE_KILLED_IMG);
        this.setWidth(ImageConstants.SMALL_PLANE_KILLED_WIDTH);
        this.setHeight(ImageConstants.SMALL_PLANE_KILLED_HEIGHT);
        super.draw(g);
        try {
            Thread.sleep(Config.SMALL_PLANE_STATUS_CHANGE_INTERVAL);
        } catch (InterruptedException e) {

        }
        this.setPlaneImage(Images.SMALL_PLANE_ASHED_IMG);
        this.setWidth(ImageConstants.SMALL_PLANE_ASHED_WIDTH);
        this.setHeight(ImageConstants.SMALL_PLANE_ASHED_HEIGHT);
        super.draw(g);
        try {
            Thread.sleep(Config.SMALL_PLANE_STATUS_CHANGE_INTERVAL);
        } catch (InterruptedException e) {

        }
    }

    class DrawFighting implements Runnable {
        private Graphics g;

        DrawFighting(Graphics g) {
            this.g = g;
        }

        @Override
        public void run() {
            drawFightingRun(g);
        }
    }

    class DrawKilled implements Runnable {
        private Graphics g;

        DrawKilled(Graphics g) {
            this.g = g;
        }

        @Override
        public void run() {
            drawKilledRun(g);
        }

    }

}
