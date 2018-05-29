package listener;

import entity.EnemyPlane;

public interface EnemyPlaneListener {
    void onEnemyPlaneLocationChanged(EnemyPlane p);
}