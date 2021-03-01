package issou.collection.assets.enums;

public enum CardType {
    Minion,
    Spell;

    public static CardType Get(int id){
        return switch (id) {
            case 1 -> Minion;
            case 2 -> Spell;
            default -> Minion;
        };
    }
}
