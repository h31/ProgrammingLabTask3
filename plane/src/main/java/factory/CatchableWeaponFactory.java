package factory;

import UI.GamePlayingPanel;
import config.CatchableWeaponType;
import config.Config;
import config.ImageConstants;
import entity.Bomb;
import entity.CatchableWeapon;
import entity.DoubleLaser;
import until.Images;

import java.util.Random;

public class CatchableWeaponFactory {
    public static final Random rand = new Random();

    public static CatchableWeapon createCatchableWeapon(GamePlayingPanel playingPanel, CatchableWeaponType weaponType) {
        CatchableWeapon weapon = null;
        switch (weaponType) {
            case BOMB:
                weapon = new Bomb(playingPanel, weaponType);
                weapon.setWidth(ImageConstants.BOMB_WIDTH);
                weapon.setHeight(ImageConstants.BOMB_HEIGHT);
                weapon.setWeaponImage(Images.BOMB_IMG);
                weapon.setSpeed(Config.POP_WEAPON_MOVE_SPEED);
                break;
            case DOUBLE_LASER:
                weapon = new DoubleLaser(playingPanel, weaponType);
                weapon.setWidth(ImageConstants.DOUBLE_LASER_WIDTH);
                weapon.setHeight(ImageConstants.DOUBLE_LASER_HEIGHT);
                weapon.setWeaponImage(Images.DOUBLE_LASER_IMG);
                weapon.setSpeed(Config.POP_WEAPON_MOVE_SPEED);
                break;
        }

        int posX = rand.nextInt(playingPanel.getWidth() - weapon.getWidth());
        int posY = 0;
        weapon.setPosX(posX);
        weapon.setPosY(posY);

        return weapon;
    }
}
