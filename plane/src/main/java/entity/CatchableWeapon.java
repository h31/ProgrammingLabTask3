package entity;

import UI.GamePlayingPanel;
import config.CatchableWeaponType;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class CatchableWeapon {
    private int posX;
    private int posY;

    private int width;
    private int height;
    private Image weaponImage;

    private GamePlayingPanel gamePlayingPanel;
    private CatchableWeaponType weaponType;
    private boolean useAnimation;
    private boolean useAnimationDone;
    private int speed;
    private boolean weaponDisappear;

    public CatchableWeapon(GamePlayingPanel gamePlayingPanel, CatchableWeaponType weaponType) {
        this.gamePlayingPanel = gamePlayingPanel;
        this.weaponType = weaponType;
        this.useAnimation = false;
        this.useAnimationDone = false;
        this.weaponDisappear = false;
    }

    public Rectangle getRectangle() {
        return new Rectangle(posX, posY, width, height);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(weaponImage, posX, posY, width, height, (ImageObserver) gamePlayingPanel);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getWeaponImage() {
        return weaponImage;
    }

    public void setWeaponImage(Image weaponImage) {
        this.weaponImage = weaponImage;
    }

    public GamePlayingPanel getGamePlayingPanel() {
        return gamePlayingPanel;
    }

    public void setGamePlayingPanel(GamePlayingPanel gamePlayingPanel) {
        this.gamePlayingPanel = gamePlayingPanel;
    }

    public CatchableWeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(CatchableWeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isUseAnimation() {
        return useAnimation;
    }

    public void setUseAnimation(boolean useAnimation) {
        this.useAnimation = useAnimation;
    }

    public boolean isUseAnimationDone() {
        return useAnimationDone;
    }

    public void setUseAnimationDone(boolean useAnimationDone) {
        this.useAnimationDone = useAnimationDone;
    }

    public boolean isWeaponDisappear() {
        return weaponDisappear;
    }

    public void setWeaponDisappear(boolean weaponDisappear) {
        this.weaponDisappear = weaponDisappear;
    }

}
