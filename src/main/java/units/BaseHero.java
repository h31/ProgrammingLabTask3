package units;

import units.Hero;

public class BaseHero implements Hero {
    private int hp;
    private final int hit;
    private int heal;
    private int healHit;
    private double kritical;
    private int radiusAttack;
    private int moves;

    public BaseHero(int hp, int hit, int heal, int healHit, double kritical, int radiusAttack, int moves) {
        this.hp = hp;
        this.hit = hit;
        this.heal = heal;
        this.healHit = healHit;
        this.kritical = kritical;
        this.radiusAttack = radiusAttack;
        this.moves = moves;
    }

    public int getHp() {
        return hp;
    }

    public int getMoves() {
        return this.moves;
    }

    public int getRadiusAttack() {
        return this.radiusAttack;
    }

    @Override
    public void heal(Hero friend) {
        if (this.heal > 0) {
            friend.healHit(this.healHit);
            this.heal--;
        } else {
            System.out.println("Аптечек больше нет.");
        }
    }

    @Override
    public void healHit(int value) {
            this.hp += value;
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
