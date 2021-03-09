package issou.collection.assets;

import org.json.JSONObject;

public class HeroAsset {

    private final String name;
    private final String heroPowerName;
    private final int health;
    private final int startMana;

    public HeroAsset(JSONObject json) {
        this.name = json.getString("name");
        this.health  = json.getInt("health");
        this.startMana = json.getInt("startMana");
        this.heroPowerName = json.getString("heroPower");
    }

    public String getName() {
        return name;
    }
    public String getHeroPowerName() {
        return heroPowerName;
    }
    public int getHealth() {
        return health;
    }
    public int getStartMana() {
        return startMana;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroAsset heroAsset = (HeroAsset) o;
        return name.equals(heroAsset.name);
    }
    public String toString() {
        return "HeroAsset{" +
                "name=" + name +
                ", health=" + health +
                ", startMana=" + startMana +
                ", heroPowername=" + heroPowerName +
                '}';
    }
}
