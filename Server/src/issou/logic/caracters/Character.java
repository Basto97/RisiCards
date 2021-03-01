package issou.logic.caracters;

import issou.logic.utils.Identifiable;

public abstract class Character extends Identifiable {

    private int health;
    private int maxHealth;

    public Character(int maxHealth){
        super();
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public int getHealth(){
        return this.health;
    }

    public int heal(int amount){
        int beforeHealth = this.health;
        this.health = Math.min(health+amount,maxHealth);
        return this.health - beforeHealth;
    }

    public int takeDamages(int amount){
        int beforeHealth = this.health;
        this.health-= amount;
        if(this.health <= 0)
            die();
        return beforeHealth - this.health;
    }

    public abstract void die();
}
