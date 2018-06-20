package app.popov.gohookah.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ferrero on 16.05.2018.
 */

public class HookahList {
    ArrayList<Hookah> hookahs = new ArrayList<>();

    public void addHookah(Hookah hookah){
        hookahs.add(hookah);
    }

    public String[] getClubsNames(){
        List<String> names = new ArrayList<>();
        for(Hookah hookah : hookahs){
            names.add(hookah.getName());
            System.out.println(hookah.getName());
        }
        return names.toArray(new String[names.size()]);

    }

    public int size(){
        return hookahs.size();
    }
}
