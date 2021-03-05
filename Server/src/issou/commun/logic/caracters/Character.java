package issou.commun.logic.caracters;

import issou.commun.logic.utils.Identifiable;

public abstract class Character extends Identifiable implements ICharacter {

    private final int healthMax;
    private int health;
    private int healthBonus;

    private final int attack;
    private int attackBonus;
    private int attackBonusTurn;

    private int attacksPerTurn;
    private int attacksLeftThisTurn;

    public Character(int healthMax, int attack, int attacksForOneTurn, boolean charge){
        super();
        this.healthMax = healthMax;
        this.health = healthMax;
        this.healthBonus = 0;
        this.attack = attack;
        this.attackBonus = 0;
        this.attacksPerTurn = attacksForOneTurn;
        if(charge)
            this.attacksLeftThisTurn = this.attacksPerTurn;
        else
            this.attacksLeftThisTurn = 0;
    }

    @Override
    public void setAttacksPerTurn(int attacksPerTurn){
        this.attacksPerTurn = attacksPerTurn;
    }
    @Override
    public boolean canAttack(){
        return attacksLeftThisTurn > 0;
    }
    @Override
    public int attack(){
        this.attacksLeftThisTurn--;
        return attack+attackBonus+attackBonusTurn;
    }
    @Override
    public void newTurn(){
        this.attacksLeftThisTurn = this.attacksPerTurn;
    }
    @Override
    public void changeAttackBonus(int amount) {
        this.attackBonus += amount;
    }
    @Override
    public void changeAttackBonusTurn(int amount) {
        this.attackBonusTurn += amount;
    }
    @Override
    public int heal(int amount){
        int beforeHealth = this.health;
        this.health = Math.min(health+amount, healthMax);
        return this.health - beforeHealth;
    }
    @Override
    public int takeDamages(int amount){
        int beforeHealth = this.health;
        this.health-= amount;
        if(this.health <= 0) die();
        return beforeHealth - this.health;
    }
    @Override
    public int getHealthBonus(){
        return this.healthBonus;
    }
    @Override
    public void changeHealthBonus(int amount) {
        this.healthBonus += amount;
    }
    @Override
    public int getHealthMax(){
        return this.healthMax;
    }
    @Override
    public int getHealth(){
        return this.health;
    }
    @Override
    public int getAttack(){
        return this.attack;
    }
    @Override
    public int getAttackBonus(){
        return this.attackBonus;
    }
    @Override
    public int getAttackBonusTurn() {
        return this.attackBonusTurn;
    }
    @Override
    public int getAttacksPerTurn(){
        return attacksPerTurn;
    }
}
