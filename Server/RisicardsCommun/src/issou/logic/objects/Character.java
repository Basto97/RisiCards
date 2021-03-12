package issou.logic.objects;

import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public abstract class Character extends Identifiable implements SerializableSFSType {

    public final int healthMax;
    public int health;
    public int healthBonus;
    public final int attack;
    public int attackBonus;
    public int attackBonusTurn;
    public int attacksPerTurn;
    public int attacksLeftThisTurn;

    public Character(int healthMax, int attack, int attacksForOneTurn, boolean charge){
        super();
        this.healthMax = healthMax;
        this.health = healthMax;
        this.healthBonus = 0;
        this.attack = attack;
        this.attackBonus = 0;
        this.attacksPerTurn = attacksForOneTurn;
        this.attacksLeftThisTurn = charge ? this.attacksPerTurn : 0;

    }

    public void setAttacksPerTurn(int attacksPerTurn){
        this.attacksPerTurn = attacksPerTurn;
    }
    public boolean canAttack(){
        return attacksLeftThisTurn > 0;
    }
    public int attack(){
        this.attacksLeftThisTurn--;
        return attack+attackBonus+attackBonusTurn;
    }
    public void newTurn(){
        this.attacksLeftThisTurn = this.attacksPerTurn;
    }
    public void changeAttackBonus(int amount) {
        this.attackBonus += amount;
    }
    public void changeAttackBonusTurn(int amount) {
        this.attackBonusTurn += amount;
    }
    public int heal(int amount){
        int beforeHealth = this.health;
        this.health = Math.min(health+amount, healthMax);
        return this.health - beforeHealth;
    }
    public int takeDamages(int amount){
        int beforeHealth = this.health;
        this.health-= amount;
        // if(this.health <= 0) die();
        return beforeHealth - this.health;
    }
    public void changeHealthBonus(int amount) {
        this.healthBonus += amount;
    }

    public SFSObject toSFSObject(){
        SFSObject obj = super.toSFSObject();
        obj.putInt("healthMax", healthMax);
        obj.putInt("health", health);
        obj.putInt("healthBonus", healthBonus);
        obj.putInt("attack", attack);
        obj.putInt("attackBonus", attackBonus);
        obj.putInt("attackBonusTurn", attackBonusTurn);
        obj.putInt("attacksPerTurn", attacksPerTurn);
        obj.putInt("attacksLeftThisTurn", attacksLeftThisTurn);
        return obj;
    }
}
