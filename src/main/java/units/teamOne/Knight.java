package units.teamOne;

import units.BaseHero;
import units.Hero;

public class Knight implements Hero{
    private final BaseHero hero;
    private int hp = 35;
    private int hit = 10;
    private int heal = 2;
    private int healHit = 10;
    private double kritical = 0.6;
    private int radiusAttack = 1;

    public Knight() {
        this.hero = new BaseHero(hp,hit,heal,healHit,kritical,radiusAttack);
    }

    public int getRadiusAttack() {
        return radiusAttack;
    }

    @Override
    public void heal(Hero friend) {
        this.hero.heal(friend);
    }

    @Override
    public void healHit(int value) {
        this.hero.healHit(healHit);
    }

    @Override
    public void degrade(Hero enemy) {
        this.hero.degrade(enemy);
    }

    @Override
    public void damage(int value) {
        this.hp -= value;
    }

    @Override
    public boolean actionToTeam() {
        return false;
    }

    @Override
    public boolean isAlive() {
        return this.hp > 0;
    }
}
