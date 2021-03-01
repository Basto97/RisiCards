package issou.collection.assets.enums;

public enum CharacterType {
    MageNoir,
    ChevalierBlanc;

    public static CharacterType Get(int id){
        return switch (id) {
            case 1 -> MageNoir;
            case 2 -> ChevalierBlanc;
            default -> MageNoir;
        };
    }
}

