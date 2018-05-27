package units;

import units.Hero;

public class BaseHero implements Hero {
    private int hp;
    private final int hit;
    private int heal;
    private int healHit;
    private double kritical;
    private int radiusAttack;

    public BaseHero(int hp, int hit, int heal, int healHit, double kritical, int radiusAttack) {
        this.hp = hp;
        this.hit = hit;
        this.heal = heal;
        this.healHit = healHit;
        this.kritical = kritical;
        this.radiusAttack = radiusAttack;
    }

    public int getRadiusAttack() {
        return radiusAttack;
    }

    @Override
    public void heal(Hero friend) {
        if (heal > 0) {
            friend.heal(friend);
            heal--;
        }
    }

    @Override
    public void healHit(int value) {
        if (this.hp + value >= hp){
            this.hp += (hp - this.hp);
        } else {
            this.hp += value;
        }
    }

    @Override
    public void degrade(Hero enemy) {
        enemy.damage(this.hit);
    }

    @Override
    public void damage(int value) {
        if (Math.random() <= this.kritical){
            this.hp -= value * 3;
        } else {
            this.hp -= value;
        }
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
