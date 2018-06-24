package units.teamTwo;

import units.BaseHero;
import units.Hero;

public class Sectoid implements Hero{

    private BaseHero hero;
    private int hp = 45;
    private int hit = 7;
    private int heal = 2;
    private int healHit = 7;
    private double kritical = 0.2;
    private int radiusAttack = 1;
    private int moves = 6;

    public Sectoid() {
        this.hero = new BaseHero(hp, hit, heal, healHit, kritical, radiusAttack, moves);
    }

    public int getHeal() {
        return this.heal;
    }

    public int getMoves() {
        return this.moves;
    }

    public int getHp() {
        return this.hp;
    }

    public int getRadiusAttack() {
        return radiusAttack;
    }

    @Override
    public void heal(Hero friend) {
        if (this.heal > 0){
            this.hero.heal(friend);
            this.heal--;
        }
    }

    @Override
    public void healHit(int value) {
        this.hp += healHit;
    }

    @Override
    public void degrade(Hero enemy) {
        this.hero.degrade(enemy);
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
