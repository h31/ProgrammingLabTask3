package listener;

import entity.CatchableWeapon;

public interface CatchableWeaponListener {
    public void onCatchableWeaponLocationChanged(CatchableWeapon weapon);
}
