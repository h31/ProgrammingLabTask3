package listener;

import entity.Bullet;

public interface BulletListener {
    void onBulletLocationChanged(Bullet b);
}