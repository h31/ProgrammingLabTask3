package units;

public interface Hero {

    void heal(Hero friend);

    void degrade(Hero enemy);

    void damage(int value);

    void healHit(int value);

    boolean isAlive();

    boolean actionToTeam();
}
