package issou.logic.objects.caracters;

import issou.collection.assets.HeroAsset;

public class Hero extends Character {

    private final String name;
    private final int startMana;

    public Hero(HeroAsset heroAsset) {
        super(heroAsset.getHealth(), 0,1 , true);
        this.name = heroAsset.getName();
        this.startMana = heroAsset.getStartMana();
    }

    public void die(){

    }

    public String getName() {
        return name;
    }

    public int getStartMana() {
        return this.startMana;
    }

    public String toString() {
        return "Hero{" + "name=" + name + '}';
    }
}
