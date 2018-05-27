package units;

import units.Hero;
import units.Team;

public class Battle {
    private final Team first;
    private final Team second;

    public Battle(final Team first, final Team second) {
        this.first = first;
        this.second = second;
    }

    public void init() {
        boolean turn = true;
        do {
            Hero master = turn ? first.turn() : second.turn();
            if (master.actionToTeam()) {
                Hero friend = turn ? first.turn() : second.turn();
                master.heal(friend);
            } else {
                Hero enemy = !turn ? first.turn() : second.turn();
                master.degrade(enemy);
            }
            turn = !turn;
        } while(this.first.isAlive() && this.second.isAlive());
    }
}
